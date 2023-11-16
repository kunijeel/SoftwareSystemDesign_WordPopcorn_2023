package kr.ac.jnu;

import javax.swing.*;
import java.util.Objects;

/**
 * SongSelectPanel 클래스는 사용자가 노래를 선택할 수 있는 패널을 구현합니다.
 * 이 패널은 앨범 커버 이미지와 노래에 대한 간략한 정보를 제공하여 사용자가 노래를 선택하고 정보를 볼 수 있도록 합니다.
 *
 * @author Tam Oh
 */
public class SongSelectPanel extends JPanel {
    /**
     * 왼쪽에 위치한 앨범 커버 레이블.
     * 이 레이블은 사용자가 앨범을 선택할 때 이전 앨범을 표시합니다.
     */
    private JLabel labelLeft;

    /**
     * 중앙에 위치한 큰 앨범 커버 레이블.
     * 이 레이블은 앨범의 커버를 크게 표시합니다.
     */
    private JLabel labelCenter;

    /**
     * 오른쪽에 위치한 앨범 커버 레이블.
     * 이 레이블은 사용자가 앨범을 선택할 때 다음 앨범을 표시합니다.
     */
    private JLabel labelRight;

    /**
     * 노래의 제목, 가수, 레벨을 표시하는 레이블.
     */
    private JLabel labelPreview;

    /**
     * 왼쪽으로 이동하는 버튼.
     * 이 버튼은 사용자가 이전 앨범을 볼 수 있도록 합니다.
     */
    private JButton btnLeft;

    /**
     * 오른쪽으로 이동하는 버튼.
     * 이 버튼은 사용자가 다음 앨범을 볼 수 있도록 합니다.
     */
    private JButton btnRight;

    /**
     * 노래 정보를 보여주는 버튼.
     * 이 버튼을 클릭하면 현재 선택된 노래의 상세 정보를 표시합니다.
     */
    private JButton btnShowSongInfo;

    /**
     * 앨범 이미지를 저장하는 배열.
     * 이 배열은 각 앨범 커버 이미지를 ImageIcon 객체로 저장합니다.
     */
    private final ImageIcon[] albumImages;

    /**
     * 앨범 요약 이미지의 경로를 저장하는 배열.
     * 각 앨범에 대한 요약 이미지의 경로가 저장됩니다.
     */
    private final String[] albumPreviewPaths;

    /**
     * 현재 중앙에 위치한 앨범 이미지의 인덱스.
     * 이 인덱스는 사용자가 앨범을 넘길 때마다 업데이트됩니다.
     */
    private int currentIndex = 0;

    /**
     * 큰 이미지의 너비를 정의하는 상수.
     * 중앙에 위치한 앨범 이미지의 너비에 사용됩니다.
     */
    private final int largeWidth = 600;

    /**
     * 큰 이미지의 높이를 정의하는 상수.
     * 중앙에 위치한 앨범 이미지의 높이에 사용됩니다.
     */
    private final int largeHeight = 600;

    /**
     * 작은 이미지의 너비를 정의하는 상수.
     * 좌우에 위치한 앨범 이미지의 너비에 사용됩니다.
     */
    private final int smallWidth = 400;

    /**
     * 작은 이미지의 높이를 정의하는 상수.
     * 좌우에 위치한 앨범 이미지의 높이에 사용됩니다.
     */
    private final int smallHeight = 400;

    /**
     * SongSelectPanel의 생성자.
     * 패널의 레이아웃, 앨범 이미지, 요약 이미지 경로를 초기화하고, 레이블과 버튼을 설정합니다.
     * 또한 버튼의 이벤트 리스너를 추가하여 사용자가 앨범을 선택하고, 노래 정보를 볼 수 있도록 합니다.
     */
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
        labelLeft.setBounds(50, 65, smallWidth, smallHeight);

        labelRight = new JLabel();
        labelRight.setBounds(950, 65, smallWidth, smallHeight);

        labelCenter = new JLabel();
        labelCenter.setBounds(400, 55, largeWidth, largeHeight);

        // 레이블 설정
        labelPreview = new JLabel();
        labelPreview.setBounds(460, 550, 480, 200);

        // 버튼 초기화 및 위치, 크기 설정
        btnLeft = new JButton();
        UIUtils.setButtonGraphics(btnLeft, "/Image/Button/left.png", 240, 70); // 버튼에 150x100 크기의 아이콘을 설정합니다.
        btnLeft.setBounds(210, 530, 240, 70); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정

        btnRight = new JButton();
        UIUtils.setButtonGraphics(btnRight, "/Image/Button/right.png", 240, 70); // 버튼에 150x100 크기의 아이콘을 설정합니다.
        btnRight.setBounds(950, 530, 240, 70); // 이제 아이콘이 설정된 후에 버튼의 위치와 크기를 지정

        btnShowSongInfo = new JButton();
        UIUtils.setButtonGraphics(btnShowSongInfo, "/Image/Button/showsonginfo.png", 330, 110);
        btnShowSongInfo.setBounds(980, 640, 330, 110);

        btnLeft.addActionListener(e -> moveLeft());

        btnRight.addActionListener(e -> moveRight());

        btnShowSongInfo.addActionListener(e -> {
            int underscoreIndex = albumPreviewPaths[currentIndex].lastIndexOf("_") + 1;
            int dotIndex = albumPreviewPaths[currentIndex].lastIndexOf(".");
            String songName = albumPreviewPaths[currentIndex].substring(underscoreIndex, dotIndex);

            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            ((SongInfoPanel) wordPopcorn.getCardPanel().getComponent(2)).setSongInfo(songName);
            wordPopcorn.setCurrentSongName(songName);
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

    /**
     * 사용자가 왼쪽 버튼을 클릭했을 때 호출되는 메소드.
     * 현재 선택된 앨범 인덱스를 감소시켜 이전 앨범을 중앙에 표시합니다.
     * 이와 함께 좌우의 앨범 이미지도 갱신합니다.
     */
    private void moveLeft() {
        currentIndex = (currentIndex + albumImages.length - 1) % albumImages.length;
        updateLabelImages();
    }

    /**
     * 사용자가 오른쪽 버튼을 클릭했을 때 호출되는 메소드.
     * 현재 선택된 앨범 인덱스를 증가시켜 다음 앨범을 중앙에 표시합니다.
     * 이와 함께 좌우의 앨범 이미지도 갱신합니다.
     */
    private void moveRight() {
        currentIndex = (currentIndex + 1) % albumImages.length;
        updateLabelImages();
    }

    /**
     * 현재 선택된 앨범 인덱스에 따라 레이블에 앨범 이미지를 업데이트하는 메소드.
     * 중앙 레이블에는 큰 이미지를, 좌우 레이블에는 작은 이미지를 표시합니다.
     * 또한, 요약 이미지도 갱신합니다.
     */
    private void updateLabelImages() {
        labelLeft.setIcon(UIUtils.resizeImageIcon(albumImages[(currentIndex + albumImages.length - 1) % albumImages.length], smallWidth, smallHeight));
        labelCenter.setIcon(UIUtils.resizeImageIcon(albumImages[currentIndex], largeWidth, largeHeight)); // 중앙 이미지만 크게
        labelRight.setIcon(UIUtils.resizeImageIcon(albumImages[(currentIndex + 1) % albumImages.length], smallWidth, smallHeight));
        // 미리보기 레이블 이미지 업데이트
        labelPreview.setIcon(UIUtils.resizeImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(albumPreviewPaths[currentIndex]))), 480, 200));
    }
}