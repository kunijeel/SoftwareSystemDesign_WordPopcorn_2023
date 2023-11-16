package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * UIUtils 클래스는 사용자 인터페이스 관련 유틸리티 메소드들을 제공합니다.
 * 이 클래스는 버튼의 그래픽 설정과 이미지 크기 조정 기능을 포함합니다.
 *
 * @author Tam Oh
 */
public class UIUtils {
    /**
     * JButton에 이미지 아이콘을 설정하고 버튼의 스타일을 설정하는 메소드.
     * 주어진 경로에서 이미지를 로드하고, 지정된 크기로 조정한 다음, 버튼에 아이콘으로 설정합니다.
     *
     * @param button 이미지 아이콘이 설정될 JButton.
     * @param imagePath 이미지의 경로. 클래스 패스 리소스 경로여야 합니다.
     * @param width 버튼에 설정할 이미지의 너비.
     * @param height 버튼에 설정할 이미지의 높이.
     */
    public static void setButtonGraphics(JButton button, String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(UIUtils.class.getResource(imagePath)));
        Image newimg = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);

        button.setIcon(icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    /**
     * ImageIcon의 크기를 조정하는 메소드.
     * 주어진 ImageIcon을 지정된 크기로 조정하여 반환합니다.
     *
     * @param icon 크기를 조정할 ImageIcon.
     * @param width 조정된 이미지의 너비.
     * @param height 조정된 이미지의 높이.
     * @return 크기가 조정된 새 ImageIcon 객체.
     */
    public static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image resizedImage = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}