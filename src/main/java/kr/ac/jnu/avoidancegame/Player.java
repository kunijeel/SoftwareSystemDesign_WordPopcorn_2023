package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Player 클래스는 게임의 플레이어를 나타냅니다.
 * 이 클래스는 JLabel을 상속받으며, Moveable 인터페이스를 구현하여 이동 기능을 제공합니다.
 *
 * @author Tam Oh
 */
public class Player extends JLabel implements Moveable {
    /**
     * 게임의 폭을 나타냅니다.
     */
    private final int GAME_WIDTH = 800;

    /**
     * 게임의 높이를 나타냅니다.
     */
    private final int GAME_HEIGHT = 775;

    /**
     * 플레이어의 X좌표를 나타냅니다.
     */
    private int x;

    /**
     * 플레이어의 Y좌표를 나타냅니다.
     */
    private int y;

    /**
     * 왼쪽으로 이동 중임을 나타내는 변수입니다.
     */
    private boolean left;

    /**
     * 오른쪽으로 이동 중임을 나타내는 변수입니다.
     */
    private boolean right;

    /**
     * 위쪽으로 이동 중임을 나타내는 변수입니다.
     */
    private boolean up;

    /**
     * 아래쪽으로 이동 중임을 나타내는 변수입니다.
     */
    private boolean down;

    /**
     * 플레이어의 이동 속도를 나타냅니다.
     */
    private final int SPEED = 2;

    /**
     * 플레이어 이미지 아이콘입니다.
     */
    private ImageIcon player;

    /**
     * Player 객체를 생성하고 초기화합니다.
     */
    public Player() {
        initObject();
        initSetting();
    }

    /**
     * 플레이어의 이미지 아이콘을 초기화합니다.
     */
    private void initObject() {
        player = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/AvoidanceGame/player.png")));
    }

    /**
     * 플레이어의 초기 위치와 상태를 설정합니다.
     */
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

    /**
     * 플레이어가 왼쪽으로 이동합니다.
     * 이 메소드는 새 스레드를 시작하여 플레이어를 연속적으로 왼쪽으로 이동시킵니다.
     */
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

    /**
     * 플레이어가 오른쪽으로 이동합니다.
     * 이 메소드는 새 스레드를 시작하여 플레이어를 연속적으로 오른쪽으로 이동시킵니다.
     */
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

    /**
     * 플레이어가 위로 이동합니다.
     * 이 메소드는 새 스레드를 시작하여 플레이어를 연속적으로 위로 이동시킵니다.
     */
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

    /**
     * 플레이어가 아래로 이동합니다.
     * 이 메소드는 새 스레드를 시작하여 플레이어를 연속적으로 아래로 이동시킵니다.
     */
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

    /**
     * 플레이어가 현재 왼쪽으로 이동 중인지 여부를 반환합니다.
     *
     * @return 왼쪽 이동 중이면 true, 그렇지 않으면 false
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * 플레이어가 현재 오른쪽으로 이동 중인지 여부를 반환합니다.
     *
     * @return 오른쪽 이동 중이면 true, 그렇지 않으면 false
     */
    public boolean isRight() {
        return right;
    }

    /**
     * 플레이어가 현재 위쪽으로 이동 중인지 여부를 반환합니다.
     *
     * @return 위쪽 이동 중이면 true, 그렇지 않으면 false
     */
    public boolean isUp() {
        return up;
    }

    /**
     * 플레이어가 현재 아래쪽으로 이동 중인지 여부를 반환합니다.
     *
     * @return 아래쪽 이동 중이면 true, 그렇지 않으면 false
     */
    public boolean isDown() {
        return down;
    }

    /**
     * 플레이어의 왼쪽 이동 상태를 설정합니다.
     *
     * @param left 왼쪽 이동 상태
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * 플레이어의 오른쪽 이동 상태를 설정합니다.
     *
     * @param right 오른쪽 이동 상태
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * 플레이어의 위쪽 이동 상태를 설정합니다.
     *
     * @param up 위쪽 이동 상태
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * 플레이어의 아래쪽 이동 상태를 설정합니다.
     *
     * @param down 아래쪽 이동 상태
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * 아이템과의 충돌을 확인하고, 충돌 시 아이템을 수집합니다.
     *
     * @throws IOException 파일 입출력 예외
     * @throws FontFormatException 폰트 형식 예외
     */
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