package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.CustomInfoDialog;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * Obstacle3 클래스는 무작위 방향으로 움직이는 장애물을 구현합니다.
 * 이 클래스는 Obstacle 클래스를 상속받습니다.
 *
 * @author Tam Oh
 */
public class Obstacle3 extends Obstacle {
    /**
     * 게임의 메인 프레임을 참조합니다.
     */
    private AvoidanceGameFrame gameFrame;

    /**
     * 장애물의 이동 상태를 관리하는 변수입니다.
     */
    private volatile boolean running = true;

    /**
     * 게임에서 플레이어 객체를 참조합니다.
     */
    private Player player;

    /**
     * 장애물의 이동을 제어하는 타이머입니다.
     */
    private Timer moveTimer;

    /**
     * 장애물의 이동 방향을 무작위로 결정하는 데 사용됩니다.
     */
    private Random random = new Random();

    /**
     * 게임 타이머를 참조합니다.
     */
    private Timer timer;

    /**
     * Obstacle3 객체를 생성합니다.
     *
     * @param imagePath 장애물 이미지의 경로
     * @param player 게임의 플레이어 객체
     * @param gameFrame 게임의 메인 프레임
     * @param gameTimer 게임의 타이머 객체
     */
    public Obstacle3(String imagePath, Player player, AvoidanceGameFrame gameFrame, Timer gameTimer) {
        super(imagePath);
        this.player = player;
        this.gameFrame = gameFrame;
        this.timer = gameTimer;

        initMovement();
        startMoving();
        new Thread(this::startMovingThread).start(); // 새로운 스레드 시작
    }

    /**
     * 장애물의 초기 이동 방향을 설정합니다.
     */
    private void initMovement() {
        int centerX = GAME_WIDTH / 2;
        int centerY = GAME_HEIGHT / 2;
        double angle = Math.atan2(centerY - y, centerX - x);
        int speed = 2; // 이동 속도
        dx = (int) (speed * Math.cos(angle));
        dy = (int) (speed * Math.sin(angle));
    }

    /**
     * 장애물의 움직임을 별도의 스레드에서 처리합니다.
     */
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

    /**
     * 장애물의 이동 방향을 주기적으로 변경합니다.
     */
    private void startMoving() {
        moveTimer = new Timer(1000, e -> {
            int speed = random.nextInt(3) + 2;
            double angle = random.nextDouble() * 2 * Math.PI;
            dx = (int) (speed * Math.cos(angle));
            dy = (int) (speed * Math.sin(angle));
        });
        moveTimer.start();
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