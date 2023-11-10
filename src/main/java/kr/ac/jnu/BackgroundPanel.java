package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BackgroundPanel extends JPanel {
    private final Image backgroundImage;

    public BackgroundPanel() {
        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/background.jpg"))).getImage().getScaledInstance(1400, 775, Image.SCALE_SMOOTH);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}