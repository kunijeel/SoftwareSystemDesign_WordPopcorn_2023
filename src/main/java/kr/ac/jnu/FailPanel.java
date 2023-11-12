package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FailPanel extends JPanel {
    private JButton btnSelectHint, btnBack;
    public FailPanel() {
        setLayout(null);
        setOpaque(false);

        btnBack = new JButton();
        UIUtils.setButtonGraphics(btnBack,"/Image/Button/back.png", 330, 95);
        btnBack.setBounds(990, 655, 330, 90);
        btnBack.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            wordPopcorn.showCard("InitialPanel"); // InitialPanel로 이동
        });

        btnSelectHint = new JButton();
        UIUtils.setButtonGraphics(btnSelectHint,"/Image/Button/selecthint.png", 330, 95);
        btnSelectHint.setBounds(990, 655, 330, 90);
        btnSelectHint.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            wordPopcorn.showCard("HintPanel"); // InitialPanel로 이동
        });

        JLabel labelFail = new JLabel();
        ImageIcon iconFail = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/fail.png")));
        labelFail.setIcon(UIUtils.resizeImageIcon(iconFail, 1050, 700));
        labelFail.setBounds(175, 80, 1050, 700); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        add(btnBack);
        add(btnSelectHint);
        add(labelFail);
    }

    public void updateForRoundStatus(boolean moreRoundsAvailable) {
        btnSelectHint.setVisible(moreRoundsAvailable); // 추가 라운드가 있으면 힌트 선택 버튼을 보여줌
        btnBack.setVisible(!moreRoundsAvailable); // 추가 라운드가 없으면 돌아가기 버튼을 보여줌
    }
}