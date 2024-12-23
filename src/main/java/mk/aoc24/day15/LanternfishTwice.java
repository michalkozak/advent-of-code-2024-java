package mk.aoc24.day15;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;
import mk.aoc24.board.Board;
import mk.aoc24.board.Coordinate;

public class LanternfishTwice extends Lanternfish {

    @Override
    protected void parseLines(Stream<String> lines) {
        super.parseLines(lines);
        board = makeBoardTwiceAsWide();
    }

    private Board makeBoardTwiceAsWide() {
        Board wideBoard = new Board(board.width() * 2, board.height());
        for (int x = 0; x < board.width(); x++) {
            for (int y = 0; y < board.height(); y++) {
                if (board.get(x, y) == '#') {
                    wideBoard.set(2 * x, y, '#');
                    wideBoard.set(2 * x + 1, y, '#');
                } else if (board.get(x, y) == 'O') {
                    wideBoard.set(2 * x, y, '[');
                    wideBoard.set(2 * x + 1, y, ']');
                } else if (board.get(x, y) == '.') {
                    wideBoard.set(2 * x, y, '.');
                    wideBoard.set(2 * x + 1, y, '.');
                } else if (board.get(x, y) == '@') {
                    wideBoard.set(2 * x, y, '@');
                    wideBoard.set(2 * x + 1, y, '.');
                }
            }
        }
        return wideBoard;
    }

    @Override
    protected Coordinate moveVertically(Coordinate robot, int dy) {
        Set<Coordinate> boxes = new HashSet<>();
        int y = robot.y() + dy;
        Set<Integer> rx = new HashSet<>();
        rx.add(robot.x());
        while (isAnyBox(y, rx) && isNoWall(y, rx)) {
            Set<Integer> ux = new HashSet<>();
            for (int x : rx) {
                if (board.get(x, y) == '[') {
                    boxes.add(new Coordinate(x, y));
                    ux.add(x);
                    ux.add(x + 1);
                }
                if (board.get(x, y) == ']') {
                    boxes.add(new Coordinate(x - 1, y));
                    ux.add(x - 1);
                    ux.add(x);
                }
            }
            rx = ux;
            y += dy;
        }
        if (isNoWall(y, rx)) {
            pushBoxesVertically(robot, boxes, dy);
            return new Coordinate(robot.x(), robot.y() + dy);
        }
        return new Coordinate(robot.x(), robot.y());
    }

    @Override
    protected Coordinate moveHorizontally(Coordinate robot, int dx) {
        Set<Coordinate> boxes = new HashSet<>();
        int x = robot.x() + dx;
        while (board.get(x, robot.y()) != '#' && board.get(x, robot.y()) != '.') {
            boxes.add(new Coordinate(x, robot.y()));
            x += 2 * dx;
        }
        if (board.get(x, robot.y()) == '.') {
            pushBoxesHorizontally(robot, boxes, dx);
            return new Coordinate(robot.x() + dx, robot.y());
        }
        return new Coordinate(robot.x(), robot.y());
    }

    @Override
    protected void pushBoxesVertically(Coordinate robot, Set<Coordinate> boxes, int dy) {
        board.set(robot.x(), robot.y(), '.');
        board.set(robot.x(), robot.y() + dy, '@');
        if (board.get(robot.x() - 1, robot.y() + dy) == '[') {
            board.set(robot.x() - 1, robot.y() + dy, '.');
        }
        if (board.get(robot.x() + 1, robot.y() + dy) == ']') {
            board.set(robot.x() + 1, robot.y() + dy, '.');
        }
        for (Coordinate box : boxes) {
            board.set(box.x(), box.y() + dy , '[');
            if (board.get(box.x() - 1, box.y() + dy) == '[') {
                board.set(box.x() - 1, box.y() + dy, '.');
            }
            board.set(box.x() + 1, box.y() + dy , ']');
            if (board.get(box.x() + 2 , box.y() + dy) == ']') {
                board.set(box.x() + 2, box.y() + dy, '.');
            }
        }
    }

    @Override
    protected void pushBoxesHorizontally(Coordinate robot, Set<Coordinate> boxes, int dx) {
        board.set(robot.x(), robot.y(), '.');
        board.set(robot.x() + dx, robot.y(), '@');
        Iterator<Coordinate> iterator = boxes.iterator();
        while (iterator.hasNext()) {
            Coordinate box = iterator.next();
            if (dx == 1) {
                board.set(box.x() + 1, box.y(), '[');
                board.set(box.x() + 2, box.y(), ']');
            } else {
                // dx == -1
                board.set(box.x() - 1, box.y(), ']');
                board.set(box.x() - 2, box.y(), '[');
            }
        }
    }

    private boolean isAnyBox(int y, Set<Integer> rx) {
        for (int x : rx) {
            if (board.get(x, y) == '[' || board.get(x, y) == ']') {
                return true;
            }
        }
        return false;
    }

    private boolean isNoWall(int y, Set<Integer> rx) {
        for (int x : rx) {
            if (board.get(x, y) == '#') {
                return false;
            }
        }
        return true;
    }

    @Override
    protected int countGps() {
        int gps = 0;
        for (int x = 0; x < board.width(); x++) {
            for (int y = 0; y < board.height(); y++) {
                if (board.get(x, y) == '[') {
                    gps += 100 * y + x;
                }
            }
        }
        return gps;
    }

}
