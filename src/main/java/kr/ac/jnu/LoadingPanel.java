package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LoadingPanel extends JPanel implements ActionListener {

    private final Image image;
    private final Timer timer;
    private float alpha = 0f; // 초기 투명도

    public LoadingPanel(String imagePath) {
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))).getImage();
        // 6초 동안 애니메이션을 완료하기 위해 필요한 계산을 수행합니다.
        // 여기서는 타이머 이벤트 간격을 100ms로, 총 60회 이벤트가 발생하도록 설정합니다.
        timer = new Timer(100, this); // 100ms 간격으로 이벤트 처리
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // 이미지를 1400x775 크기로 그립니다.
        g2d.drawImage(image, 0, 0, 1400, 775, this);

        g2d.dispose();
    }

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