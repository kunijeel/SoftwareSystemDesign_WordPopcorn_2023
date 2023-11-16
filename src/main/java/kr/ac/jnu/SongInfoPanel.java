package kr.ac.jnu;

import javax.swing.*;
import java.util.Objects;

/**
 * SongInfoPanel 클래스는 노래 정보를 표시하는 패널을 구현합니다.
 * 이 패널은 노래 정보 레이블과 시작 버튼을 포함하며, 노래 정보에 따라 동적으로 내용이 업데이트됩니다.
 *
 * @author Tam Oh
 */
public class SongInfoPanel extends JPanel {
    /**
     * 노래 정보를 표시하는 레이블.
     */
    private JLabel labelSongInfo;

    /**
     * 게임 시작 버튼.
     */
    private JButton btnStart;

    /**
     * 이 상수는 songInfoLabel 레이블의 너비를 정의하는 데 사용됩니다.
     */
    private final int imageWidth = 1100;  // 기본 이미지 너비 설정

    /**
     * 이 상수는 songInfoLabel 레이블의 높이를 정의하는 데 사용됩니다.
     */
    private final int imageHeight = 650; // 기본 이미지 높이 설정

    /**
     * SongInfoPanel의 생성자.
     * 패널의 레이아웃을 설정하고, 시작 버튼과 노래 정보 레이블을 초기화합니다.
     */
    public SongInfoPanel() {
        setLayout(null);
        setOpaque(false); // 패널을 투명하게 설정

        btnStart = new JButton();
        UIUtils.setButtonGraphics(btnStart, "/Image/Button/startgame.png", 360, 110);
        btnStart.setBounds(970, 640, 360, 110);
        btnStart.addActionListener(e -> {
            WordPopcorn wordPopcorn = (WordPopcorn) SwingUtilities.getWindowAncestor(this);
            ((MainPanel) wordPopcorn.getCardPanel().getComponent(3)).setMain(wordPopcorn.getCurrentSongName());
            wordPopcorn.showCard("MainPanel");
        });

        labelSongInfo = new JLabel();
        labelSongInfo.setBounds(150, 60, imageWidth, imageHeight); // 레이블 위치와 크기 설정

        add(btnStart);
        add(labelSongInfo); // 레이블을 패널에 추가
    }

    /**
     * 주어진 노래 이름에 해당하는 이미지 아이콘으로 레이블을 업데이트합니다.
     *
     * @param songName 설정할 노래의 이름입니다. 이 이름에 해당하는 이미지 파일이 존재해야 합니다.
     */
    public void setSongInfo(String songName) {
        ImageIcon iconSonginfo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/Songinfo/songinfo_" + songName + ".png")));
        labelSongInfo.setIcon(UIUtils.resizeImageIcon(iconSonginfo, imageWidth, imageHeight));
    }
}