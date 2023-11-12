package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class EditingPanel extends JPanel {
    private Timer blinkTimer;
    public EditingPanel() {
        setLayout(null);
        setOpaque(false);

        JLabel labelEditing = new JLabel();
        ImageIcon iconGrading = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/editing.png")));
        labelEditing.setIcon(resizeImageIcon(iconGrading, 1000, 500));
        labelEditing.setBounds(200, 140, 1000, 500); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)
        add(labelEditing);

        // 깜빡이는 타이머 설정
        blinkTimer = new Timer(500, e -> labelEditing.setVisible(!labelEditing.isVisible()));
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
