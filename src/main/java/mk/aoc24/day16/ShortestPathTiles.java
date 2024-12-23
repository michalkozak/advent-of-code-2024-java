package mk.aoc24.day16;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import mk.aoc24.board.Coordinate;
import mk.aoc24.board.DirectedCoordinate;
import mk.aoc24.board.Direction;
import mk.aoc24.board.Path;

public class ShortestPathTiles extends ShortestPath {

    @Override
    protected String computeResult() {
        Map<DirectedCoordinate, Path<DirectedCoordinate>> paths = findShortestPath();
        List<DirectedCoordinate> shortestDirectedEnds = findShortestDirectedEnds(paths);

        return String.valueOf(countVerticesOnShortestPath(paths, shortestDirectedEnds));
    }

    private List<DirectedCoordinate> findShortestDirectedEnds(Map<DirectedCoordinate, Path<DirectedCoordinate>> paths) {
        List<DirectedCoordinate> directedCoordinates = Arrays.stream(Direction.values())
            .map(d -> new DirectedCoordinate(end, d)).filter(paths::containsKey).toList();
        List<Path<DirectedCoordinate>> directedPathEnds = directedCoordinates.stream().map(paths::get).toList();
        int minDistance = directedPathEnds.stream().map(Path::distance).min(Integer::compareTo).orElseThrow();
        return directedCoordinates.stream().filter(dc -> paths.get(dc).distance() == minDistance).toList();
    }

    private int countVerticesOnShortestPath(Map<DirectedCoordinate, Path<DirectedCoordinate>> paths,
        List<DirectedCoordinate> shortestDirectedEnds) {
        Set<Coordinate> coordinates = new HashSet<>();
        for (DirectedCoordinate directionCoordinate : shortestDirectedEnds) {
            gatherVerticesOnShortestPath(paths, directionCoordinate, coordinates);
        }
        return coordinates.size();
    }

    private void gatherVerticesOnShortestPath(Map<DirectedCoordinate, Path<DirectedCoordinate>> paths,
        DirectedCoordinate directedCoordinate, Set<Coordinate> coordinates) {
        if (directedCoordinate != null && paths.get(directedCoordinate) != null) {
            coordinates.add(directedCoordinate.coordinate());
            Coordinate neighbor = board.neighbor(directedCoordinate.coordinate(), directedCoordinate.direction());
            int distance = paths.get(directedCoordinate).distance();
            List<DirectedCoordinate> previousDirectedCoordinates = Arrays.stream(Direction.values())
                .map(d -> new DirectedCoordinate(neighbor, d))
                .filter(paths::containsKey)
                .filter(directedNeighbor -> paths.get(directedNeighbor).distance() < distance).toList();
            previousDirectedCoordinates.forEach(p -> gatherVerticesOnShortestPath(paths, p, coordinates));
        }
    }

}
