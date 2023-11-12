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
        labelMessage.setIcon(UIUtils.resizeImageIcon(iconMessage, width, height));
        labelMessage.setBounds(x, y, width, height); // 위치 설정
        add(labelMessage);

        // 깜빡이는 타이머 설정
        blinkTimer = new Timer(500, e -> labelMessage.setVisible(!labelMessage.isVisible()));
        blinkTimer.start();
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
