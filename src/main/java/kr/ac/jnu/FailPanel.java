package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FailPanel extends JPanel {
    private JButton btnSelectHint, btnBack;
    public FailPanel() {
        setLayout(null);
        setOpaque(false);

        btnBack = new JButton();
        setButtonGraphics(btnBack,"/Image/Button/back.png", 330, 95);
        btnBack.setBounds(990, 655, 330, 90);
        btnBack.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            wordPopcorn.showCard("InitialPanel"); // InitialPanel로 이동
        });

        btnSelectHint = new JButton();
        setButtonGraphics(btnSelectHint,"/Image/Button/selecthint.png", 330, 95);
        btnSelectHint.setBounds(990, 655, 330, 90);
        btnSelectHint.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            wordPopcorn.showCard("HintPanel"); // InitialPanel로 이동
        });

        JLabel labelFail = new JLabel();
        ImageIcon iconFail = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/fail.png")));
        labelFail.setIcon(resizeImageIcon(iconFail, 1050, 700));
        labelFail.setBounds(175, 80, 1050, 700); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        add(btnBack);
        add(btnSelectHint);
        add(labelFail);
    }

    public void updateForRoundStatus(boolean moreRoundsAvailable) {
        btnSelectHint.setVisible(moreRoundsAvailable); // 추가 라운드가 있으면 힌트 선택 버튼을 보여줌
        btnBack.setVisible(!moreRoundsAvailable); // 추가 라운드가 없으면 돌아가기 버튼을 보여줌
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