package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setButtonGraphics(btnStart, "/Image/Button/startgame.png", 360, 110);
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
        labelSongInfo.setIcon(resizeImageIcon(iconSonginfo, imageWidth, imageHeight));
    }

    private void setButtonGraphics(JButton button, String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        Image newimg = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // 넘겨받은 width와 height를 사용하여 크기를 조정합니다.
        icon = new ImageIcon(newimg); // 조정된 이미지로 ImageIcon을 다시 생성합니다.

        button.setIcon(icon);
        button.setBorderPainted(false); // 버튼 경계를 그리지 않음
        button.setContentAreaFilled(false); // 내용 영역 배경을 그리지 않음
        button.setFocusPainted(false); // 선택(포커스)됐을 때 경계를 그리지 않음
        button.setOpaque(false); // 투명하게 설정
    }
    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image resizedImage = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}