package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.CustomInfoDialog;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Obstacle3 extends Obstacle {
    private AvoidanceGameFrame gameFrame;
    private volatile boolean running = true;
    private Player player;
    private Timer moveTimer;
    private Random random = new Random();
    private Timer timer;

    public Obstacle3(String imagePath, Player player, AvoidanceGameFrame gameFrame, Timer gameTimer) {
        super(imagePath);
        this.player = player;
        this.gameFrame = gameFrame;
        this.timer = gameTimer;

        initMovement();
        startMoving();
        new Thread(this::startMovingThread).start(); // 새로운 스레드 시작
    }

    private void initMovement() {
        int centerX = GAME_WIDTH / 2;
        int centerY = GAME_HEIGHT / 2;
        double angle = Math.atan2(centerY - y, centerX - x);
        int speed = 3; // 이동 속도
        dx = (int) (speed * Math.cos(angle));
        dy = (int) (speed * Math.sin(angle));
    }

    private void startMovingThread() {
        while (running) {
            try {
                move();
                Thread.sleep(10); // 움직임 업데이트 주기
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void startMoving() {
        moveTimer = new Timer(1000, e -> {
            int speed = random.nextInt(4) + 2;
            double angle = random.nextDouble() * 2 * Math.PI;
            dx = (int) (speed * Math.cos(angle));
            dy = (int) (speed * Math.sin(angle));
        });
        moveTimer.start();
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
