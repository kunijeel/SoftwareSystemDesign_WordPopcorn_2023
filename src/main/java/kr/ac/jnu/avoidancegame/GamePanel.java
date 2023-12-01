package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.MiniGameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GamePanel extends JPanel {
    private double squareX;
    private double squareY;
    private final int squareSize = 15;
    private final ArrayList<Obstacle> obstacles;
    private final Random random;
    private final Timer gameTimer;
    private boolean gameStarted = false;
    private MiniGameFrame miniGameFrame;

    public GamePanel(MiniGameFrame miniGameFrame) {
        this.miniGameFrame = miniGameFrame;

        obstacles = new ArrayList<>();
        random = new Random();
        setPreferredSize(new Dimension(700, 700));

        setupKeyBindings();
        setFocusable(true);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                squareX = getWidth() / 2.0 - squareSize / 2.0;
                squareY = getHeight() / 2.0 - squareSize / 2.0;
            }
        });

        gameTimer = new Timer(15, this::onGameUpdate);
    }
    private void onGameUpdate(ActionEvent e) {
        if (random.nextInt(1000) < 15) {
            addObstacle(ObstacleOne.class);
        }
        if (random.nextInt(1000) < 3) {
            addObstacle(ObstacleTwo.class);
        }
        if (random.nextInt(1000) < 15) {
            addObstacle(ObstacleThree.class);
        }
        if (random.nextInt(1000) < 10) { // 확률은 조정 가능
            addObstacle(ObstacleFour.class);
        }

        moveObstacle();
        checkCollisions();
        repaint();
    }
    private void setupKeyBindings() {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        String[] actionNames = {"MoveUp", "MoveDown", "MoveLeft", "MoveRight"};
        int[] keyEvents = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};

        for (int i = 0; i < actionNames.length; i++) {
            KeyStroke keyStroke = KeyStroke.getKeyStroke(keyEvents[i], 0);
            im.put(keyStroke, actionNames[i]);
            int finalI = i;
            am.put(actionNames[i], new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!gameStarted) {
                        gameTimer.start();
                        gameStarted = true;
                    }
                    movePlayer(keyEvents[finalI]);
                }
            });
        }
    }
    private void movePlayer(int direction) {
        final int moveAmount = 20;
        final double smoothing = 1; // 이동 부드러움 정도

        double targetX = squareX;
        double targetY = squareY;

        switch (direction) {
            case KeyEvent.VK_UP:
                targetY = Math.max(squareY - moveAmount, 0);
                break;
            case KeyEvent.VK_DOWN:
                targetY = Math.min(squareY + moveAmount, getHeight() - squareSize);
                break;
            case KeyEvent.VK_LEFT:
                targetX = Math.max(squareX - moveAmount, 0);
                break;
            case KeyEvent.VK_RIGHT:
                targetX = Math.min(squareX + moveAmount, getWidth() - squareSize);
                break;
        }

        squareX += ((targetX - squareX) * smoothing);
        squareY += ((targetY - squareY) * smoothing);

        repaint();
    }
    private void addObstacle(Class<? extends Obstacle> obstacleClass) {
        try {
            Constructor<? extends Obstacle> constructor = obstacleClass.getConstructor(int.class, int.class, int.class);
            Obstacle obstacle = constructor.newInstance(getWidth(), getHeight(), squareSize);
            obstacles.add(obstacle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void moveObstacle() {
        ArrayList<Obstacle> newObstacles = new ArrayList<>();
        obstacles.removeIf(obstacle -> {
            obstacle.move();

            if (obstacle instanceof ObstacleThree && ((ObstacleThree) obstacle).shouldSplit()) {
                Collections.addAll(newObstacles, ((ObstacleThree) obstacle).split());
                return true; // 현재 장애물 제거
            }

            return obstacle.isOutOfBounds(getWidth(), getHeight());
        });

        obstacles.addAll(newObstacles);
    }
    private void checkCollisions() {
        Rectangle playerBounds = new Rectangle((int) squareX, (int) squareY, squareSize, squareSize);
        for (Obstacle obstacle : obstacles) {
            if (playerBounds.intersects(obstacle.getBounds())) {
                gameTimer.stop();
                JOptionPane.showMessageDialog(this, "Game Over!");
                miniGameFrame.dispose();
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED); // 플레이어 색상 설정
        g.fillRect((int)squareX, (int)squareY, 20, 20); // 플레이어 그리기

        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
    }
}