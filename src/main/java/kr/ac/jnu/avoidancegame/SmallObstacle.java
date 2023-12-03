package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.CustomInfoDialog;
import kr.ac.jnu.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class SmallObstacle extends Obstacle {
    private int localX, localY;
    private int localDx, localDy;
    private final AvoidanceGameFrame gameFrame;
    private final Timer timer;
    private final Player player;
    private volatile boolean running = true;

    public SmallObstacle(int x, int y, int direction, String imagePath, AvoidanceGameFrame gameFrame, Player player, Timer timer) {
        super(imagePath);
        this.gameFrame = gameFrame;
        this.player = player;
        this.timer = timer;
        this.localX = x;
        this.localY = y;
        setIcon(UIUtils.resizeImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))), 15, 15));
        setSize(15, 15);
        setLocation(localX, localY);
        initMovement(direction);
    }

    private void initMovement(int direction) {
        int speed = 3;
        switch (direction) {
            case 0: // 북
                localDx = 0;
                localDy = -speed;
                break;
            case 1: // 동
                localDx = speed;
                localDy = 0;
                break;
            case 2: // 남
                localDx = 0;
                localDy = speed;
                break;
            case 3: // 서
                localDx = -speed;
                localDy = 0;
                break;
        }
    }

    @Override
    public void move() throws IOException, FontFormatException {
        localX += localDx;
        localY += localDy;
        SwingUtilities.invokeLater(() -> setLocation(localX, localY));

        if (isOutOfBounds(localX, localY)) {
            running = false;
            SwingUtilities.invokeLater(() -> {
                Container parent = getParent();
                if (parent != null) {
                    parent.remove(this);
                    parent.repaint();
                }
            });
        }

        if (!gameFrame.isGameOver() && getBounds().intersects(player.getBounds())) {
            timer.stop();
            gameFrame.setGameOver();
            CustomInfoDialog.showInfoDialog(gameFrame, "알림", "Game Over!", 40f, 400, 200);
            gameFrame.dispose();
        }
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x > GAME_WIDTH || y < 0 || y > GAME_HEIGHT;
    }

    public void startMoving() {
        new Thread(() -> {
            while (running) {
                try {
                    move();
                    Thread.sleep(10);
                } catch (InterruptedException | IOException | FontFormatException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}