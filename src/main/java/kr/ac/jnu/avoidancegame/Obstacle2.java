package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.CustomInfoDialog;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 이 클래스는 특정한 유형의 장애물을 나타내며, Obstacle 클래스를 확장합니다.
 * 장애물은 중앙을 향해 빠르게 이동하며, 플레이어와 충돌 시 게임이 종료됩니다.
 *
 * @author Tam Oh
 */
public class Obstacle2 extends Obstacle {
    /**
     * 게임에서 플레이어의 객체를 참조합니다.
     */
    private Player player;

    /**
     * 게임의 메인 프레임을 참조합니다.
     */
    private AvoidanceGameFrame gameFrame;

    /**
     * 장애물의 이동을 제어하는 타이머입니다.
     */
    private Timer timer;

    /**
     * 장애물의 이동 상태를 관리하는 변수입니다.
     */
    private volatile boolean running = true;

    /**
     * Obstacle2 객체를 생성합니다.
     *
     * @param imagePath 장애물 이미지의 경로
     * @param player 게임의 플레이어 객체
     * @param gameFrame 게임의 메인 프레임
     * @param timer 게임의 타이머 객체
     */
    public Obstacle2(String imagePath, Player player,AvoidanceGameFrame gameFrame, Timer timer) {
        super(imagePath);
        this.player = player;
        this.timer = timer;
        this.gameFrame = gameFrame;
        initMovement();
        startMoving();
    }

    /**
     * 장애물의 이동을 시작합니다. 별도의 스레드에서 실행됩니다.
     */
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

    /**
     * 장애물의 초기 이동 방향을 설정합니다.
     */
    private void initMovement() {
        int centerX = GAME_WIDTH / 2;
        int centerY = GAME_HEIGHT / 2;
        double angle = Math.atan2(centerY - y, centerX - x);
        int speed = 6; // 이동 속도
        dx = (int) (speed * Math.cos(angle));
        dy = (int) (speed * Math.sin(angle));
    }

    /**
     * 장애물의 이동을 처리하고, 게임 오버 상태를 확인합니다.
     *
     * @throws IOException 파일 입출력 예외
     * @throws FontFormatException 폰트 형식 예외
     */
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