package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HintPanel extends JPanel {
    private JButton btnSlow, btnSpace, btnIncorrect, btnChar;
    private int hintBtnWidth = 510;
    private int hintBtnHeight = 320;
    public HintPanel() {
        setLayout(null);
        setOpaque(false); // InitialPanel을 투명하게 설정

        btnSpace = new JButton();
        setButtonGraphics(btnSpace, "/Image/Button/space.png", hintBtnWidth, hintBtnHeight); // 먼저 아이콘을 설정
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
        setButtonGraphics(btnChar, "/Image/Button/char.png", hintBtnWidth, hintBtnHeight); // 먼저 아이콘을 설정
        btnChar.setBounds(710, 50, hintBtnWidth, hintBtnHeight); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정

        btnSlow = new JButton();
        setButtonGraphics(btnSlow, "/Image/Button/slow.png", hintBtnWidth, hintBtnHeight); // 먼저 아이콘을 설정
        btnSlow.setBounds(180, 410, hintBtnWidth, hintBtnHeight); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정
        btnSlow.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            MainPanel mainPanel = (MainPanel) wordPopcorn.getCardPanel().getComponent(3); // MainPanel 인덱스에 맞게 조정

            // EditingPanel 표시
            wordPopcorn.showCard("EditingPanel");
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
        setButtonGraphics(btnIncorrect, "/Image/Button/incorrect.png", hintBtnWidth, hintBtnHeight); // 먼저 아이콘을 설정
        btnIncorrect.setBounds(710, 410, hintBtnWidth, hintBtnHeight); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정

        add(btnSpace);
        add(btnChar);
        add(btnSlow);
        add(btnIncorrect);
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