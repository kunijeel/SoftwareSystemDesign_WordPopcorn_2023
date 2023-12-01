package kr.ac.jnu.avoidancegame;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Objects;
import java.util.Random;

public class ObstacleFour extends Obstacle {
    private Image image; // 장애물 이미지
    private static final Random random = new Random();
    private static final double MAX_SPEED = 5.0; // 최대 이동 속도
    private long lastMovementTime; // 마지막으로 이동 방향을 변경한 시간
    private static final long MOVEMENT_INTERVAL = 700; // 이동 방향 변경 간격 (ms)

    public ObstacleFour(int panelWidth, int panelHeight, int size) {
        super(random.nextInt(panelWidth), random.nextInt(panelHeight), size, 0, 0, "/Image/avoidancegame/obstaclefour.png");
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/avoidancegame/obstaclefour.png"))).getImage();
        this.lastMovementTime = System.currentTimeMillis();
        initializePosition(panelWidth, panelHeight); // 테두리에서 생성 위치 초기화
        randomizeMovement();
    }

    private void initializePosition(int panelWidth, int panelHeight) {
        int side = random.nextInt(4); // 0-3 사이의 랜덤 숫자 선택 (상단, 하단, 좌측, 우측)
        switch(side) {
            case 0: // 상단
                this.x = random.nextInt(panelWidth);
                this.y = 0;
                break;
            case 1: // 하단
                this.x = random.nextInt(panelWidth);
                this.y = panelHeight - size;
                break;
            case 2: // 좌측
                this.x = 0;
                this.y = random.nextInt(panelHeight);
                break;
            case 3: // 우측
                this.x = panelWidth - size;
                this.y = random.nextInt(panelHeight);
                break;
        }
    }

    // 장애물의 이동 방향 및 속도를 무작위로 설정
    private void randomizeMovement() {
        double angle = random.nextDouble() * 2 * Math.PI;
        double speed = 0.5 + random.nextDouble() * (MAX_SPEED - 1); // 최저 속도 0.5, 최대 속도 MAX_SPEED
        dx = speed * Math.cos(angle);
        dy = speed * Math.sin(angle);
    }

    @Override
    public void move() {
        long currentTime = System.currentTimeMillis();
        // 일정 시간 간격이 지났는지 확인
        if (currentTime - lastMovementTime > MOVEMENT_INTERVAL) {
            randomizeMovement();
            lastMovementTime = currentTime;
        }
        super.move();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, size, size, null);
    }
}