package mk.aoc24.day18;

import java.util.Map;
import mk.aoc24.board.BoardGraph;
import mk.aoc24.board.Coordinate;
import mk.aoc24.board.Path;

public class NoExit extends MemorySpace {

    public NoExit(int width, int height, int steps) {
        super(width, height, steps);
    }

    @Override
    protected String computeResult() {
        int i = steps + 1;
        while (i < obstacles.size()) {
            board.set(obstacles.get(i), '#');

            BoardGraph<Coordinate> graph = board.toGraph('#');

            Map<Coordinate, Path<Coordinate>> paths = graph.shortestPathByDijkstra(start, (d, c) -> 1);
            if (paths.get(end).distance() == Integer.MAX_VALUE) {
                break;
            }
            i++;
        }
        Coordinate obstaclesEnd = obstacles.get(i);
        return String.format("%s,%s", obstaclesEnd.x(), obstaclesEnd.y());
    }

}
