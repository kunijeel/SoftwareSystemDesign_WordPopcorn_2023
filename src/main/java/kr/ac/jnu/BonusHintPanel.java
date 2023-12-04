package kr.ac.jnu;

import kr.ac.jnu.avoidancegame.AvoidanceGameFrame;

import javax.swing.*;
import java.util.Objects;

/**
 * 이 클래스는 게임 내에서 보너스 힌트를 안내하는 패널을 구현합니다.
 * 사용자가 보너스 힌트 버튼을 누를 때 나타나는 패널로, 보너스 힌트 정보를 제공합니다.
 *
 * @author Tam Oh
 */
public class BonusHintPanel extends JPanel {
    /**
     * 메인 패널에 대한 참조입니다. 이를 통해 다른 패널과 상호작용이 가능합니다.
     */
    private MainPanel mainPanel;

    /**
     * 보너스 힌트를 표시하는 레이블입니다.
     */
    private JLabel labelBonusHint;

    /**
     * 시작 버튼입니다. 이 버튼을 클릭하면 게임이 시작됩니다.
     */
    private JButton btnStart;

    /**
     * BonusHintPanel의 생성자입니다.
     * 주요 UI 컴포넌트를 초기화하고 이벤트 리스너를 설정합니다.
     *
     * @param mainPanel 메인 패널에 대한 참조
     */
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
