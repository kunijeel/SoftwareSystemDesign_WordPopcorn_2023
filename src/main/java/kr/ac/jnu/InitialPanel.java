package kr.ac.jnu;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

/**
 * 이 클래스는 애플리케이션의 초기 화면을 나타내는 패널을 구현합니다.
 * 게임을 시작하거나 종료할 수 있는 버튼과 게임 플레이 가이드를 보여주는 버튼이 포함되어 있습니다.
 *
 * @author Tam Oh
 */
public class InitialPanel extends JPanel {
    /**
     * 게임을 시작하기 위한 버튼입니다.
     */
    private JButton btnAccess;

    /**
     * 애플리케이션을 종료하기 위한 버튼입니다.
     */
    private JButton btnExit;

    /**
     * 게임 플레이 가이드를 보여주기 위한 버튼입니다.
     */
    private JButton btnPlayGuide;

    /**
     * 랭킹을 표시하기 위한 버튼입니다.
     * 이 버튼을 클릭하면 사용자는 게임의 랭킹 정보를 볼 수 있는 창을 볼 수 있습니다.
     */
    private JButton btnRanking;

    /**
     * 타이틀 이미지를 표시하기 위한 레이블입니다.
     */
    private JLabel labelTitle;

    /**
     * 게임에서 사용되는 노래 정보를 관리하는 SongLibrary 객체입니다.
     * 이 객체는 노래 제목, 가사 및 힌트 정보를 관리합니다.
     */
    private SongLibrary songLibrary;

    /**
     * InitialPanel의 생성자입니다.
     * 이 생성자는 패널의 레이아웃을 설정하고, 필요한 버튼과 타이틀 라벨을 초기화합니다.
     */
    public InitialPanel() {
        setLayout(null);
        setOpaque(false); // InitialPanel을 투명하게 설정
        songLibrary = new SongLibrary();
        // 시작 버튼
        btnAccess = new JButton();
        UIUtils.setButtonGraphics(btnAccess, "/Image/Button/access.png", 440, 130); // 먼저 아이콘을 설정
        btnAccess.setBounds(480, 480, 440, 130); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnAccess.addActionListener(e -> ((WordPopcorn)SwingUtilities.getWindowAncestor(this)).showCard("SongSelectPanel"));

        // 나가기 버튼
        btnExit = new JButton();
        UIUtils.setButtonGraphics(btnExit, "/Image/Button/exit.png", 440, 130); // 먼저 아이콘을 설정
        btnExit.setBounds(480, 620, 440, 130); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnExit.addActionListener(e -> {
            // 프로그램 종료
            System.exit(0);
        });

        btnPlayGuide = new JButton();
        UIUtils.setButtonGraphics(btnPlayGuide, "/Image/Button/guide.png", 50, 50); // 먼저 아이콘을 설정
        btnPlayGuide.setBounds(1260, 60, 50, 50); // 버튼 위치 및 크기 설정
        btnPlayGuide.addActionListener(e -> showGamePlayInfo());

        btnRanking = new JButton();
        UIUtils.setButtonGraphics(btnRanking, "/Image/Button/btnranking.png", 100, 100);
        btnRanking.setBounds(100 ,660, 100, 100);
        btnRanking.addActionListener(e -> openRankingFrame());

        labelTitle = new JLabel();
        ImageIcon iconTitle = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/title.png"))); // 기존의 타이틀 이미지 경로
        labelTitle.setIcon(UIUtils.resizeImageIcon(iconTitle, 1200, 400));
        labelTitle.setBounds(100, 50, 1200, 400); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        // 버튼과 타이틀을 패널에 추가
        add(labelTitle);
        add(btnAccess);
        add(btnExit);
        add(btnPlayGuide);
        add(btnRanking);
    }

    /**
     * 게임 플레이 방법을 보여주는 창을 표시합니다.
     * 이 메소드는 게임 플레이 가이드 버튼이 눌렸을 때 호출됩니다.
     */
    private void showGamePlayInfo() {
        JFrame infoFrame = new JFrame("게임 플레이 방법");
        PlayGuidePanel guidePanel = new PlayGuidePanel("/Image/guideimg.png", 800, 450); // 800x600 크기로 설정
        infoFrame.add(guidePanel);
        infoFrame.pack();
        infoFrame.setLocationRelativeTo(null);
        infoFrame.setVisible(true);
    }

    /**
     * 랭킹 정보를 표시하는 프레임을 열기 위한 메소드입니다.
     * 이 메소드는 btnRanking 버튼이 클릭될 때 호출되며, RankingFrame을 생성하고 표시합니다.
     * 랭킹 데이터 로딩 중 오류가 발생하면 사용자에게 오류 메시지를 표시합니다.
     */
    private void openRankingFrame() {
        try {
            RankingFrame rankingFrame = new RankingFrame(songLibrary);
            rankingFrame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "랭킹 데이터를 불러오는 데 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}