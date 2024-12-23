package mk.aoc24.day18;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.board.Board;
import mk.aoc24.board.BoardGraph;
import mk.aoc24.board.Coordinate;
import mk.aoc24.board.Path;

public class MemorySpace extends PuzzleSolver {

    protected final int width;
    protected final int height;
    protected final int steps;

    protected Board board;
    protected List<Coordinate> obstacles;

    protected Coordinate start;
    protected Coordinate end;

    public MemorySpace(int width, int height, int steps) {
        this.width = width;
        this.height = height;
        this.steps = steps;
    }

    @Override
    protected void parseLines(Stream<String> lines) {
        this.obstacles = lines.map(this::parseLine).toList();
        board = buildBoardForStep(steps);

        start = new Coordinate(0, 0);
        end = new Coordinate(width - 1, height - 1);
    }

    private Coordinate parseLine(String line) {
        String[] parts = line.split(",");
        return new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    private Board buildBoardForStep(int step) {
        Board stepBoard = new Board(width, height);
        int i = 0;
        for (Coordinate o : obstacles) {
            if (i == step) {
                break;
            }
            stepBoard.set(o, '#');
            i++;
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (stepBoard.get(x, y) == null) {
                    stepBoard.set(x, y, '.');
                }
            }
        }
        return stepBoard;
    }

    @Override
    protected String computeResult() {
        BoardGraph<Coordinate> graph = board.toGraph('#');

        Map<Coordinate, Path<Coordinate>> paths = graph.shortestPathByDijkstra(start, (d, c) -> 1);

        return String.valueOf(paths.get(end).distance());
    }

}
