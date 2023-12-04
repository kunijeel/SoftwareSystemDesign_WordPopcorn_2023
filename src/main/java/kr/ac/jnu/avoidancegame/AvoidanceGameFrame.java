package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.CustomInfoDialog;
import kr.ac.jnu.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

/**
 * 이 클래스는 회피 게임의 메인 프레임을 구현합니다.
 * 사용자는 이 프레임에서 게임을 플레이하고, 아이템을 수집하며, 장애물을 피합니다.
 *
 * @author Tam Oh
 */
public class AvoidanceGameFrame extends JFrame {
    /**
     * 메인 패널에 대한 참조입니다. 이를 통해 다른 패널과 상호작용할 수 있습니다.
     */
    private MainPanel mainPanel;

    /**
     * 게임의 배경을 표시하는 레이블입니다.
     */
    private JLabel labelBackground;

    /**
     * 수집한 아이템 수를 표시하는 레이블입니다.
     */
    private JLabel labelCount;

    /**
     * 플레이어의 캐릭터를 나타내는 객체입니다.
     */
    private Player player;

    /**
     * 게임의 메인 타이머입니다. 이 타이머는 장애물을 생성하고 게임 로직을 관리합니다.
     */
    private Timer timer;

    /**
     * 아이템 생성을 위한 타이머입니다.
     */
    private Timer itemSpawnTimer;

    /**
     * 게임 오버 상태를 나타내는 플래그입니다.
     */
    private boolean isGameOver = false;

    /**
     * 수집한 아이템의 수입니다.
     */
    private int collectedItems = 0;

    /**
     * AvoidanceGameFrame의 생성자입니다.
     * 게임의 초기 설정을 구성합니다.
     *
     * @param mainPanel 메인 패널에 대한 참조
     */
    public AvoidanceGameFrame(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        initSetting();
        initObject();
        initListener();
        setVisible(true);
    }

    /**
     * 게임에 필요한 객체들을 초기화하는 메서드입니다.
     * 배경, 플레이어, 아이템, 장애물 생성 타이머 등을 설정합니다.
     */
    private void initObject() {
        labelBackground = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/AvoidanceGame/background.png"))));
        setContentPane(labelBackground);

        labelCount = new JLabel("Shrimps: 0 / 3");
        labelCount.setBounds(10, 10, 150, 30);
        add(labelCount);

        player = new Player();
        add(player);

        itemSpawnTimer = new Timer(3000, e -> { // 3초마다 아이템 생성
            add(new Item());
            repaint();
        });
        itemSpawnTimer.start();

        timer = new Timer(500, e -> {
            Random random = new Random();
            int chance = random.nextInt(100);
            Obstacle obstacle;

            if (chance < 50) {
                obstacle = new Obstacle1("/Image/AvoidanceGame/obstacle1.png", player, this, timer);
            } else if (chance < 60) {
                obstacle = new Obstacle2("/Image/AvoidanceGame/obstacle2.png", player, this, timer);
            } else if (chance < 80) {
                obstacle = new Obstacle3("/Image/AvoidanceGame/obstacle3.png", player, this, timer);
            } else {
                obstacle = new Obstacle4("/Image/AvoidanceGame/obstacle4.png", player, this, timer);
            }
            add(obstacle);
            repaint();
        });
        timer.start();
    }

    /**
     * 게임 오버 상태를 설정하는 메서드입니다.
     * 이 메서드를 호출하면 게임이 종료됩니다.
     */
    public void setGameOver() {
        this.isGameOver = true;
    }

    /**
     * 게임 오버 상태를 반환하는 메서드입니다.
     *
     * @return 게임 오버 상태를 나타내는 boolean 값. 게임 오버일 경우 true를 반환합니다.
     */
    public boolean isGameOver() {
        return this.isGameOver;
    }

    /**
     * 아이템을 수집했을 때 호출되는 메서드입니다.
     * 수집한 아이템의 수를 증가시키고, 수집한 아이템이 일정 수에 도달하면 게임을 종료합니다.
     * 게임 종료 시 힌트를 표시합니다.
     *
     * @throws IOException 파일 입출력 예외가 발생할 수 있습니다.
     * @throws FontFormatException 폰트 형식 예외가 발생할 수 있습니다.
     */
    public void collectItem() throws IOException, FontFormatException {
        collectedItems++;
        labelCount.setText("Shrimps: " + collectedItems + " / 3");
        if (collectedItems >= 3) {
            itemSpawnTimer.stop();
            timer.stop();
            this.setGameOver();
            String currentSongName = mainPanel.getCurrentSongName();
            String hint = mainPanel.getSongLibrary().getHintByTitle(currentSongName);
            CustomInfoDialog.showInfoDialog(this, "알림", hint, 15f, 600, 200);
            this.dispose();
        }
    }

    /**
     * 게임 내에서 아이템 생성을 제어하는 타이머를 중지시키는 메소드입니다.
     * 이 메소드는 아이템 생성을 중단할 필요가 있을 때 호출됩니다 (예: 게임 오버 시).
     */
    public void stopItemSpawnTimer() {
        if (itemSpawnTimer != null) {
            itemSpawnTimer.stop();
        }
    }

    /**
     * 게임 창의 기본 설정을 수행하는 메서드입니다.
     * 크기, 레이아웃, 종료 동작, 창 제목 등을 설정합니다.
     */
    private void initSetting() {
        setSize(800, 800);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("The Adventures of Mulgae");
    }

    /**
     * 게임의 이벤트 리스너를 초기화하는 메서드입니다.
     * 키보드 입력에 대한 이벤트 처리를 설정합니다.
     * 플레이어의 움직임과 키보드 입력의 릴리즈를 관리합니다.
     */
    private void initListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (!player.isLeft()) {
                            player.left();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!player.isRight()) {
                            player.right();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (!player.isUp()) {
                            player.up();
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (!player.isDown()) {
                            player.down();
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        player.setLeft(false);
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.setRight(false);
                        break;
                    case KeyEvent.VK_UP:
                        player.setUp(false);
                        break;
                    case KeyEvent.VK_DOWN:
                        player.setDown(false);
                        break;
                }
            }
        });
    }
}