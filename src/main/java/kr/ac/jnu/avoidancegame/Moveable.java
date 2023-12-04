package kr.ac.jnu.avoidancegame;

/**
 * 이동 가능한 객체들이 구현해야 하는 인터페이스입니다.
 * 이 인터페이스는 객체가 네 가지 기본 방향(왼쪽, 오른쪽, 위, 아래)으로 이동할 수 있도록 메서드를 정의합니다.
 *
 * @author Tam Oh
 */
public interface Moveable {
    /**
     * 객체를 왼쪽으로 이동시키는 메서드입니다.
     */
    public abstract void left();

    /**
     * 객체를 오른쪽으로 이동시키는 메서드입니다.
     */
    public abstract void right();

    /**
     * 객체를 위로 이동시키는 메서드입니다.
     */
    public abstract void up();

    /**
     * 객체를 아래로 이동시키는 메서드입니다.
     */
    public abstract void down();
}