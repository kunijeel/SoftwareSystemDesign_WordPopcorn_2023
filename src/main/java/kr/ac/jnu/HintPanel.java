package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 게임에서 사용되는 다양한 힌트 기능을 제공하는 패널을 구현하는 클래스입니다.
 * 이 패널에는 각기 다른 기능을 수행하는 여러 버튼이 포함되어 있습니다.
 *
 * @author Tam Oh
 */
public class HintPanel extends JPanel {
    /**
     * '느리게 재생' 힌트를 선택하기 위한 버튼입니다.
     */
    private JButton btnSlow;

    /**
     * '전체 띄어쓰기' 힌트를 선택하기 위한 버튼입니다.
     */
    private JButton btnSpace;

    /**
     * '오답 수 확인' 힌트를 선택하기 위한 버튼입니다.
     */
    private JButton btnIncorrect;

    /**
     * '한 글자 확인' 힌트를 선택하기 위한 버튼입니다.
     */
    private JButton btnChar;

    /**
     * 힌트 버튼의 너비입니다.
     */
    private int hintBtnWidth = 510;

    /**
     * 힌트 버튼의 높이입니다.
     */
    private int hintBtnHeight = 320;

    /**
     * HintPanel의 생성자입니다.
     * 이 생성자는 패널의 레이아웃을 설정하고, 각종 버튼을 초기화하며, 이벤트 리스너를 추가합니다.
     */
    public HintPanel() {
        setLayout(null);
        setOpaque(false); // InitialPanel을 투명하게 설정

        btnSpace = new JButton();
        UIUtils.setButtonGraphics(btnSpace, "/Image/Button/space.png", hintBtnWidth, hintBtnHeight); // 먼저 아이콘을 설정
        btnSpace.setBounds(180, 50, hintBtnWidth, hintBtnHeight); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnSpace.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            MainPanel mainPanel = (MainPanel) wordPopcorn.getCardPanel().getComponent(3); // MainPanel 인덱스에 맞게 조정

            // EditingPanel 표시
            wordPopcorn.showCard("EditingPanel");
            btnSpace.setVisible(false);

            // 4초 후 MainPanel로 전환하는 타이머 설정 및 시작
            Timer timer = new Timer(4000, event -> {
                mainPanel.updateBoardImage(wordPopcorn.getCurrentSongName()); // board 이미지 업데이트
                mainPanel.updateRoundLabel();
                wordPopcorn.showCard("MainPanel"); // MainPanel로 전환
            });
            timer.setRepeats(false); // 타이머가 한 번만 실행되도록 설정
            timer.start(); // 타이머 시작
        });

        btnChar = new JButton();
        UIUtils.setButtonGraphics(btnChar, "/Image/Button/char.png", hintBtnWidth, hintBtnHeight); // 먼저 아이콘을 설정
        btnChar.setBounds(710, 50, hintBtnWidth, hintBtnHeight); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnChar.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            MainPanel mainPanel = (MainPanel) wordPopcorn.getCardPanel().getComponent(3); // MainPanel 인덱스에 맞게 조정

            wordPopcorn.showCard("MainPanel"); // MainPanel로 전환

            while (true) {
                String positionStr = JOptionPane.showInputDialog(this, "원하는 한 글자의 위치에 해당하는 번호를 입력해 주세요 (첫 글자는 1번). 취소하시면 다시 힌트판으로 돌아갑니다.");
                if (positionStr == null || positionStr.isEmpty()) {
                    // 사용자가 취소하거나 빈 문자열을 입력한 경우 루프 종료
                    wordPopcorn.showCard("HintPanel"); // MainPanel로 전환
                    break;
                }

                try {
                    int position = Integer.parseInt(positionStr) - 1; // 사용자 입력은 1부터 시작하지만 배열 인덱스는 0부터 시작
                    String character = mainPanel.getCharacterAt(position);

                    if (!character.equals("잘못된 위치")) {
                        CustomInfoDialog.showInfoDialog((JFrame) SwingUtilities.getWindowAncestor(this), "알림", "선택한 위치의 글자 : " + character, 20f, 600, 200);
                        mainPanel.updateRoundLabel();
                        btnChar.setVisible(false);
                        break;
                    } else {
                        CustomInfoDialog.showInfoDialog((JFrame) SwingUtilities.getWindowAncestor(this), "알림", "잘못된 위치입니다. 다시 입력해 주세요.", 20f, 600, 200);
                    }
                } catch (NumberFormatException ex) {
                    try {
                        CustomInfoDialog.showInfoDialog((JFrame) SwingUtilities.getWindowAncestor(this), "알림", "유효한 숫자를 입력해 주세요.", 20f, 600, 200);
                    } catch (IOException | FontFormatException exc) {
                        throw new RuntimeException(exc);
                    }
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnSlow = new JButton();
        UIUtils.setButtonGraphics(btnSlow, "/Image/Button/slow.png", hintBtnWidth, hintBtnHeight); // 먼저 아이콘을 설정
        btnSlow.setBounds(180, 410, hintBtnWidth, hintBtnHeight); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnSlow.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            MainPanel mainPanel = (MainPanel) wordPopcorn.getCardPanel().getComponent(3); // MainPanel 인덱스에 맞게 조정

            wordPopcorn.showCard("SlowDownPanel");
            btnSlow.setVisible(false);

            // 4초 후 MainPanel로 전환하는 타이머 설정 및 시작
            Timer timer = new Timer(4000, event -> {
                mainPanel.getBtnPlaySlow().setVisible(true);
                mainPanel.updateRoundLabel();
                wordPopcorn.showCard("MainPanel"); // MainPanel로 전환
            });
            timer.setRepeats(false); // 타이머가 한 번만 실행되도록 설정
            timer.start(); // 타이머 시작
        });

        btnIncorrect = new JButton();
        UIUtils.setButtonGraphics(btnIncorrect, "/Image/Button/incorrect.png", hintBtnWidth, hintBtnHeight); // 먼저 아이콘을 설정
        btnIncorrect.setBounds(710, 410, hintBtnWidth, hintBtnHeight); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnIncorrect.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            MainPanel mainPanel = (MainPanel) wordPopcorn.getCardPanel().getComponent(3); // MainPanel 인덱스에 맞게 조정

            int incorrectCount = mainPanel.getIncorrectAnswersCount();
            mainPanel.updateRoundLabel();
            try {
                CustomInfoDialog.showInfoDialog(wordPopcorn, "오답 수 확인", "오답의 개수 : " + incorrectCount, 50f, 600, 300);
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
            wordPopcorn.showCard("MainPanel"); // MainPanel로 전환
            btnIncorrect.setVisible(false);
        });

        add(btnSpace);
        add(btnChar);
        add(btnSlow);
        add(btnIncorrect);
    }

    /**
     * 패널에 있는 모든 버튼을 다시 보이도록 설정합니다.
     * 이 메소드는 새로운 곡으로 플레이를 시작할 때 호출되어 패널을 초기 상태로 리셋합니다.
     */
    public void resetPanel() {
        // 모든 버튼을 보이게 설정
        btnSpace.setVisible(true);
        btnChar.setVisible(true);
        btnSlow.setVisible(true);
        btnIncorrect.setVisible(true);
    }
}