package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Item extends JLabel {
    private ImageIcon itemIcon;
    private Timer disappearTimer;

    public Item() {
        initObject();
        initSetting();
        startDisappearingTimer();
    }

    private void initObject() {
        itemIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/AvoidanceGame/item.png")));
        setIcon(UIUtils.resizeImageIcon(itemIcon, 60, 60));
    }

    private void initSetting() {
        Random rand = new Random();
        int x = rand.nextInt(740);
        int y = rand.nextInt(715);
        setLocation(x, y);
        setSize(60, 60);
    }

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