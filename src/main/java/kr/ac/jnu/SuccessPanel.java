package kr.ac.jnu;

import javax.swing.*;
import java.util.Objects;

public class SuccessPanel extends JPanel {
    private JButton btnBack;
    private JLabel labelSuccess;
    public SuccessPanel() {
        setLayout(null);
        setOpaque(false);

        btnBack = new JButton();
        UIUtils.setButtonGraphics(btnBack, "/Image/Button/back.png", 330, 95);
        btnBack.setBounds(990, 655, 330, 90);
        btnBack.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);

            HintPanel hintPanel = (HintPanel) wordPopcorn.getCardPanel().getComponent(4);
            MainPanel mainPanel = (MainPanel) wordPopcorn.getCardPanel().getComponent(3);

            hintPanel.resetPanel();
            mainPanel.resetPanel();
            mainPanel.updateRoundLabel();

            wordPopcorn.showCard("InitialPanel");
        });

        labelSuccess = new JLabel();
        ImageIcon iconSuccess = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/success.png")));
        labelSuccess.setIcon(UIUtils.resizeImageIcon(iconSuccess, 1050, 700));
        labelSuccess.setBounds(175, 80, 1050, 700); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        add(btnBack);
        add(labelSuccess);
    }
}