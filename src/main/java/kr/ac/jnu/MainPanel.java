package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MainPanel extends JPanel {
    private String savedLyrics; // 사용자가 저장한 가사를 보관할 변수
    private int incorrectAnswersCount = 0; // 오답 수 저장 변수
    private SongLibrary songLibrary; // SongLibrary 객체 선언
    private String currentSongName;
    private AudioPlayer player = new AudioPlayer();
    private JLabel labelBoard, labelRound;
    private JButton btnPlaySong, btnSubmit, btnSaveLyrics, btnPlaySlow;
    private JTextArea answerTextArea;
    private boolean[] roundsPlayed = new boolean[3]; // 3라운드를 위한 재생 여부 배열
    private int currentRound = 0;
    private final int imageWidth = 1300;  // 기본 이미지 너비 설정
    private final int imageHeight = 500; // 기본 이미지 높이 설정
    public MainPanel() throws IOException, FontFormatException {
        setOpaque(false);
        setLayout(null); // 레이아웃 매니저를 null로 설정
        songLibrary = new SongLibrary(); // SongLibrary 객체 초기화

        savedLyrics = ""; // 초기화

        btnPlaySong = new JButton();
        UIUtils.setButtonGraphics(btnPlaySong, "/Image/Button/playsong.png", 240, 70); // 먼저 아이콘을 설정
        btnPlaySong.setBounds(1100, 580, 240, 70); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnPlaySong.addActionListener(e -> {
            if (player.isPlaying()) {
                try {
                    // CustomInfoDialog를 사용하여 메시지를 표시합니다.
                    CustomInfoDialog.showInfoDialog((JFrame) SwingUtilities.getWindowAncestor(this), "알림", "노래가 재생 중입니다!", 17f, 600, 200);
                } catch (IOException | FontFormatException ex) {
                    ex.printStackTrace();
                    // 필요한 경우 오류 처리를 추가할 수 있습니다.
                }
            } else {
                try {
                    playRoundSong();
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // 제출 버튼
        btnSubmit = new JButton();
        UIUtils.setButtonGraphics(btnSubmit, "/Image/Button/submit.png", 240, 70);
        btnSubmit.setBounds(1100, 670, 240, 70);
        btnSubmit.addActionListener(e -> {
            JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this); // JFrame 객체를 가져옵니다.
            // 먼저, 노래가 재생 중인지 확인합니다.
            if (!player.isPlaying()) {
                if (roundsPlayed[currentRound]) { // 해당 라운드의 노래를 이미 들었는지 확인합니다.
                    if (savedLyrics.length() == songLibrary.getLyricsByTitle(currentSongName).length()) {
                        ((WordPopcorn) SwingUtilities.getWindowAncestor(MainPanel.this)).showCard("GradingPanel");

                        Timer timer = new Timer(4000, event -> {
                            if (checkAnswer()) {
                                ((WordPopcorn) SwingUtilities.getWindowAncestor(MainPanel.this)).showCard("SuccessPanel");
                                savedLyrics = "";
                                currentRound = 0;
                            } else {
                                // 오답 처리
                                boolean isLastRound = currentRound == roundsPlayed.length - 1;
                                updateFailPanel(isLastRound);
                                ((WordPopcorn) SwingUtilities.getWindowAncestor(MainPanel.this)).showCard("FailPanel");
                                savedLyrics = "";
                                if (!isLastRound) {
                                    currentRound++;
                                } else {
                                    currentRound = 0;
                                }
                            }
                        });
                        timer.setRepeats(false); // 타이머가 한 번만 실행되도록 설정합니다.
                        timer.start(); // 타이머 시작
                    } else {
                        try {
                            CustomInfoDialog.showInfoDialog(owner, "알림", "답안과 정답의 글자 수가 서로 맞지 않거나, 답안이 저장되지 않았습니다.", 17f, 600, 200);
                        } catch (IOException | FontFormatException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    // 해당 라운드의 노래를 아직 듣지 않았으므로 메시지를 표시합니다.
                    try {
                        CustomInfoDialog.showInfoDialog(owner, "알림", "이 라운드의 노래를 먼저 들어야 합니다.", 17f, 600, 200);
                    } catch (IOException | FontFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else {
                // 노래가 재생 중일 경우, 경고 메시지를 표시합니다.
                try {
                    CustomInfoDialog.showInfoDialog(owner, "알림", "노래가 아직 재생 중입니다! 재생이 끝난 후 시도해 주세요.", 17f, 600, 200);
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // 저장 버튼
        btnSaveLyrics = new JButton();
        UIUtils.setButtonGraphics(btnSaveLyrics, "/Image/Button/save.png",130, 170);
        btnSaveLyrics.setBounds(965, 580, 130, 170); // 적절한 위치와 크기 설정
        btnSaveLyrics.addActionListener(e -> {
            try {
                saveLyrics(); // saveLyrics 함수 호출
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

        btnPlaySlow = new JButton();
        UIUtils.setButtonGraphics(btnPlaySlow, "/Image/Button/playslow.png", 240, 75);
        btnPlaySlow.setBounds(1100, 580, 240, 75); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnPlaySlow.setVisible(false);
        btnPlaySlow.addActionListener(e -> {
            JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this); // JFrame 객체를 가져옵니다.
            if (player.isPlaying()) {
                try {
                    CustomInfoDialog.showInfoDialog(owner, "알림", "노래가 이미 재생 중입니다!", 17f, 600, 200);
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                try {
                    String slowSongFilePath = "/Sound/smusic_" + currentSongName + ".wav";
                    player.load(slowSongFilePath);
                    player.play();
                    new Timer(100, e1 -> {
                        if (!player.isPlaying()) {
                            btnPlaySlow.setVisible(false);
                            ((Timer) e1.getSource()).stop(); // 타이머 정지
                        }
                    }).start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        CustomInfoDialog.showInfoDialog((JFrame) SwingUtilities.getWindowAncestor(this), "오류", "노래를 재생할 수 없습니다.", 17f, 600, 200);
                    } catch (IOException | FontFormatException exc) {
                        throw new RuntimeException(exc);
                    }
                }
            }
        });


        labelBoard = new JLabel();
        labelBoard.setBounds(50, 50, imageWidth, imageHeight); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        labelRound = new JLabel();
        ImageIcon iconRound = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/round" + (currentRound + 1) + ".png")));
        labelRound.setIcon(UIUtils.resizeImageIcon(iconRound, 240, 80));
        labelRound.setBounds(575, 25, 240, 80); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        // JTextArea 생성
        answerTextArea = new JTextArea();
        answerTextArea.setLineWrap(true); // 자동 줄바꿈 활성화
        answerTextArea.setWrapStyleWord(true); // 단어 단위로 줄바꿈

        // JScrollPane에 JTextArea 추가
        JScrollPane scrollPane = new JScrollPane(answerTextArea);
        scrollPane.setBounds(140, 585, 800, 150); // JTextArea가 있던 위치와 크기로 설정
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // 사용자 정의 폰트 적용
        InputStream is = getClass().getResourceAsStream("/Font/DNFBitBitv2.ttf");
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
        customFont = customFont.deriveFont(30f); // 폰트 크기 30f로 설정
        answerTextArea.setFont(customFont);

        add(labelRound);
        add(labelBoard);
        add(btnPlaySlow);
        add(btnPlaySong);
        add(btnSubmit);
        add(btnSaveLyrics);
        add(scrollPane);
    }

    private void playRoundSong() throws IOException, FontFormatException {
        if (currentRound < roundsPlayed.length && !roundsPlayed[currentRound]) {
            try {
                player.load("/Sound/music_" + currentSongName + ".wav"); // 라운드에 맞는 노래 로드
                player.play(); // 노래 재생
                roundsPlayed[currentRound] = true; // 현재 라운드에서 노래가 재생됨을 표시
            } catch (Exception ex) {
                ex.printStackTrace(); // 오류 출력
            }
        } else {
            CustomInfoDialog.showInfoDialog((JFrame) SwingUtilities.getWindowAncestor(this), "알림", "이 라운드에서는 노래를 이미 들었습니다.", 17f, 600, 200);
        }
    }
    public JButton getBtnPlaySlow() {
        return btnPlaySlow;
    }
    public int getIncorrectAnswersCount() {
        return incorrectAnswersCount;
    }
    public void setMain(String songName) {
        this.currentSongName = songName; // 멤버 변수에 노래 제목 저장
        ImageIcon iconMain = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Board/board_" + songName + ".png")));
        labelBoard.setIcon(UIUtils.resizeImageIcon(iconMain, imageWidth, imageHeight));
    }
    private void updateFailPanel(boolean lastRound) {
        WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
        FailPanel failPanel = ((FailPanel) wordPopcorn.getCardPanel().getComponent(6));
        failPanel.updateForRoundStatus(!lastRound); // 마지막 라운드가 아니면 true, 마지막 라운드면 false
    }
    private boolean checkAnswer() {
        int counter = 0; // 틀린 문자 수 초기화
        String correctAnswer = songLibrary.getLyricsByTitle(currentSongName); // 현재 노래의 정답 가사 가져오기

        for (int i = 0; i < correctAnswer.length(); i++) {
            if (savedLyrics.charAt(i) != correctAnswer.charAt(i)) {
                counter++;
            }
        }

        this.incorrectAnswersCount = counter;
        return savedLyrics.equals(correctAnswer); // 저장된 가사와 정답 가사가 완전히 같은지 비교
    }
    private boolean isOnlyKorean(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!(ch >= '\uAC00' && ch <= '\uD7A3')) {
                return false; // 한글이 아닌 문자가 포함되어 있습니다.
            }
        }
        return true; // 모든 문자가 한글입니다.
    }
    // 가사의 특정 위치에 있는 글자 반환
    public String getCharacterAt(int index) {
        String currentLyrics = songLibrary.getLyricsByTitle(currentSongName); // 현재 라운드의 노래 가사 가져오기

        if (currentLyrics != null && index >= 0 && index < currentLyrics.length()) {
            return String.valueOf(currentLyrics.charAt(index));
        } else {
            return "잘못된 위치";
        }
    }
    public void saveLyrics() throws IOException, FontFormatException {
        String lyrics = answerTextArea.getText(); // 텍스트 가져오기
        lyrics = lyrics.replaceAll("\\s+", ""); // 모든 공백 제거
        JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this); // JFrame 객체를 가져옵니다.

        if (lyrics.isEmpty()) {
            CustomInfoDialog.showInfoDialog(owner, "알림", "가사를 입력해주세요.", 17f, 600, 200);
        } else if (!isOnlyKorean(lyrics)) {
            CustomInfoDialog.showInfoDialog(owner, "알림", "가사는 한글로만 구성되어야 합니다.", 17f, 600, 200);
        } else {
            this.savedLyrics = lyrics;
            answerTextArea.setText(""); // 입력 필드 초기화
            CustomInfoDialog.showInfoDialog(owner, "알림", "가사가 저장되었습니다.", 17f, 600, 200);
        }
    }

    // board 이미지 변경 메서드
    public void updateBoardImage(String songName) {
        // 새 이미지 파일 경로 생성
        String imagePath = "/Image/Board/sboard_" + songName + ".png";
        ImageIcon iconChangedBoard = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        labelBoard.setIcon(UIUtils.resizeImageIcon(iconChangedBoard, imageWidth, imageHeight));
    }
    public void updateRoundLabel() {
        ImageIcon iconRound = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/round" + (currentRound + 1) + ".png")));
        labelRound.setIcon(UIUtils.resizeImageIcon(iconRound, 240, 80));
    }

    public void resetPanel() {
        // 저장된 가사 초기화
        savedLyrics = "";

        // 현재 노래 이름 초기화
        currentSongName = "";

        // 답변 텍스트 영역 초기화
        if (answerTextArea != null) {
            answerTextArea.setText("");
        }

        // 현재 라운드 번호 초기화
        currentRound = 0;

        for (int i = 0; i < roundsPlayed.length; i++) {
            roundsPlayed[i] = false;
        }
    }
}