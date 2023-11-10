package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainPanel extends JPanel {
    private String currentSongName;
    private JLabel labelBoard, labelRound;
    private JButton btnPlaySong, btnSubmit;
    private final int imageWidth = 1100;  // 기본 이미지 너비 설정
    private final int imageHeight = 500; // 기본 이미지 높이 설정
    public MainPanel() {
        setOpaque(false);
        setLayout(null); // 레이아웃 매니저를 null로 설정

        btnPlaySong = new JButton();
        setButtonGraphics(btnPlaySong, "/Image/Button/playsong.png", 250, 70); // 먼저 아이콘을 설정
        btnPlaySong.setBounds(1100, 560, 250, 100); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnPlaySong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnSubmit = new JButton();
        setButtonGraphics(btnSubmit, "/Image/Button/submit.png", 250, 70);
        btnSubmit.setBounds(1100, 640, 250, 100);

        labelBoard = new JLabel();
        labelBoard.setBounds(150, 50, imageWidth, imageHeight); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        labelRound = new JLabel();
        ImageIcon iconRound = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/round1.png")));
        labelRound.setIcon(resizeImageIcon(iconRound, 250, 60));
        labelRound.setBounds(575, 30, 250, 60); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        add(labelRound);
        add(labelBoard);
        add(btnPlaySong);
        add(btnSubmit);
    }

    public void setMain(String songName) {
        this.currentSongName = songName; // 멤버 변수에 노래 제목 저장
        ImageIcon mainIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Board/board_" + songName + ".png")));
        labelBoard.setIcon(resizeImageIcon(mainIcon, imageWidth, imageHeight));
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