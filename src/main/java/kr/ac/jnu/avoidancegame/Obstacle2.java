package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.CustomInfoDialog;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Obstacle2 extends Obstacle {
    private Player player;
    private AvoidanceGameFrame gameFrame;
    private Timer timer;
    private volatile boolean running = true; // 스레드 실행 상태를 관리하는 변수
    public Obstacle2(String imagePath, Player player,AvoidanceGameFrame gameFrame, Timer timer) {
        super(imagePath);
        this.player = player;
        this.timer = timer;
        this.gameFrame = gameFrame;
        initMovement();
        startMoving();
    }
    private void startMoving() {
        new Thread(() -> {
            while (running) {
                try {
                    move();
                } catch (IOException | FontFormatException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(10); // 이동 속도 조절
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // 스레드 인터럽트 상태 설정
                }
            }
        }).start();
    }
    private void initMovement() {
        int centerX = GAME_WIDTH / 2;
        int centerY = GAME_HEIGHT / 2;
        double angle = Math.atan2(centerY - y, centerX - x);
        int speed = 6; // 이동 속도
        dx = (int) (speed * Math.cos(angle));
        dy = (int) (speed * Math.sin(angle));
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