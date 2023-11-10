package kr.ac.jnu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SongSelectPanel extends JPanel {
    private JLabel labelLeft, labelCenter, labelRight, labelPreview; // 세 개의 레이블
    private JButton btnLeft, btnRight, btnShowSongInfo;
    private final ImageIcon[] albumImages; // 앨범 이미지 배열
    private final String[] albumPreviewPaths; // 앨범 미리보기 이미지 경로 배열
    private int currentIndex = 0; // 현재 중앙에 위치한 이미지의 인덱스
    private final int largeWidth = 600; // 큰 이미지의 너비
    private final int largeHeight = 600; // 큰 이미지의 높이
    private final int smallWidth = 400; // 작은 이미지의 너비
    private final int smallHeight = 400; // 작은 이미지의 높이

    public SongSelectPanel() {
        setOpaque(false);
        setLayout(null); // 레이아웃 매니저를 null로 설정

        // 앨범 이미지 로드
        albumImages = new ImageIcon[]{
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Cover/cover_eta.png"))),
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Cover/cover_hypeboy.png"))),
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Cover/cover_zero.png"))),
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Cover/cover_omg.png")))
        };

        // 앨범 미리보기 이미지 경로 배열 초기화
        albumPreviewPaths = new String[]{
                "/Image/Preview/preview_eta.png",
                "/Image/Preview/preview_hypeboy.png",
                "/Image/Preview/preview_zero.png",
                "/Image/Preview/preview_omg.png"
        };

        // 레이블 초기화 및 위치, 크기 설정
        labelLeft = new JLabel();
        labelLeft.setBounds(50, 65, smallWidth, smallHeight); // x, y, width, height

        labelRight = new JLabel();
        labelRight.setBounds(950, 65, smallWidth, smallHeight); // x, y, width, height

        labelCenter = new JLabel();
        labelCenter.setBounds(400, 55, largeWidth, largeHeight); // x, y, width, height

        // 레이블 설정
        labelPreview = new JLabel();
        labelPreview.setBounds(475, 550, 450, 200);

        // 버튼 초기화 및 위치, 크기 설정
        btnLeft = new JButton();
        setButtonGraphics(btnLeft, "/Image/Button/left.png", 230, 150); // 버튼에 150x100 크기의 아이콘을 설정합니다.
        btnLeft.setBounds(250, 450, 230, 150); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정

        btnRight = new JButton();
        setButtonGraphics(btnRight, "/Image/Button/right.png", 230, 150); // 버튼에 150x100 크기의 아이콘을 설정합니다.
        btnRight.setBounds(920, 450, 230, 150); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정

        btnShowSongInfo = new JButton();
        setButtonGraphics(btnShowSongInfo, "/Image/Button/showsonginfo.png", 250, 130);
        btnShowSongInfo.setBounds(1050, 620, 250, 130);

        btnLeft.addActionListener(e -> moveLeft());

        btnRight.addActionListener(e -> moveRight());

        btnShowSongInfo.addActionListener(e -> {
            int underscoreIndex = albumPreviewPaths[currentIndex].lastIndexOf("_") + 1;
            int dotIndex = albumPreviewPaths[currentIndex].lastIndexOf(".");
            String songName = albumPreviewPaths[currentIndex].substring(underscoreIndex, dotIndex);
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(SongSelectPanel.this);
            SongInfoPanel songInfoPanel = (SongInfoPanel) wordPopcorn.getCardPanel().getComponent(2); // 인덱스 2는 SongInfoPanel을 의미합니다.
            songInfoPanel.setSongInfo(songName);
            wordPopcorn.showCard("SongInfoPanel");
        });

        // 컴포넌트들을 패널에 추가
        add(labelPreview);
        add(btnLeft);
        add(btnRight);
        add(labelCenter);
        add(labelLeft);
        add(labelRight);
        add(btnShowSongInfo);

        // 초기 이미지 아이콘을 업데이트
        updateLabelImages();
    }
    // 왼쪽으로 이동
    private void moveLeft() {
        currentIndex = (currentIndex + albumImages.length - 1) % albumImages.length;
        updateLabelImages();
    }
    // 오른쪽으로 이동
    private void moveRight() {
        currentIndex = (currentIndex + 1) % albumImages.length;
        updateLabelImages();
    }
    // 레이블에 이미지 업데이트
    private void updateLabelImages() {
        labelLeft.setIcon(resizeImageIcon(albumImages[(currentIndex + albumImages.length - 1) % albumImages.length], smallWidth, smallHeight));
        labelCenter.setIcon(resizeImageIcon(albumImages[currentIndex], largeWidth, largeHeight)); // 중앙 이미지만 크게
        labelRight.setIcon(resizeImageIcon(albumImages[(currentIndex + 1) % albumImages.length], smallWidth, smallHeight));
        // 미리보기 레이블 이미지 업데이트
        labelPreview.setIcon(resizeImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(albumPreviewPaths[currentIndex]))), 450, 200));
    }
    private void setButtonGraphics(JButton button, String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        Image newimg = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // 넘겨받은 width와 height를 사용하여 크기를 조정합니다.
        icon = new ImageIcon(newimg); // 조정된 이미지로 ImageIcon을 다시 생성합니다.

        button.setIcon(icon);
        button.setBorderPainted(false); // 버튼 경계를 그리지 않음
        button.setContentAreaFilled(false); // 내용 영역 배경을 그리지 않음
        button.setFocusPainted(false); // 선택(포커스)됐을 때 경계를 그리지 않음
        button.setOpaque(false); // 투명하게 설정
    }
    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image resizedImage = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}