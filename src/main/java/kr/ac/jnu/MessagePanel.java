package kr.ac.jnu;

import javax.swing.*;
import java.util.Objects;

/**
 * 메시지를 표시하는 패널을 구현하는 클래스입니다.
 * 이 패널은 주어진 이미지를 사용하여 메시지를 표시하며, 깜빡이는 효과를 제공합니다.
 *
 * @author Tam Oh
 */
public class MessagePanel extends JPanel {
    /**
     * 레이블의 가시성을 깜빡이게 하는 타이머입니다.
     */
    private Timer blinkTimer;

    /**
     * 메시지를 표시하는 레이블입니다.
     */
    private JLabel labelMessage;

    /**
     * MessagePanel의 생성자입니다.
     * 이미지의 위치, 크기를 지정하여 패널을 초기화합니다.
     *
     * @param imagePath 이미지의 경로
     * @param width 이미지의 너비
     * @param height 이미지의 높이
     * @param x 이미지의 x 좌표
     * @param y 이미지의 y 좌표
     */
    public MessagePanel(String imagePath, int width, int height, int x, int y) {
        setLayout(null);
        setOpaque(false);

        labelMessage = new JLabel();
        labelMessage.setIcon(UIUtils.resizeImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))), width, height));
        labelMessage.setBounds(x, y, width, height); // 위치 설정
        add(labelMessage);

        // 깜빡이는 타이머 설정
        blinkTimer = new Timer(500, e -> labelMessage.setVisible(!labelMessage.isVisible()));
        blinkTimer.start();
    }

    /**
     * 패널의 가시성을 설정합니다.
     * 패널이 보이지 않게 설정되면 깜빡이는 타이머가 중지되고, 보이게 설정되면 타이머가 시작됩니다.
     *
     * @param aFlag 패널을 보이게 할지 여부
     */
    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (!aFlag) {
            blinkTimer.stop();
        } else {
            blinkTimer.start();
        }
    }
}