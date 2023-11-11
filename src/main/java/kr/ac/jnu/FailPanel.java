package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FailPanel extends JPanel {
    public FailPanel() {
        setLayout(null);
        setOpaque(false);

        JButton btnBack = new JButton();
        setButtonGraphics(btnBack,"/Image/Button/back.png", 330, 95);
        btnBack.setBounds(990, 655, 330, 90);

        JButton btnSelectHint = new JButton();
        setButtonGraphics(btnSelectHint,"/Image/Button/selecthint.png", 330, 95);
        btnSelectHint.setBounds(990, 655, 330, 90);

        JLabel labelFail = new JLabel();
        ImageIcon iconFail = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/fail.png")));
        labelFail.setIcon(resizeImageIcon(iconFail, 1050, 700));
        labelFail.setBounds(175, 80, 1050, 700); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        add(btnBack);
        add(btnSelectHint);
        add(labelFail);
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