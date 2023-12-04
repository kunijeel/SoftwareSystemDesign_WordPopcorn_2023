package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.CustomInfoDialog;
import kr.ac.jnu.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * 이 클래스는 분할된 작은 장애물의 기능을 구현합니다.
 *
 * @author Tam Oh
 */
public class SmallObstacle extends Obstacle {
    /**
     * 장애물의 현재 X 좌표입니다.
     */
    private int localX;

    /**
     * 장애물의 현재 Y 좌표입니다.
     */
    private int localY;
    /**
     * 장애물의 X 방향 이동 속도입니다.
     */
    private int localDx;

    /**
     * 장애물의 Y 방향 이동 속도입니다.
     */
    private int localDy;

    /**
     * 게임 프레임의 참조를 저장합니다.
     */
    private final AvoidanceGameFrame gameFrame;

    /**
     * 게임 타이머의 참조를 저장합니다.
     */
    private final Timer timer;

    /**
     * 플레이어 객체의 참조를 저장합니다.
     */
    private final Player player;

    /**
     * 장애물의 실행 상태를 나타내는 플래그입니다.
     */
    private volatile boolean running = true;

    /**
     * SmallObstacle 생성자.
     *
     * @param x           장애물의 초기 X 좌표
     * @param y           장애물의 초기 Y 좌표
     * @param direction   이동 방향
     * @param imagePath   장애물의 이미지 경로
     * @param gameFrame   게임 프레임의 참조
     * @param player      플레이어 객체
     * @param timer       타이머 객체
     */
    public SmallObstacle(int x, int y, int direction, String imagePath, AvoidanceGameFrame gameFrame, Player player, Timer timer) {
        super(imagePath);
        this.gameFrame = gameFrame;
        this.player = player;
        this.timer = timer;
        this.localX = x;
        this.localY = y;
        setIcon(UIUtils.resizeImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))), 20, 20));
        setSize(20, 20);
        setLocation(localX, localY);
        initMovement(direction);
    }

    /**
     * 장애물의 초기 이동 방향을 설정합니다.
     *
     * @param direction 이동 방향
     */
    private void initMovement(int direction) {
        int speed = 2;
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

    /**
     * 장애물을 움직입니다. 이 메소드는 장애물의 위치를 업데이트하고,
     * 장애물이 화면 밖으로 벗어나거나 플레이어와 충돌하는 경우를 처리합니다.
     *
     * @throws IOException 파일 입출력 예외가 발생할 경우
     * @throws FontFormatException 폰트 형식 예외가 발생할 경우
     */
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

    /**
     * 장애물이 화면 밖으로 벗어났는지 확인합니다.
     *
     * @param x X 좌표
     * @param y Y 좌표
     * @return 화면 밖으로 벗어나면 true, 그렇지 않으면 false
     */
    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x > GAME_WIDTH || y < 0 || y > GAME_HEIGHT;
    }

    /**
     * 장애물을 움직이기 시작하는 메소드입니다. 별도의 스레드에서 실행됩니다.
     */
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