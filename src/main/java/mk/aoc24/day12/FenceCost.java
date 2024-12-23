package mk.aoc24.day12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.board.Board;
import mk.aoc24.board.Coordinate;

public class FenceCost extends PuzzleSolver {

    protected Board board;
    private final Set<Character> typesOfPlants = new HashSet<>();
    protected final Map<String, Set<Coordinate>> areas = new HashMap<>();

    @Override
    protected void parseLines(Stream<String> lines) {
        board = new Board(lines);
        for (int i = 0; i < board.width(); i++) {
            for (int j = 0; j < board.height(); j++) {
                typesOfPlants.add(board.get(i, j));
            }
        }
        findPlantAreas();
    }

    private void findPlantAreas() {
        Set<Coordinate> visited = new HashSet<>();
        Map<Character, Integer> count = new HashMap<>();
        for (Character type : typesOfPlants) {
            for (int i = 0; i < board.width(); i++) {
                for (int j = 0; j < board.height(); j++) {
                    Coordinate coordinate = new Coordinate(i, j);
                    if (!visited.contains(coordinate) && (board.get(i, j).equals(type))) {
                        Set<Coordinate> coordinatesWithTheSamePlant = new HashSet<>();
                        coordinatesWithTheSamePlant.add(coordinate);
                        int numberOfCoordinatesWithTheSamePlant = coordinatesWithTheSamePlant.size();
                        Set<Coordinate> neighbors = new HashSet<>(coordinatesWithTheSamePlant);
                        while (true) {
                            Set<Coordinate> newNeighbors = new HashSet<>();
                            for (Coordinate neighbor : neighbors) {
                                newNeighbors.addAll(board.findNeighborsWithValue(neighbor, type));
                            }
                            coordinatesWithTheSamePlant.addAll(newNeighbors);
                            if (numberOfCoordinatesWithTheSamePlant == coordinatesWithTheSamePlant.size()) {
                                break;
                            }
                            numberOfCoordinatesWithTheSamePlant = coordinatesWithTheSamePlant.size();
                            neighbors = newNeighbors;
                        }
                        count.putIfAbsent(type, 0);
                        count.put(type, count.get(type) + 1);
                        areas.put(String.valueOf(type) + count.get(type), coordinatesWithTheSamePlant);
                        visited.addAll(coordinatesWithTheSamePlant);
                    }
                }
            }
        }
    }

    @Override
    protected String computeResult() {
        int cost = 0;

        for (Entry<String, Set<Coordinate>> area : areas.entrySet()) {
            int length = 0;
            for (Coordinate coordinate : area.getValue()) {
                length += 4 - board.findNeighborsWithValue(coordinate, area.getKey().charAt(0)).size();
            }
            int entryCost = area.getValue().size() * length;
            cost += entryCost;
        }

        return String.valueOf(cost);
    }

}
