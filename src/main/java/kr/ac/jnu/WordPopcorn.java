package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;

public class WordPopcorn extends JFrame {
    private String currentSongName; // 현재 노래 제목을 저장하는 변수
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel; // 이제 cardPanel을 클래스의 필드로 선언하였습니다.

    public WordPopcorn() {
        // 레이어드 팬 생성
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1400, 775)); // 레이어드 팬의 선호 사이즈 설정

        // 배경 패널 설정
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(0, 0, 1400, 775); // 배경 패널의 크기와 위치 설정

        // 카드 패널 설정
        cardPanel = new JPanel(cardLayout); // 여기에서 초기화하였습니다.
        cardPanel.setOpaque(false); // 카드 패널을 투명하게 설정하여 배경이 보이도록 함
        cardPanel.setBounds(0, 0, 1400, 775); // 카드 패널의 크기와 위치 설정

        // 패널 추가
        InitialPanel initialPanel = new InitialPanel(); // 초기 패널 인스턴스 생성
        SongSelectPanel songSelectPanel = new SongSelectPanel(); // SongSelectPanel 인스턴스 생성
        SongInfoPanel songInfoPanel = new SongInfoPanel();
        MainPanel mainPanel = new MainPanel();
        HintPanel hintPanel = new HintPanel();
        GradingPanel gradingPanel = new GradingPanel();
        SuccessPanel successPanel = new SuccessPanel();
        FailPanel failPanel = new FailPanel();
        cardPanel.add(initialPanel, "InitialPanel");
        cardPanel.add(songSelectPanel, "SongSelectPanel"); // cardPanel에 SongSelectPanel 추가
        cardPanel.add(songInfoPanel, "SongInfoPanel");
        cardPanel.add(mainPanel, "MainPanel");
        cardPanel.add(hintPanel, "HintPanel");
        cardPanel.add(gradingPanel, "GradingPanel");
        cardPanel.add(successPanel, "SuccessPanel");
        cardPanel.add(failPanel, "FailPanel");

        // 레이어드 팬에 배경 패널과 카드 패널을 추가
        layeredPane.add(backgroundPanel, Integer.valueOf(1)); // 배경은 레이어 1에 추가
        layeredPane.add(cardPanel, Integer.valueOf(2)); // 카드 패널은 레이어 2에 추가

        // JFrame 설정
        setContentPane(layeredPane);
        setSize(1400, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setCurrentSongName(String name) {
        this.currentSongName = name;
    }
    public String getCurrentSongName() {
        return this.currentSongName;
    }
    public void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }
    public JPanel getCardPanel() {
        return this.cardPanel; // 'this'는 현재 객체를 참조하며, cardPanel 필드를 반환합니다.
    }
    public static void main(String[] args) {
        // GUI 실행
        SwingUtilities.invokeLater(() -> {
            WordPopcorn frame = new WordPopcorn();
            frame.cardLayout.show(frame.cardPanel, "InitialPanel"); // 프로그램 시작 시 InitialPanel 보이기
            frame.setVisible(true);
        });
    }
}