package kr.ac.jnu;

import javax.swing.*;
import java.util.Objects;

public class SuccessPanel extends JPanel {
    public SuccessPanel() {
        setLayout(null);
        setOpaque(false);

        JButton btnBack = new JButton();
        UIUtils.setButtonGraphics(btnBack, "/Image/Button/back.png", 330, 95);
        btnBack.setBounds(990, 655, 330, 90);
        btnBack.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            wordPopcorn.showCard("InitialPanel"); // InitialPanel로 이동
        });

        JLabel labelSuccess = new JLabel();
        ImageIcon iconSuccess = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/success.png")));
        labelSuccess.setIcon(UIUtils.resizeImageIcon(iconSuccess, 1050, 700));
        labelSuccess.setBounds(175, 80, 1050, 700); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        add(btnBack);
        add(labelSuccess);
    }
}