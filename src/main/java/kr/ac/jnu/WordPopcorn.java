package kr.ac.jnu;

import kr.ac.jnu.avoidancegame.AvoidanceGameFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * WordPopcorn 클래스는 게임의 메인 윈도우를 나타내는 프레임을 구현합니다.
 * 이 클래스는 게임의 다양한 패널을 관리하고, 현재 선택된 노래의 이름을 추적합니다.
 *
 * @author Tam Oh
 */
public class WordPopcorn extends JFrame {
    /**
     * 현재 선택된 노래의 제목을 저장하는 변수입니다.
     */
    private String currentSongName;

    /**
     * 여러 패널을 포함하는 JLayeredPane입니다.
     */
    private final JLayeredPane layeredPane;

    /**
     * 여러 패널을 전환하기 위한 CardLayout입니다.
     */
    private final CardLayout cardLayout = new CardLayout();

    /**
     * CardLayout을 사용하는 JPanel입니다. 게임의 다양한 화면이 이 패널에 추가됩니다.
     */
    private final JPanel cardPanel;

    /**
     * 게임의 배경을 표시하는 패널입니다.
     */
    private final BackgroundPanel backgroundPanel;

    private LoadingPanel loadingPanel;

    /**
     * 게임이 느리게 듣기 힌트를 선택했음을 알리는 메시지 패널입니다.
     */
    private final MessagePanel slowDownPanel;

    /**
     * 평가 중임을 알리는 메시지 패널입니다.
     */
    private final MessagePanel gradingPanel;

    /**
     * 가사판 수정중임을 알리는 메시지 패널입니다.
     */
    private final MessagePanel editingPanel;

    /**
     * 게임 시작 시 보여지는 초기 패널입니다.
     */
    private final InitialPanel initialPanel;

    /**
     * 노래 선택을 위한 패널입니다.
     */
    private final SongSelectPanel songSelectPanel;

    /**
     * 선택된 노래의 정보를 표시하는 패널입니다.
     */
    private final SongInfoPanel songInfoPanel;

    /**
     * 게임의 메인 화면을 표시하는 패널입니다.
     */
    private final MainPanel mainPanel;

    /**
     * 게임 힌트를 표시하는 패널입니다.
     */
    private final HintPanel hintPanel;

    /**
     * 게임 성공 시 보여지는 패널입니다.
     */
    private final SuccessPanel successPanel;

    /**
     * 게임 실패 시 보여지는 패널입니다.
     */
    private final FailPanel failPanel;

    private final BonusHintPanel bonusHintPanel;

    /**
     * WordPopcorn 클래스의 생성자.
     * 게임의 주요 GUI 구성 요소를 초기화하고 배치합니다.
     *
     * @throws IOException 파일 입출력 예외가 발생할 수 있습니다.
     * @throws FontFormatException 폰트 형식 예외가 발생할 수 있습니다.
     */
    public WordPopcorn() throws IOException, FontFormatException {
        // 레이어드 팬 생성
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1400, 775));

        // 배경 패널 설정
        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(0, 0, 1400, 775);

        // 카드 패널 설정
        cardPanel = new JPanel(cardLayout); // 여기에서 초기화하였습니다.
        cardPanel.setOpaque(false); // 카드 패널을 투명하게 설정하여 배경이 보이도록 함
        cardPanel.setBounds(0, 0, 1400, 775);

        // 패널 추가
        initialPanel = new InitialPanel(); // 초기 패널 인스턴스 생성
        songSelectPanel = new SongSelectPanel(); // SongSelectPanel 인스턴스 생성
        songInfoPanel = new SongInfoPanel();
        mainPanel = new MainPanel();
        hintPanel = new HintPanel();
        successPanel = new SuccessPanel();
        failPanel = new FailPanel();
        editingPanel = new MessagePanel("/Image/Sign/editing.png", 1000, 500, 200, 140);
        slowDownPanel = new MessagePanel("/Image/Sign/slowdown.png", 1180, 500, 110, 140);
        gradingPanel = new MessagePanel("/Image/Sign/grading.png", 900, 300, 290, 240);
        loadingPanel = new LoadingPanel("/Image/Sign/loading.png");
        bonusHintPanel = new BonusHintPanel(mainPanel);

        cardPanel.add(initialPanel, "InitialPanel");
        cardPanel.add(songSelectPanel, "SongSelectPanel"); // cardPanel에 SongSelectPanel 추가
        cardPanel.add(songInfoPanel, "SongInfoPanel");
        cardPanel.add(mainPanel, "MainPanel");
        cardPanel.add(hintPanel, "HintPanel");
        cardPanel.add(successPanel, "SuccessPanel");
        cardPanel.add(failPanel, "FailPanel");
        cardPanel.add(editingPanel, "EditingPanel");
        cardPanel.add(slowDownPanel, "SlowDownPanel");
        cardPanel.add(gradingPanel, "GradingPanel");
        cardPanel.add(loadingPanel, "LoadingPanel");
        cardPanel.add(bonusHintPanel, "BonusHintPanel");

        // 레이어드 팬에 배경 패널과 카드 패널을 추가
        layeredPane.add(backgroundPanel, Integer.valueOf(1)); // 배경은 레이어 1에 추가
        layeredPane.add(cardPanel, Integer.valueOf(2)); // 카드 패널은 레이어 2에 추가

        // JFrame 설정
        setTitle("Word Popcorn");
        setContentPane(layeredPane);
        setSize(1400, 803);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 현재 노래의 이름을 설정하는 메소드.
     *
     * @param name 설정할 노래의 이름입니다.
     */
    public void setCurrentSongName(String name) {
        this.currentSongName = name;
    }

    /**
     * 현재 노래의 이름을 반환하는 메소드.
     *
     * @return 현재 설정된 노래의 이름입니다.
     */
    public String getCurrentSongName() {
        return this.currentSongName;
    }

    /**
     * 지정된 이름의 카드(패널)을 보여주는 메소드.
     * 이 메소드는 CardLayout을 사용하여 카드 패널 중 하나를 표시합니다.
     *
     * @param cardName 보여줄 카드의 이름입니다.
     */
    public void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }

    /**
     * CardPanel을 반환하는 메소드.
     *
     * @return 현재 객체의 cardPanel 필드를 반환합니다.
     */
    public JPanel getCardPanel() {
        return this.cardPanel;
    }

    public void startLoading() {
        // 로딩 패널을 보여줍니다.
        showCard("LoadingPanel");

        // 로딩 처리를 위한 타이머 생성
        Timer timer = new Timer(6000, e -> SwingUtilities.invokeLater(() -> showCard("InitialPanel")));

        timer.setRepeats(false); // 타이머가 한 번만 실행되도록 설정
        timer.start(); // 타이머 시작
    }

    /**
     * 애플리케이션의 메인 메소드. 프로그램을 시작합니다.
     *
     * @param args 명령줄 인자들입니다.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordPopcorn frame;
            try {
                frame = new WordPopcorn();
                frame.setVisible(true);
                frame.startLoading();
            } catch (IOException | FontFormatException e) {
                throw new RuntimeException(e);
            }
        });
    }
}