package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Player extends JLabel implements Moveable {
    private final int GAME_WIDTH = 800;
    private final int GAME_HEIGHT = 775;
    private int x;
    private int y;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private final int SPEED = 2;
    private ImageIcon player;

    public Player() {
        initObject();
        initSetting();
    }
    private void initObject() {
        player = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/AvoidanceGame/player.png")));
    }
    private void initSetting() {
        x = 385;
        y = 385;

        left = false;
        right = false;
        up = false;
        down = false;

        setIcon(UIUtils.resizeImageIcon(player, 40, 40));
        setSize(40,40);
        setLocation(x, y);
    }

    @Override
    public void left() {
        left = true;
        new Thread(() -> {
            while (left) {
                if (x > 0) { // 왼쪽 벽에 닿지 않았는지 확인
                    x = x - SPEED;
                }
                setLocation(x, y);
                try {
                    checkItemCollision();
                } catch (IOException | FontFormatException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void right() {
        right = true;
        new Thread(() -> {
            while (right) {
                if (x < GAME_WIDTH - getWidth()) { // 오른쪽 벽에 닿지 않았는지 확인
                    x = x + SPEED;
                }
                setLocation(x, y);
                try {
                    checkItemCollision();
                } catch (IOException | FontFormatException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void up() {
        up = true;
        new Thread(() -> {
            while (up) {
                if (y > 0) { // 상단 벽에 닿지 않았는지 확인
                    y = y - SPEED;
                }
                setLocation(x, y);
                try {
                    checkItemCollision();
                } catch (IOException | FontFormatException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public void down() {
        down = true;
        new Thread(() -> {
            while (down) {
                if (y < GAME_HEIGHT - getHeight()) { // 하단 벽에 닿지 않았는지 확인
                    y = y + SPEED;
                }
                setLocation(x, y);
                try {
                    checkItemCollision();
                } catch (IOException | FontFormatException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    public boolean isLeft() {
        return left;
    }
    public boolean isRight() {
        return right;
    }
    public boolean isUp() {
        return up;
    }
    public boolean isDown() {
        return down;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public void setDown(boolean down) {
        this.down = down;
    }

    private void checkItemCollision() throws IOException, FontFormatException {
        Container parent = this.getParent();
        while (parent != null && !(parent instanceof AvoidanceGameFrame)) {
            parent = parent.getParent(); // 상위 컨테이너로 이동
        }

        if (parent != null) {
            AvoidanceGameFrame gameFrame = (AvoidanceGameFrame) parent;
            Component[] components = gameFrame.getContentPane().getComponents();
            for (Component comp : components) {
                if (comp instanceof Item) {
                    Rectangle playerBounds = this.getBounds();
                    Rectangle itemBounds = comp.getBounds();

                    if (playerBounds.intersects(itemBounds)) {
                        gameFrame.remove(comp);
                        gameFrame.repaint();
                        gameFrame.collectItem();
                        break; // 한 번에 하나의 아이템만 수집
                    }
                }
            }
        }
    }
}