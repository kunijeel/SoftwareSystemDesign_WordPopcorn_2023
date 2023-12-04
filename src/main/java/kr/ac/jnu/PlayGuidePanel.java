package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * PlayGuidePanel 클래스는 게임 가이드 이미지를 표시하는 데 사용되는 패널을 구현합니다.
 * 이 패널은 주어진 이미지 경로에서 이미지를 로드하고, 해당 이미지를 패널에 그립니다.
 * 사용자는 생성자를 통해 이미지 경로와 이미지의 크기를 지정할 수 있습니다.
 *
 * @author Tam Oh
 */
public class PlayGuidePanel extends JPanel {
    /**
     * 패널에 표시할 이미지.
     * 이미지는 생성자에서 지정된 경로에서 로드되며, 사용자가 지정한 크기로 조절됩니다.
     */

    private Image imgPlayGuide;

    /**
     * PlayGuidePanel 생성자.
     * 주어진 이미지 경로를 사용하여 이미지를 로드하고, 지정된 크기로 이미지를 조절합니다.
     *
     * @param imagePath 이미지가 있는 경로. 클래스 패스 리소스 경로여야 합니다.
     * @param width     이미지의 너비.
     * @param height    이미지의 높이.
     */
    public PlayGuidePanel(String imagePath, int width, int height) {
        // 이미지 로드 및 크기 조절
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        ImageIcon resizedIcon = resizeImageIcon(originalIcon, width, height);
        imgPlayGuide = resizedIcon.getImage();

        // 패널의 선호 크기 설정
        setPreferredSize(new Dimension(width, height));
    }

    /**
     * 패널에 이미지를 그리는 메소드.
     * 이 메소드는 슈퍼클래스의 paintComponent를 호출한 후, 패널에 이미지를 그립니다.
     *
     * @param g 그래픽스 컨텍스트 객체. 이 객체를 사용하여 패널에 그리기 작업을 수행합니다.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 조절된 크기의 이미지 그리기
        g.drawImage(imgPlayGuide, 0, 0, this);
    }

    /**
     * 주어진 ImageIcon을 지정된 크기로 조절하는 유틸리티 메소드.
     *
     * @param icon   원본 ImageIcon 객체.
     * @param width  조절될 이미지의 너비.
     * @param height 조절될 이미지의 높이.
     * @return 조절된 크기의 ImageIcon 객체.
     */
    public static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
