package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * PlayGuidePanel 클래스는 게임 가이드 이미지를 표시하는 데 사용되는 패널을 구현합니다.
 * 이 패널은 주어진 이미지 경로에서 이미지를 로드하고, 해당 이미지를 패널에 그립니다.
 *
 * @author Tam Oh
 */
public class PlayGuidePanel extends JPanel {
    /**
     * 게임 가이드를 표시하는 데 사용되는 이미지.
     * 이 이미지는 생성자에서 지정된 경로에서 로드됩니다.
     */
    private Image imgPlayGuide;

    /**
     * PlayGuidePanel 생성자.
     * 주어진 이미지 경로를 사용하여 이미지를 로드하고 패널의 크기를 이미지 크기에 맞게 설정합니다.
     *
     * @param imagePath 이미지가 있는 경로. 클래스 패스 리소스 경로여야 합니다.
     */
    public PlayGuidePanel(String imagePath) {
        imgPlayGuide = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))).getImage();
        setPreferredSize(new Dimension(imgPlayGuide.getWidth(null), imgPlayGuide.getHeight(null)));
    }

    /**
     * 패널의 컴포넌트를 그리는 메소드.
     * 이 메소드는 패널에 이미지를 그립니다.
     *
     * @param g 그래픽스 컨텍스트 객체. 이 객체를 사용하여 패널에 그리기 작업을 수행합니다.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgPlayGuide, 0, 0, this);
    }
}