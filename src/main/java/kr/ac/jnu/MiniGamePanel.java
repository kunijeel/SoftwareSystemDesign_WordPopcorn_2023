package kr.ac.jnu;

import javax.swing.*;
import java.util.Objects;

public class MiniGamePanel extends JPanel {
    private JLabel labelBonusHint;
    private JButton btnGameA;

    public MiniGamePanel() {
        setLayout(null);
        setOpaque(false);

        btnGameA = new JButton();
        UIUtils.setButtonGraphics(btnGameA,"/Image/Button/miniA.png", 500, 150);
        btnGameA.setBounds(450, 270, 500, 150);
        btnGameA.addActionListener(e -> {
            ((WordPopcorn)SwingUtilities.getWindowAncestor(this)).showCard("MainPanel");
        });

        labelBonusHint = new JLabel();
        ImageIcon iconBonusHint = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/bonushint.png")));
        labelBonusHint.setIcon(UIUtils.resizeImageIcon(iconBonusHint, 800, 200));
        labelBonusHint.setBounds(300, 40, 800, 200);

        add(btnGameA);
        add(labelBonusHint);
    }
}