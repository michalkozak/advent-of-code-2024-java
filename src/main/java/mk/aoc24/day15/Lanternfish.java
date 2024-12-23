package mk.aoc24.day15;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.board.Board;
import mk.aoc24.board.Coordinate;

public class Lanternfish extends PuzzleSolver {

    protected Board board;
    protected String robotMoves;

    @Override
    protected void parseLines(Stream<String> lines) {
        List<String> list = lines.toList();
        board = new Board(list.stream().takeWhile(l -> !l.isEmpty()));
        robotMoves = list.stream().dropWhile(l -> !l.isEmpty()).collect(Collectors.joining());
    }

    @Override
    protected String computeResult() {
        Coordinate robot = board.findCoordinateOf('@');
        for (char move : robotMoves.toCharArray()) {
            robot = switch (move) {
                case '^' -> moveUp(robot);
                case 'v' -> moveDown(robot);
                case '>' -> moveRight(robot);
                case '<' -> moveLeft(robot);
                default -> throw new IllegalStateException("Unexpected value: " + move);
            };
        }

        return String.valueOf(countGps());
    }

    protected Coordinate moveUp(Coordinate robot) {
        return moveVertically(robot, -1);
    }

    protected Coordinate moveDown(Coordinate robot) {
        return moveVertically(robot, 1);
    }

    protected Coordinate moveRight(Coordinate robot) {
        return moveHorizontally(robot, 1);
    }

    protected Coordinate moveLeft(Coordinate robot) {
        return moveHorizontally(robot, -1);
    }

    protected Coordinate moveVertically(Coordinate robot, int dy) {
        Set<Coordinate> boxes = new HashSet<>();
        int y = robot.y() + dy;
        while (board.get(robot.x(), y) != '#' && board.get(robot.x(), y) != '.') {
            boxes.add(new Coordinate(robot.x(), y));
            y += dy;
        }
        if (board.get(robot.x(), y) == '.') {
            pushBoxesVertically(robot, boxes, dy);
            return new Coordinate(robot.x(), robot.y() + dy);
        }
        return new Coordinate(robot.x(), robot.y());
    }

    protected Coordinate moveHorizontally(Coordinate robot, int dx) {
        Set<Coordinate> boxes = new HashSet<>();
        int x = robot.x() + dx;
        while (board.get(x, robot.y()) != '#' && board.get(x, robot.y()) != '.') {
            boxes.add(new Coordinate(x, robot.y()));
            x += dx;
        }
        if (board.get(x, robot.y()) == '.') {
            pushBoxesHorizontally(robot, boxes, dx);
            return new Coordinate(robot.x() + dx, robot.y());
        }
        return new Coordinate(robot.x(), robot.y());
    }

    protected void pushBoxesVertically(Coordinate robot, Set<Coordinate> boxes, int dy) {
        board.set(robot.x(), robot.y(), '.');
        board.set(robot.x(), robot.y() + dy, '@');
        for (Coordinate box : boxes) {
            board.set(box.x(), box.y() + dy, 'O');
        }
    }

    protected void pushBoxesHorizontally(Coordinate robot, Set<Coordinate> boxes, int dx) {
        board.set(robot.x(), robot.y(), '.');
        board.set(robot.x() + dx, robot.y(), '@');
        for (Coordinate box : boxes) {
            board.set(box.x() + dx, box.y(), 'O');
        }
    }

    protected int countGps() {
        int gps = 0;
        for (int x = 0; x < board.width(); x++) {
            for (int y = 0; y < board.height(); y++) {
                if (board.get(x, y) == 'O') {
                    gps += 100 * y + x;
                }
            }
        }
        return gps;
    }

}
