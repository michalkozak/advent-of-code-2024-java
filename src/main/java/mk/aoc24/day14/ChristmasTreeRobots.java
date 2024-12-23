package mk.aoc24.day14;

import java.util.ArrayList;
import java.util.List;
import mk.aoc24.board.Board;

public class ChristmasTreeRobots extends BathroomRobots {

    public ChristmasTreeRobots(int width, int height) {
        super(width, height);
    }

    @Override
    protected String computeResult() {
        int step = 0;
        int adjacentPointHeuristic = height / 5;
        while (true) {
            int[][] numberOfRobots = countRobots();
            List<Integer> adjacentVerticallyPoints = countAdjacentVerticallyPoints(numberOfRobots);
            int maxAdjacentVerticallyPoints = adjacentVerticallyPoints.stream().max(Integer::compareTo).orElseThrow();
            if (maxAdjacentVerticallyPoints > adjacentPointHeuristic &&
                adjacentVerticallyPoints.stream().filter(n -> n > adjacentPointHeuristic).count() >= 5) {
                new Board(numberOfRobots).print();
                break;
            }
            step++;
            for (Robot robot : robots) {
                robot.move(width, height);
            }
        }

        return String.valueOf(step);
    }

    private List<Integer> countAdjacentVerticallyPoints(int[][] numberOfRobots) {
        List<Integer> numberOfAdjacentInColumns = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            int max = 0;
            int count = 0;
            for (int j = 0; j < height; j++) {
                if (numberOfRobots[i][j] == 0) {
                    max = Math.max(max, count);
                    count = 0;
                } else {
                    count++;
                }
            }
            max = Math.max(max, count);
            numberOfAdjacentInColumns.add(max);
        }
        return numberOfAdjacentInColumns;
    }

}
