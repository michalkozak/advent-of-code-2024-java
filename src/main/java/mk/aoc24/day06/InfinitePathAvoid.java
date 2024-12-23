package mk.aoc24.day06;

import mk.aoc24.board.Board;

public class InfinitePathAvoid extends FindPath {

    private int numberOfPotentialInfiniteObstacles = 0;

    @Override
    protected String computeResult() {
        MovingCoordinate guardPosition = findGuardCoordinate();
        MovingCoordinate originalGuardPosition = new MovingCoordinate(guardPosition);
        super.moveGuard(guardPosition);

        for (int i = 0; guardBoard.width() > i; i++) {
            for (int j = 0; j < guardBoard.height(); j++) {
                if (guardBoard.get(i, j) == 'X') {
                    Board boardWithAdditionalObstacle = new Board(guardBoard);
                    boardWithAdditionalObstacle.set(i, j,'#');
                    MovingCoordinate infiniteGuardPosition = new MovingCoordinate(originalGuardPosition);
                    moveGuard(infiniteGuardPosition, boardWithAdditionalObstacle);
                }
            }
        }

        return String.valueOf(numberOfPotentialInfiniteObstacles);
    }

    private void moveGuard(MovingCoordinate guardPosition, Board board) {
        char[][] visitedPlaces = new char[board.width()][board.height()];
        while (true) {
            if (visitedPlaces[guardPosition.x][guardPosition.y] == guardPosition.direction.name().charAt(0)) {
                numberOfPotentialInfiniteObstacles++;
                return;
            } else {
                if (visitedPlaces[guardPosition.x][guardPosition.y] == 0) {
                    visitedPlaces[guardPosition.x][guardPosition.y] = guardPosition.direction.name().charAt(0);
                }
            }

            ObstaclePresent obstacleAhead = isObstacleAhead(guardPosition, board);
            if (obstacleAhead == ObstaclePresent.YES) {
                guardPosition.turnRight();
                continue;
            } else if (obstacleAhead == ObstaclePresent.END) {
                return;
            }
            guardPosition.move();
        }
    }

}
