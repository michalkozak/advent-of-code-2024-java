package mk.aoc24.day14;

public class Robot {

    private final int vx;
    private final int vy;

    private int x;
    private int y;

    public Robot(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }


    public void move(int width, int height) {
        x += vx;
        if (x < 0) {
            x = width + x;
        }
        x %= width;
        y += vy;
        if (y < 0) {
            y = height + y;
        }
        y %= height;
    }


    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

}
