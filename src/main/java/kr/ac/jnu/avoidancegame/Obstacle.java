package kr.ac.jnu.avoidancegame;

import kr.ac.jnu.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

/**
 * 장애물 객체를 나타내는 추상 클래스입니다.
 * 이 클래스는 장애물의 이미지, 위치, 이동 방식을 정의합니다.
 *
 * @author Tam Oh
 */
public abstract class Obstacle extends JLabel {
    /**
     * 장애물의 x 좌표입니다.
     */
    protected int x;

    /**
     * 장애물의 y 좌표입니다.
     */
    protected int y;

    /**
     * 장애물의 x축 이동 속도입니다.
     */
    protected int dx;

    /**
     * 장애물의 y축 이동 속도입니다.
     */
    protected int dy;

    /**
     * 장애물의 이미지를 저장하는 필드입니다.
     */
    protected ImageIcon image;

    /**
     * 장애물 이미지의 파일 경로를 저장하는 필드입니다.
     */
    protected String imagePath;

    /**
     * 게임 화면의 너비를 정의하는 상수입니다.
     */
    protected final int GAME_WIDTH = 800;

    /**
     * 게임 화면의 높이를 정의하는 상수입니다.
     */
    protected final int GAME_HEIGHT = 775;

    /**
     * Obstacle 객체를 생성합니다.
     *
     * @param imagePath 장애물의 이미지 경로입니다.
     */
    public Obstacle(String imagePath) {
        this.imagePath = imagePath;
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        setIcon(UIUtils.resizeImageIcon(image, 40, 40));
        setSize(40, 40);
        initPosition();
    }

    /**
     * 장애물의 초기 위치를 설정합니다.
     * 게임 화면의 네 가지 모서리 중 하나에서 시작합니다.
     */
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

    /**
     * 장애물이 화면 바깥으로 이동했는지 확인합니다.
     *
     * @return 화면 바깥으로 이동했으면 true, 그렇지 않으면 false를 반환합니다.
     */
    protected boolean isOutOfBounds() {
        return x < 0 || x > GAME_WIDTH || y < 0 || y > GAME_HEIGHT;
    }

    /**
     * 장애물을 이동시키는 추상 메서드입니다.
     * 상속받는 클래스에서 이 메서드를 구현하여 각 장애물의 특정 이동 방식을 정의합니다.
     *
     * @throws IOException 파일 입출력 예외가 발생할 경우
     * @throws FontFormatException 폰트 형식 예외가 발생할 경우
     */
    public abstract void move() throws IOException, FontFormatException;
}