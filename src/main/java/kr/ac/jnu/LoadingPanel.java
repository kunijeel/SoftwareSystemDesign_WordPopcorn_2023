package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * 이 클래스는 게임 로딩 중에 애니메이션을 표시하는 패널을 구현합니다.
 * 특정 이미지를 점차적으로 투명하게 만들면서 표시하는 애니메이션 기능을 제공합니다.
 *
 * @author Tam Oh
 */
public class LoadingPanel extends JPanel implements ActionListener {
    /**
     * 로딩 중에 표시될 이미지입니다.
     */
    private final Image image;

    /**
     * 애니메이션 효과를 위한 타이머입니다.
     */
    private final Timer timer;

    /**
     * 현재 이미지의 투명도를 나타내는 변수입니다.
     */
    private float alpha = 0f; // 초기 투명도

    /**
     * LoadingPanel의 생성자입니다.
     * 지정된 이미지 경로로부터 이미지를 로드하고, 애니메이션 효과를 위한 타이머를 설정합니다.
     *
     * @param imagePath 로딩 이미지의 경로
     */
    public LoadingPanel(String imagePath) {
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))).getImage();
        // 6초 동안 애니메이션을 완료하기 위해 필요한 계산을 수행합니다.
        // 여기서는 타이머 이벤트 간격을 100ms로, 총 60회 이벤트가 발생하도록 설정합니다.
        timer = new Timer(100, this); // 100ms 간격으로 이벤트 처리
        timer.start();
    }

    /**
     * 패널의 그래픽 컴포넌트를 그리는 메서드입니다.
     * 이 메서드는 투명도가 적용된 이미지를 패널에 그립니다.
     *
     * @param g 그래픽스 객체
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // 이미지를 1400x775 크기로 그립니다.
        g2d.drawImage(image, 0, 0, 1400, 775, this);

        g2d.dispose();
    }

    /**
     * 타이머 이벤트가 발생할 때 호출되는 메서드입니다.
     * 이 메서드는 이미지의 투명도를 점차적으로 증가시키고, 최대값에 도달하면 타이머를 정지합니다.
     *
     * @param e 액션 이벤트 객체
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        alpha += 1.0f / 60; // 60단계로 나누어 투명도 증가 (총 6초)
        if (alpha > 1f) {
            alpha = 1f;
            timer.stop();
        }
        repaint();
    }
}