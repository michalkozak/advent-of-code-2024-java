package mk.aoc24.day14;

import java.util.List;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;

public class BathroomRobots extends PuzzleSolver {

    protected final int width;
    protected final int height;
    protected List<Robot> robots;

    public BathroomRobots(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected void parseLines(Stream<String> lines) {
        robots = lines.map(this::parseRobot).toList();
    }

    private Robot parseRobot(String line) {
        int idx0 = line.indexOf("p=");
        int idx1 = line.indexOf(",");
        int x = Integer.parseInt(line.substring(idx0 + 2, idx1));
        int idx2= line.indexOf(" ");
        int y = Integer.parseInt(line.substring(idx1 + 1, idx2));
        int idx3 = line.indexOf("v=");
        int idx4 = line.lastIndexOf(",");
        int vx = Integer.parseInt(line.substring(idx3 + 2, idx4));
        int vy = Integer.parseInt(line.substring(idx4 + 1));
        return new Robot(x, y, vx, vy);
    }

    @Override
    protected String computeResult() {
        for (int i = 0; i < 100; i++) {
            for (Robot robot : robots) {
                robot.move(width, height);
            }
        }

        int[][] numberOfRobots = countRobots();

        int result = countRobotsInQuadrants(numberOfRobots, 0, width / 2, 0, height / 2);
        result *= countRobotsInQuadrants(numberOfRobots, 0, width / 2, height / 2 + 1, height);
        result *=countRobotsInQuadrants(numberOfRobots, width / 2 + 1, width, 0, height / 2);
        result *= countRobotsInQuadrants(numberOfRobots, width / 2 + 1, width, height / 2 + 1, height);

        return String.valueOf(result);
    }


    protected int[][] countRobots() {
        int[][] numberOfRobots = new int[width][height];
        for (Robot robot : robots) {
            numberOfRobots[robot.x()][robot.y()]++;
        }
        return numberOfRobots;
    }

    private int countRobotsInQuadrants(int[][] numberOfRobots, int xmin, int xmax, int ymin, int ymax) {
        int count = 0;
        for (int j = ymin; j < ymax; j++) {
            for (int i = xmin; i < xmax; i++) {
                count += numberOfRobots[i][j];
            }
        }
        return count;
    }

}
