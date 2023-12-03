package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.CustomInfoDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class AvoidanceGameFrame extends JFrame {
    private JLabel labelBackground;
    private Player player;
    private Timer timer;
    private Timer itemSpawnTimer;
    private boolean isGameOver = false; // 게임 오버 상태 관리
    private int collectedItems = 0;
    public AvoidanceGameFrame() {
        initObject();
        initSetting();
        initListener();
        setVisible(true);
    }
    private void initObject() {
        labelBackground = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/AvoidanceGame/background.png"))));
        setContentPane(labelBackground);

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
                obstacle = new Obstacle1("/Image/avoidancegame/obstacle1.png", player, this, timer);
            } else if (chance < 60) {
                obstacle = new Obstacle2("/Image/avoidancegame/obstacle2.png", player, this, timer);
            } else if (chance < 80) { // 예를 들어, 65부터 80 사이의 확률로 Obstacle3 생성
                obstacle = new Obstacle3("/Image/avoidancegame/obstacle3.png", player, this, timer);
            } else { // 80 이상의 확률로 Obstacle4 생성
                obstacle = new Obstacle4("/Image/avoidancegame/obstacle4.png", player, this, timer);
            }
            add(obstacle);
            repaint();
        });
        timer.start();
    }
    public void setGameOver() {
        this.isGameOver = true;
    }
    public boolean isGameOver() {
        return this.isGameOver;
    }
    public void collectItem() throws IOException, FontFormatException {
        collectedItems++;
        if (collectedItems >= 3) {
            itemSpawnTimer.stop();
            timer.stop();
            this.setGameOver();
            CustomInfoDialog.showInfoDialog(this, "알림", "Game Clear!", 40f, 400, 200);
            this.dispose();
        }
    }
    private void initSetting() {
        setSize(800, 800);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
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