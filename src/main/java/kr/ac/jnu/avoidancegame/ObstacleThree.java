package kr.ac.jnu.avoidancegame;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Objects;
import java.util.Random;

public class ObstacleThree extends Obstacle {

    private static final Random random = new Random();
    private static final int SPLIT_TIME = 1300; // 분할까지의 시간 (ms)
    private static final double SPEED = 2; // 이동 속도

    private long creationTime; // 생성 시간
    private boolean hasSplit = false; // 분할 여부
    private Image image;

    public ObstacleThree(int panelWidth, int panelHeight, int size) {
        super(0, 0, size, 0, 0, "/Image/avoidancegame/obstaclethree.png");
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Image/avoidancegame/obstaclethree.png"))).getImage();
        initializePositionAndDirection(panelWidth, panelHeight);
        this.creationTime = System.currentTimeMillis();
    }

    private void initializePositionAndDirection(int panelWidth, int panelHeight) {
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
        double speed = 2; // 이동 속도
        this.dx = speed * Math.cos(angle);
        this.dy = speed * Math.sin(angle);

        // 위치 설정
        this.x = x;
        this.y = y;
    }

    public boolean shouldSplit() {
        return !hasSplit && (System.currentTimeMillis() - creationTime > SPLIT_TIME);
    }

    public ObstacleThree[] split() {
        hasSplit = true;
        ObstacleThree[] newObstacles = new ObstacleThree[4];
        int newSize = this.size / 2;
        double[] directions = new double[]{0, Math.PI / 2, Math.PI, 3 * Math.PI / 2}; // 동, 북, 서, 남

        for (int i = 0; i < 4; i++) {
            double angle = directions[i];
            double dx = SPEED * Math.cos(angle);
            double dy = SPEED * Math.sin(angle);
            newObstacles[i] = new ObstacleThree((int) this.x, (int) this.y, newSize, dx, dy, this.imagePath);
        }

        return newObstacles;
    }

    private ObstacleThree(int x, int y, int size, double dx, double dy, String imagePath) {
        super(x, y, size, dx, dy, imagePath);
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))).getImage();
        this.hasSplit = true; // 추가 생성자를 사용하는 경우, 이미 분할된 것으로 간주
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, size, size, null);
    }
}