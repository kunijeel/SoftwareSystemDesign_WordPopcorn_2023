package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

/**
 * 게임 내에서 수집 가능한 아이템을 나타내는 클래스입니다.
 * 아이템은 화면에 임의의 위치에 나타나며 일정 시간 후 사라집니다.
 *
 * @author Tam Oh
 */
public class Item extends JLabel {
    /**
     * 아이템의 이미지를 나타내는 아이콘입니다.
     * 이 아이콘은 게임 화면에 아이템을 표시할 때 사용됩니다.
     */
    private ImageIcon itemIcon;

    /**
     * 아이템이 화면에 나타난 후 일정 시간이 지나면 사라지게 하는 타이머입니다.
     * 이 타이머는 아이템이 일정 시간 동안만 화면에 표시되도록 관리합니다.
     */
    private Timer disappearTimer;

    /**
     * Item 객체를 생성하고 초기화하는 생성자입니다.
     */
    public Item() {
        initObject();
        initSetting();
        startDisappearingTimer();
    }

    /**
     * 아이템의 아이콘을 초기화하는 메소드입니다.
     */
    private void initObject() {
        itemIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/AvoidanceGame/item.png")));
        setIcon(UIUtils.resizeImageIcon(itemIcon, 60, 60));
    }

    /**
     * 아이템의 위치와 크기를 설정하는 메소드입니다.
     * 아이템은 화면 내 임의의 위치에 배치됩니다.
     */
    private void initSetting() {
        Random rand = new Random();
        int x = rand.nextInt(740);
        int y = rand.nextInt(715);
        setLocation(x, y);
        setSize(60, 60);
    }

    /**
     * 아이템이 일정 시간 후 사라지도록 하는 타이머를 시작하는 메소드입니다.
     * 타이머는 설정된 시간(2초)가 지나면 아이템을 화면에서 제거합니다.
     */
    private void startDisappearingTimer() {
        disappearTimer = new Timer(2000, e -> {
            Container parent = getParent();
            if (parent != null) {
                parent.remove(this);
                parent.repaint();
            }
        });
        disappearTimer.setRepeats(false); // 타이머가 한 번만 실행되도록 설정
        disappearTimer.start();
    }
}