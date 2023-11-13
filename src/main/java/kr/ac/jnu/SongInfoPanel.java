package kr.ac.jnu;

import javax.swing.*;
import java.util.Objects;

public class SongInfoPanel extends JPanel {
    private JLabel labelSongInfo;
    private JButton btnStart;
    private final int imageWidth = 1100;  // 기본 이미지 너비 설정
    private final int imageHeight = 650; // 기본 이미지 높이 설정

    public SongInfoPanel() {
        setLayout(null);
        setOpaque(false); // 패널을 투명하게 설정

        btnStart = new JButton();
        UIUtils.setButtonGraphics(btnStart, "/Image/Button/startgame.png", 360, 110);
        btnStart.setBounds(970, 640, 360, 110);
        btnStart.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            ((MainPanel) wordPopcorn.getCardPanel().getComponent(3)).setMain(wordPopcorn.getCurrentSongName());
            wordPopcorn.showCard("MainPanel");
        });

        labelSongInfo = new JLabel();
        labelSongInfo.setBounds(150, 60, imageWidth, imageHeight); // 레이블 위치와 크기 설정

        add(btnStart);
        add(labelSongInfo); // 레이블을 패널에 추가
    }

    public void setSongInfo(String songName) {
        ImageIcon iconSonginfo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Songinfo/songinfo_" + songName + ".png")));
        labelSongInfo.setIcon(UIUtils.resizeImageIcon(iconSonginfo, imageWidth, imageHeight));
    }
}