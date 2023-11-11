package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SuccessPanel extends JPanel {
    public SuccessPanel() {
        setLayout(null);
        setOpaque(false);

        JButton btnback = new JButton();
        setButtonGraphics(btnback, "/Image/Button/back.png", 330, 95);
        btnback.setBounds(990, 655, 330, 90);

        JLabel labelSuccess = new JLabel();
        ImageIcon iconSuccess = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/success.png")));
        labelSuccess.setIcon(resizeImageIcon(iconSuccess, 1050, 700));
        labelSuccess.setBounds(175, 80, 1050, 700); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        add(btnback);
        add(labelSuccess);
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