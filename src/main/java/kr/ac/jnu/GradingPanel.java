package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GradingPanel extends JPanel {
    private Timer blinkTimer;
    public GradingPanel() {
        setLayout(null);
        setOpaque(false);

        JLabel labelGrading = new JLabel();
        ImageIcon iconGrading = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/grading.png")));
        labelGrading.setIcon(resizeImageIcon(iconGrading, 900, 300));
        labelGrading.setBounds(290, 240, 900, 300); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)
        add(labelGrading);

        // 깜빡이는 타이머 설정
        blinkTimer = new Timer(500, e -> labelGrading.setVisible(!labelGrading.isVisible()));
        blinkTimer.start();
    }

    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image resizedImage = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    // 패널이 더 이상 보이지 않을 때 타이머를 정지합니다.
    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (!aFlag) {
            blinkTimer.stop();
        } else {
            blinkTimer.start();
        }
    }
}