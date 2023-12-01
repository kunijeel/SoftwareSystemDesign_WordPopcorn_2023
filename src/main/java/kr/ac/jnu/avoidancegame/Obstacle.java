package kr.ac.jnu.avoidancegame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Obstacle {
    protected double x, y;   // 장애물의 현재 위치
    protected double dx, dy; // 장애물의 이동 벡터
    protected int size;      // 장애물의 크기
    protected String imagePath; // 장애물의 이미지 경로

    // 장애물의 생성자
    public Obstacle(double x, double y, int size, double dx, double dy, String imagePath) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.dx = dx;
        this.dy = dy;
        this.imagePath = imagePath;
    }

    // 장애물을 이동시키는 메소드
    public void move() {
        x += dx;
        y += dy;
    }

    // 장애물을 그리는 메소드 (구현은 하위 클래스에서 담당)
    public abstract void draw(Graphics g);
    public boolean isOutOfBounds(int panelWidth, int panelHeight) {
        // 장애물의 오른쪽 또는 아래쪽 경계가 패널 경계를 벗어나면 true 반환
        if (x + size > panelWidth || y + size > panelHeight) {
            return true;
        }

        // 장애물의 왼쪽 또는 위쪽 경계가 패널 경계를 벗어나면 true 반환
        if (x < 0 || y < 0) {
            return true;
        }

        // 그 외의 경우에는 false 반환
        return false;
    }


    // 장애물의 경계를 반환하는 메소드
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, size, size);
    }
}