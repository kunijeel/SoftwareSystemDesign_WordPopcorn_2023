package kr.ac.jnu;

import javax.swing.*;
import java.util.Objects;

/**
 * SuccessPanel 클래스는 게임에서 성공했을 때 표시되는 패널을 구현합니다.
 * 이 패널은 성공 메시지를 표시하고, 사용자가 초기 화면으로 돌아갈 수 있는 버튼을 제공합니다.
 *
 * @author Tam Oh
 */
public class SuccessPanel extends JPanel {
    /**
     * '뒤로 가기' 버튼. 사용자가 이 버튼을 클릭하면 초기 화면으로 돌아갑니다.
     */
    private JButton btnBack;

    /**
     * 성공 메시지를 표시하는 레이블.
     * 이 레이블은 성공 상태를 시각적으로 나타내기 위해 사용됩니다.
     */
    private JLabel labelSuccess;

    /**
     * SuccessPanel의 생성자.
     * 패널의 레이아웃을 설정하고, 성공 메시지 및 '뒤로가기' 버튼을 초기화합니다.
     * 버튼에는 초기 화면으로 돌아가는 기능이 추가됩니다.
     */
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