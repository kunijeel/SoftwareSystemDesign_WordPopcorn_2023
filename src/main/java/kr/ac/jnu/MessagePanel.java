package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MessagePanel extends JPanel {
    private Timer blinkTimer;
    private JLabel labelMessage;
    private ImageIcon iconMessage;

    public MessagePanel(String imagePath, int width, int height, int x, int y) {
        setLayout(null);
        setOpaque(false);

        labelMessage = new JLabel();
        iconMessage = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        labelMessage.setIcon(resizeImageIcon(iconMessage, width, height));
        labelMessage.setBounds(x, y, width, height); // 위치 설정
        add(labelMessage);

        // 깜빡이는 타이머 설정
        blinkTimer = new Timer(500, e -> labelMessage.setVisible(!labelMessage.isVisible()));
        blinkTimer.start();
    }

    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image resizedImage = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

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
