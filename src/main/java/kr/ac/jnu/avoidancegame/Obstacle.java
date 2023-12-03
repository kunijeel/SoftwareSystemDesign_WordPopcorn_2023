package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public abstract class Obstacle extends JLabel {
    protected int x, y;
    protected int dx, dy; // 이동 속도
    protected ImageIcon image;
    protected String imagePath; // 이미지 경로를 저장하는 필드 추가
    protected final int GAME_WIDTH = 800;
    protected final int GAME_HEIGHT = 775;

    public Obstacle(String imagePath) {
        this.imagePath = imagePath;
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        setIcon(UIUtils.resizeImageIcon(image, 40, 40));
        setSize(40, 40);
        initPosition();
    }

    protected void initPosition() {
        Random rand = new Random();
        int side = rand.nextInt(4);
        switch (side) {
            case 0: // 위
                x = rand.nextInt(GAME_WIDTH);
                y = 0;
                break;
            case 1: // 오른쪽
                x = GAME_WIDTH;
                y = rand.nextInt(GAME_HEIGHT);
                break;
            case 2: // 아래
                x = rand.nextInt(GAME_WIDTH);
                y = GAME_HEIGHT;
                break;
            case 3: // 왼쪽
                x = 0;
                y = rand.nextInt(GAME_HEIGHT);
                break;
        }
        setLocation(x, y);
    }
    protected boolean isOutOfBounds() {
        return x < 0 || x > GAME_WIDTH || y < 0 || y > GAME_HEIGHT;
    }
    public abstract void move() throws IOException, FontFormatException;
}