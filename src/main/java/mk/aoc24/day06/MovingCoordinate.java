package mk.aoc24.day06;

import java.util.Objects;
import mk.aoc24.board.Coordinate;
import mk.aoc24.board.Direction;

public class MovingCoordinate {
    int x;
    int y;
    Direction direction;

    public MovingCoordinate(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public MovingCoordinate(MovingCoordinate movingCoordinate) {
        this.x = movingCoordinate.x;
        this.y = movingCoordinate.y;
        this.direction = movingCoordinate.direction;
    }

    public void move() {
        switch (direction) {
            case UP -> moveUp();
            case DOWN -> moveDown();
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
        }
    }

    public void turnRight() {
        direction = switch (direction) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
        };
    }

    public Coordinate getCoordinate() {
        return new Coordinate(x, y);
    }

    private void moveUp() {
        y--;
    }

    private void moveDown() {
        y++;
    }

    private void moveLeft() {
        x--;
    }

    private void moveRight() {
        x++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovingCoordinate that)) {
            return false;
        }
        return x == that.x && y == that.y && direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction);
    }
}
