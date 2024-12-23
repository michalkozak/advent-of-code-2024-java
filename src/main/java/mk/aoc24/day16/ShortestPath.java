package mk.aoc24.day16;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.board.Board;
import mk.aoc24.board.BoardGraph;
import mk.aoc24.board.Coordinate;
import mk.aoc24.board.Dijkstra;
import mk.aoc24.board.DirectedCoordinate;
import mk.aoc24.board.Direction;
import mk.aoc24.board.Path;
import mk.aoc24.board.Vertex;

public class ShortestPath extends PuzzleSolver {

    protected Board board;

    protected Coordinate start;
    protected Coordinate end;

    @Override
    protected void parseLines(Stream<String> lines) {
        board = new Board(lines);
        start = board.findCoordinateOf('S');
        end = board.findCoordinateOf('E');
    }

    @Override
    protected String computeResult() {
        Map<DirectedCoordinate, Path<DirectedCoordinate>> paths = findShortestPath();

        int minDistance = Integer.MAX_VALUE;
        for (Direction direction : Direction.values()) {
            DirectedCoordinate directedEnd = new DirectedCoordinate(end, direction);
            if (paths.containsKey(directedEnd)) {
                minDistance = Math.min(minDistance, paths.get(directedEnd).distance());
            }
        }
        return String.valueOf(minDistance);
    }

    protected Map<DirectedCoordinate, Path<DirectedCoordinate>> findShortestPath() {
        BoardGraph<DirectedCoordinate> graph = board.toDirectedGraph('#');
        DirectedCoordinate startDirected = new DirectedCoordinate(start, Direction.RIGHT);
        if (graph.vertex(startDirected) == null) {
            addMissingStartVertex(graph, startDirected);
        }
        return graph.shortestPathByDijkstra(startDirected, this::computeDistance);
    }

    public void addMissingStartVertex(BoardGraph<DirectedCoordinate> graph, DirectedCoordinate startDirected) {
        List<DirectedCoordinate> neighbors = Arrays.stream(Direction.values())
            .filter(d -> d != startDirected.direction())
            .filter(d -> graph.vertices().containsKey(new DirectedCoordinate(startDirected.coordinate(), d)))
            .map(d -> new DirectedCoordinate(startDirected.coordinate(), d)).toList();
        for (DirectedCoordinate neighbor : neighbors) {
            graph.vertices().get(neighbor).edges().add(startDirected);
        }
        graph.vertices().put(startDirected, new Vertex<>(startDirected, neighbors));
    }

    private int computeDistance(Dijkstra<DirectedCoordinate> d, DirectedCoordinate edge) {
        return switch (d.coordinate().direction()) {
            case UP, DOWN-> {
                if (!d.coordinate().coordinate().equals(edge.coordinate())) {
                    yield 1;
                } else if (edge.direction() == Direction.RIGHT || edge.direction() == Direction.LEFT) {
                    yield 1000;
                } else {
                    yield 0;
                }
            }
            case RIGHT, LEFT -> {
                if (!d.coordinate().coordinate().equals(edge.coordinate())) {
                    yield 1;
                } else if (edge.direction() == Direction.UP || edge.direction() == Direction.DOWN) {
                    yield 1000;
                } else {
                    yield 0;
                }
            }
        };
    }

}
