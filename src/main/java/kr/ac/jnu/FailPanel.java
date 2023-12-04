package kr.ac.jnu;

import javax.swing.*;
import java.util.Objects;

/**
 * 사용자가 실패했을 때 보여주는 패널을 구현하는 클래스입니다.
 * 이 패널은 실패를 나타내는 이미지와 함께 힌트 선택 또는 이전 화면으로 돌아가는 기능을 제공합니다.
 *
 * @author Tam Oh
 */
public class FailPanel extends JPanel {
    /**
     * 사용자가 힌트를 선택할 수 있도록 하는 버튼입니다.
     */
    private JButton btnSelectHint;

    /**
     * 사용자가 이전 화면으로 돌아갈 수 있도록 하는 버튼입니다.
     */
    private JButton btnBack;

    /**
     * 실패 상태를 나타내는 라벨입니다.
     * 이 라벨은 실패 이미지를 표시하는 데 사용됩니다.
     */
    private JLabel labelFail;

    /**
     * FailPanel의 생성자입니다.
     * 이 생성자는 패널 구성요소를 초기화하고, 필요한 이벤트 리스너를 설정합니다.
     */
    public FailPanel() {
        setLayout(null);
        setOpaque(false);

        btnBack = new JButton();
        UIUtils.setButtonGraphics(btnBack,"/Image/Button/back.png", 330, 95);
        btnBack.setBounds(990, 655, 330, 95);
        btnBack.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);

            HintPanel hintPanel = (HintPanel) wordPopcorn.getCardPanel().getComponent(4);
            MainPanel mainPanel = (MainPanel) wordPopcorn.getCardPanel().getComponent(3);

            hintPanel.resetPanel();
            mainPanel.resetPanel();
            mainPanel.updateRoundLabel();

            wordPopcorn.showCard("InitialPanel"); // InitialPanel로 이동
        });

        btnSelectHint = new JButton();
        UIUtils.setButtonGraphics(btnSelectHint,"/Image/Button/selecthint.png", 330, 95);
        btnSelectHint.setBounds(990, 655, 330, 95);
        btnSelectHint.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            wordPopcorn.showCard("HintPanel"); // InitialPanel로 이동
        });

        labelFail = new JLabel();
        ImageIcon iconFail = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Sign/fail.png")));
        labelFail.setIcon(UIUtils.resizeImageIcon(iconFail, 1050, 700));
        labelFail.setBounds(175, 80, 1050, 700); // x, y 위치와 너비, 높이 설정 (크기 조정된 값을 사용)

        add(btnBack);
        add(btnSelectHint);
        add(labelFail);
    }

    /**
     * 라운드의 상태에 따라 패널의 버튼을 업데이트합니다.
     * 추가 라운드가 있는 경우 힌트 선택 버튼을, 없는 경우 돌아가기 버튼을 표시합니다.
     *
     * @param moreRoundsAvailable 추가 라운드의 유무를 나타내는 값
     */
    public void updateForRoundStatus(boolean moreRoundsAvailable) {
        btnSelectHint.setVisible(moreRoundsAvailable); // 추가 라운드가 있으면 힌트 선택 버튼을 보여줌
        btnBack.setVisible(!moreRoundsAvailable); // 추가 라운드가 없으면 돌아가기 버튼을 보여줌
    }
}