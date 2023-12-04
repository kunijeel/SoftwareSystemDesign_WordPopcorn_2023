package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.CustomInfoDialog;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Obstacle4 클래스는 분할되어 4개의 작은 장애물로 나뉘는 장애물을 구현합니다.
 * 이 클래스는 Obstacle 클래스를 상속받습니다.
 *
 * @author Tam Oh
 */
public class Obstacle4 extends Obstacle {
    /**
     * 게임의 메인 프레임을 참조합니다.
     */
    private AvoidanceGameFrame gameFrame;

    /**
     * 장애물 분할을 제어하는 타이머입니다.
     */
    private Timer splitTimer;

    /**
     * 장애물의 이동 상태를 관리하는 변수입니다.
     */
    private volatile boolean running = true;

    /**
     * 게임에서 플레이어 객체를 참조합니다.
     */
    private Player player;

    /**
     * 게임 타이머를 참조합니다.
     */
    private Timer timer;

    /**
     * Obstacle4 객체를 생성합니다.
     *
     * @param imagePath 장애물 이미지의 경로
     * @param player 게임의 플레이어 객체
     * @param gameFrame 게임의 메인 프레임
     * @param gameTimer 게임의 타이머 객체
     */
    public Obstacle4(String imagePath, Player player, AvoidanceGameFrame gameFrame, Timer gameTimer) {
        super(imagePath);
        this.player = player;
        this.gameFrame = gameFrame;
        this.timer = gameTimer;

        initMovement();
        startMoving();
        startSplitTimer();
    }

    /**
     * 장애물의 초기 이동 방향을 설정합니다.
     */
    private void initMovement() {
        int centerX = GAME_WIDTH / 2;
        int centerY = GAME_HEIGHT / 2;
        double angle = Math.atan2(centerY - y, centerX - x);
        int speed = 3; // 이동 속도
        dx = (int) (speed * Math.cos(angle));
        dy = (int) (speed * Math.sin(angle));
    }

    /**
     * 장애물의 움직임을 별도의 스레드에서 처리합니다.
     */
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

    /**
     * 일정 시간 후 장애물을 4개의 작은 장애물로 분할하는 타이머를 시작합니다.
     */
    private void startSplitTimer() {
        splitTimer = new Timer(1000, e -> splitObstacle());
        splitTimer.setRepeats(false);
        splitTimer.start();
    }

    /**
     * 장애물을 분할하고 새로운 작은 장애물을 생성합니다.
     */
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