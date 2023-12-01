package kr.ac.jnu.avoidancegame;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Objects;
import java.util.Random;

public class ObstacleOne extends Obstacle {

    private Image image; // 장애물 이미지
    private static final Random random = new Random();

    // ObstacleOne 생성자 수정
    public ObstacleOne(int panelWidth, int panelHeight, int size) {
        super(0, 0, size, 0, 0, "/Image/avoidancegame/obstacleone.png");
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/avoidancegame/obstacleone.png"))).getImage();

        // 장애물의 위치 및 방향 설정 로직
        initializePositionAndDirection(panelWidth, panelHeight);
    }

    private void initializePositionAndDirection(int panelWidth, int panelHeight) {
        // 테두리 중 임의의 위치 선택
        int position = random.nextInt(2 * (panelWidth + panelHeight));
        int x, y;
        if (position < panelWidth) { // 상단
            x = position;
            y = 0;
        } else if (position < panelWidth + panelHeight) { // 우측
            x = panelWidth - size;
            y = position - panelWidth;
        } else if (position < 2 * panelWidth + panelHeight) { // 하단
            x = position - panelWidth - panelHeight;
            y = panelHeight - size;
        } else { // 좌측
            x = 0;
            y = position - 2 * panelWidth - panelHeight;
        }

        // 중앙을 향하는 방향 계산
        double centerX = panelWidth / 2.0;
        double centerY = panelHeight / 2.0;
        double angle = Math.atan2(centerY - y, centerX - x);
        double speed = 4; // 이동 속도
        this.dx = speed * Math.cos(angle);
        this.dy = speed * Math.sin(angle);

        // 위치 설정
        this.x = x;
        this.y = y;
    }

    // draw 메소드 오버라이드
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, size, size, null); // 이미지 그리기
    }
}