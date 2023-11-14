package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;

public class PlayGuidePanel extends JPanel {
    private Image imgPlayGuide;

    public PlayGuidePanel(String imagePath) {
        imgPlayGuide = new ImageIcon(getClass().getResource(imagePath)).getImage();
        setPreferredSize(new Dimension(imgPlayGuide.getWidth(null), imgPlayGuide.getHeight(null)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgPlayGuide, 0, 0, this);
    }
}