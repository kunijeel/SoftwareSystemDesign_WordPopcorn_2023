package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SlowDownPanel extends JPanel {
    private Timer blinkTimer;
    public SlowDownPanel() {
        setLayout(null);
        setOpaque(false);

        JLabel labelSlowDown = new JLabel();
        ImageIcon iconGrading = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/slowdown.png")));
        labelSlowDown.setIcon(resizeImageIcon(iconGrading, 1180, 500));
        labelSlowDown.setBounds(110, 140, 1180, 500); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)
        add(labelSlowDown);

        // 깜빡이는 타이머 설정
        blinkTimer = new Timer(500, e -> labelSlowDown.setVisible(!labelSlowDown.isVisible()));
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
