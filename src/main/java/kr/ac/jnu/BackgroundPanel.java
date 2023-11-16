package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * 배경 이미지를 위한 JPanel을 구현하는 클래스입니다.
 * 사용자 인터페이스에 배경 이미지를 표시하는 용도로 사용됩니다.
 *
 * @author Tam Oh
 */
public class BackgroundPanel extends JPanel {
    /**
     * 패널의 배경으로 사용될 이미지입니다.
     * 이 이미지는 클래스의 생성자에서 로드되고, 스케일링되어 저장됩니다.
     */
    private final Image backgroundImage;

    /**
     * BackgroundPanel의 생성자입니다.
     * 이 생성자는 '/Image/background.jpg' 경로의 이미지 파일을 배경 이미지로 로드합니다.
     */
    public BackgroundPanel() {
        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/background.jpg"))).getImage().getScaledInstance(1400, 775, Image.SCALE_SMOOTH);
    }

    /**
     * 컴포넌트의 그래픽을 그립니다.
     * 이 메소드는 패널 전체에 배경 이미지를 그려 넣습니다.
     *
     * @param g Graphics 객체, 컴포넌트에 그래픽을 그리는 데 사용됩니다.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}