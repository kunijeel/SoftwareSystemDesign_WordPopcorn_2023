package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class InitialPanel extends JPanel {
    private JButton btnAccess, btnExit;
    private JLabel labelTitle;

    public InitialPanel() {
        setLayout(null);
        setOpaque(false); // InitialPanel을 투명하게 설정
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

        labelTitle = new JLabel();
        ImageIcon iconTitle = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/title.png"))); // 기존의 타이틀 이미지 경로
        labelTitle.setIcon(UIUtils.resizeImageIcon(iconTitle, 1200, 400));
        labelTitle.setBounds(100, 50, 1200, 400); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        // 버튼과 타이틀을 패널에 추가
        add(labelTitle);
        add(btnAccess);
        add(btnExit);

    }
}