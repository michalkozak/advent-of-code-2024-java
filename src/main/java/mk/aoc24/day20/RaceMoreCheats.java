package mk.aoc24.day20;

import java.util.Map;
import mk.aoc24.board.BoardGraph;
import mk.aoc24.board.Coordinate;
import mk.aoc24.board.Path;

public class RaceMoreCheats extends RaceCheat {

    public RaceMoreCheats(int distanceToSave) {
        super(distanceToSave);
    }

    @Override
    protected String computeResult() {
        BoardGraph<Coordinate> graph = board.toGraph('#');
        Map<Coordinate, Path<Coordinate>> paths = graph.shortestPathByDijkstra(start, (d, c) -> 1);
        int count = findAndCountShortcuts(paths, 20);
        return String.valueOf(count);
    }

}
