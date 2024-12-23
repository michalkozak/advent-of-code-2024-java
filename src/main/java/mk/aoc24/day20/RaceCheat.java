package mk.aoc24.day20;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.board.Board;
import mk.aoc24.board.BoardGraph;
import mk.aoc24.board.Coordinate;
import mk.aoc24.board.Path;

public class RaceCheat extends PuzzleSolver {

    protected final int distanceToSave;

    protected Board board;
    protected Coordinate start;
    protected Coordinate end;

    public RaceCheat(int distanceToSave) {
        this.distanceToSave = distanceToSave;
    }

    @Override
    protected void parseLines(Stream<String> lines) {
        board = new Board(lines);
        start = board.findCoordinateOf('S');
        end = board.findCoordinateOf('E');
    }

    @Override
    protected String computeResult() {
        BoardGraph<Coordinate> graph = board.toGraph('#');
        Map<Coordinate, Path<Coordinate>> paths = graph.shortestPathByDijkstra(start, (d, c) -> 1);
        int count = findAndCountShortcuts(paths, 2);
        return String.valueOf(count);
    }

    protected int findAndCountShortcuts(Map<Coordinate, Path<Coordinate>> paths, int maxShortcutLength) {
        int count = 0;
        board.set(start, '.');
        board.set(end, '.');
        for (int x = 1; x < board.width() - 1; x++) {
            for (int y = 1; y < board.height() - 1; y++) {
                Coordinate shortcutStart = new Coordinate(x, y);
                if (paths.containsKey(shortcutStart)) {
                    for (int u = x - maxShortcutLength; u <= x + maxShortcutLength; u++) {
                        for (int v = y - maxShortcutLength; v <= y + maxShortcutLength; v++) {
                            Coordinate shortcutEnd = new Coordinate(u, v);
                            int cheatingPathLength = Math.abs(x - u) + Math.abs(y - v);
                            if (cheatingPathLength <= maxShortcutLength && board.contains(shortcutEnd) && board.get(u, v) == '.') {
                                Optional<Integer> shortestByAddingVertex = countLengthOfShortcut(paths, shortcutStart,
                                    shortcutEnd, cheatingPathLength);
                                if (shortestByAddingVertex.isPresent() && shortestByAddingVertex.get() >= distanceToSave) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private Optional<Integer> countLengthOfShortcut(Map<Coordinate, Path<Coordinate>> paths, Coordinate start, Coordinate end,
        int cheatingPathLength) {
        int result = paths.get(end).distance() - paths.get(start).distance() - cheatingPathLength;
        if (result > 0) {
            return Optional.of(result);
        } else {
            return Optional.empty();
        }
    }

}
