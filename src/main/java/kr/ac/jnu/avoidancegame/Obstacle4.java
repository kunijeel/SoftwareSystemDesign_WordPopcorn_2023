package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.CustomInfoDialog;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Obstacle4 extends Obstacle {
    private AvoidanceGameFrame gameFrame;
    private Timer splitTimer;
    private volatile boolean running = true;
    private Player player;
    private Timer timer;

    public Obstacle4(String imagePath, Player player, AvoidanceGameFrame gameFrame, Timer gameTimer) {
        super(imagePath);
        this.player = player;
        this.gameFrame = gameFrame;
        this.timer = gameTimer;

        initMovement();
        startMoving();
        startSplitTimer();
    }

    private void initMovement() {
        int centerX = GAME_WIDTH / 2;
        int centerY = GAME_HEIGHT / 2;
        double angle = Math.atan2(centerY - y, centerX - x);
        int speed = 3; // 이동 속도
        dx = (int) (speed * Math.cos(angle));
        dy = (int) (speed * Math.sin(angle));
    }

    private void startMoving() {
        new Thread(() -> {
            while (running) {
                try {
                    move();
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (IOException | FontFormatException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void startSplitTimer() {
        splitTimer = new Timer(1000, e -> splitObstacle());
        splitTimer.setRepeats(false);
        splitTimer.start();
    }

    private void splitObstacle() {
        // 장애물을 4개로 분할하고 각각의 방향 설정
        running = false; // 원래 장애물의 이동을 중지
        SwingUtilities.invokeLater(() -> {
            Container parent = getParent();
            if (parent != null) {
                parent.remove(this);
                parent.repaint();

                // 분할된 장애물 생성 및 추가
                for (int i = 0; i < 4; i++) {
                    SmallObstacle smallObstacle = new SmallObstacle(x, y, i, this.imagePath, gameFrame, player, timer);
                    parent.add(smallObstacle);
                    smallObstacle.startMoving(); // 각각의 작은 장애물을 움직임 시작
                }
            }
        });
    }

    @Override
    public void move() throws IOException, FontFormatException {
        x += dx;
        y += dy;
        setLocation(x, y);

        if (isOutOfBounds()) {
            running = false; // 스레드 중지
            SwingUtilities.invokeLater(() -> {
                Container parent = getParent();
                if (parent != null) {
                    parent.remove(this); // 현재 장애물을 부모 컨테이너에서 제거
                    parent.repaint(); // 화면 갱신
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
}