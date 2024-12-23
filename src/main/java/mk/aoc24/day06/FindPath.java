package mk.aoc24.day06;

import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.board.Board;
import mk.aoc24.board.Direction;

public class FindPath extends PuzzleSolver {

    protected Board guardBoard;
    protected int numberOfVisitedPlaces = 0;

    @Override
    protected void parseLines(Stream<String> lines) {
        guardBoard = new Board(lines);
    }

    @Override
    protected String computeResult() {
        MovingCoordinate gourdPosition = findGuardCoordinate();
        moveGuard(gourdPosition);
        return String.valueOf(numberOfVisitedPlaces);
    }

    protected MovingCoordinate findGuardCoordinate() {
        for (int i = 0; guardBoard.width() > i; i++) {
            for (int j = 0; j < guardBoard.height(); j++) {
                if (guardBoard.get(i, j) == '^'){
                    return new MovingCoordinate(i, j, Direction.UP);
                }
            }
        }
        throw new IllegalStateException("No guard found");
    }

    protected void moveGuard(MovingCoordinate guardPosition) {
        while (true){
            if (guardBoard.get(guardPosition.getCoordinate()) != 'X') {
                guardBoard.set(guardPosition.getCoordinate(), 'X');
                numberOfVisitedPlaces++;
            }
            ObstaclePresent obstacleAhead = isObstacleAhead(guardPosition, guardBoard);
            if (obstacleAhead == ObstaclePresent.YES) {
                guardPosition.turnRight();
                continue;
            } else if (obstacleAhead == ObstaclePresent.END) {
                return;
            }
            guardPosition.move();
        }
    }

    protected ObstaclePresent isObstacleAhead(MovingCoordinate guardPosition, Board board) {
        return switch (guardPosition.direction) {
            case UP -> {
                if (guardPosition.y - 1 >= 0) {
                    yield isObstacle(guardPosition.x, guardPosition.y - 1, board);
                } else {
                    yield ObstaclePresent.END;
                }
            }
            case DOWN -> {
                if (guardPosition.y + 1 < board.height()) {
                    yield isObstacle(guardPosition.x, guardPosition.y + 1, board);
                } else {
                    yield ObstaclePresent.END;
                }
            }
            case LEFT -> {
                if (guardPosition.x - 1 >= 0) {
                    yield isObstacle(guardPosition.x - 1, guardPosition.y, board);
                } else {
                    yield ObstaclePresent.END;
                }
            }
            case RIGHT -> {
                if (guardPosition.x + 1 < board.width()) {
                    yield isObstacle(guardPosition.x + 1, guardPosition.y, board);
                } else {
                    yield ObstaclePresent.END;
                }
            }
        };
    }

    private ObstaclePresent isObstacle(int x, int y, Board board) {
        if (board.get(x, y) == '#') {
            return ObstaclePresent.YES;
        } else {
            return ObstaclePresent.NO;
        }
    }

}
