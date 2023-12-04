package kr.ac.jnu;

import com.sun.tools.javac.Main;
import kr.ac.jnu.avoidancegame.AvoidanceGameFrame;

import javax.swing.*;
import java.util.Objects;

public class BonusHintPanel extends JPanel {
    private MainPanel mainPanel;
    private JLabel labelBonusHint;
    private JButton btnStart;

    public BonusHintPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setLayout(null);
        setOpaque(false);

        btnStart = new JButton();
        UIUtils.setButtonGraphics(btnStart,"/Image/Button/startgame.png", 360, 110);
        btnStart.setBounds(970, 650, 360, 110);
        btnStart.addActionListener(e -> {
            ((WordPopcorn)SwingUtilities.getWindowAncestor(this)).showCard("MainPanel");
            AvoidanceGameFrame gameFrame = new AvoidanceGameFrame(mainPanel);
            gameFrame.setVisible(true);
        });

        labelBonusHint = new JLabel();
        ImageIcon iconBonusHint = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/bonushint.png")));
        labelBonusHint.setIcon(UIUtils.resizeImageIcon(iconBonusHint, 1100, 700));
        labelBonusHint.setBounds(150, 35, 1100, 700);

        add(btnStart);
        add(labelBonusHint);
    }
}
