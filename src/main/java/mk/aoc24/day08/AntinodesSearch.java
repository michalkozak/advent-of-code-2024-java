package mk.aoc24.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.board.Board;
import mk.aoc24.board.Coordinate;

public class AntinodesSearch extends PuzzleSolver {

    protected Board antennasBoard;
    protected Map<Character, List<Coordinate>> frequencies = new HashMap<>();
    protected Set<Coordinate> antinodesCooridinates = new HashSet<>();

    @Override
    protected void parseLines(Stream<String> lines) {
        antennasBoard = new Board(lines);
        for (int i = 0; i < antennasBoard.width(); i++) {
            for (int j = 0; j < antennasBoard.height(); j++) {
                if (antennasBoard.get(i, j) != '.') {
                    Coordinate coordinate = new Coordinate(i, j);
                    if (!frequencies.containsKey(antennasBoard.get(coordinate))) {
                        List<Coordinate> frequencyCoordinates = new ArrayList<>();
                        frequencies.put(antennasBoard.get(coordinate), frequencyCoordinates);
                    }
                    List<Coordinate> coordinates = frequencies.get(antennasBoard.get(coordinate));
                    coordinates.add(coordinate);
                }
            }
        }
    }

    @Override
    protected String computeResult() {
        for (Map.Entry<Character, List<Coordinate>> entry : frequencies.entrySet()) {
            findAntinodes(entry.getValue());
        }
        return String.valueOf(antinodesCooridinates.size());
    }

    protected void findAntinodes(List<Coordinate> frequencyCoordinates) {
        for (int i = 0; i < frequencyCoordinates.size(); i++) {
            for (int j = i + 1; j < frequencyCoordinates.size(); j++) {
                findAntinodes(frequencyCoordinates.get(i), frequencyCoordinates.get(j));
            }
        }
    }

    protected void findAntinodes(Coordinate frequencyCoordinate1, Coordinate frequencyCoordinate2) {
        int deltaX = frequencyCoordinate1.x() - frequencyCoordinate2.x();
        int deltaY = frequencyCoordinate1.y() - frequencyCoordinate2.y();

        createAntinodes(frequencyCoordinate1, frequencyCoordinate2, deltaX, deltaY);
    }

    protected void createAntinodes(Coordinate frequencyCoordinate1, Coordinate frequencyCoordinate2, int deltaX, int deltaY) {
        createNewAntinode(frequencyCoordinate1, deltaX, deltaY);
        createNewAntinode(frequencyCoordinate2, -deltaX, -deltaY);
    }

    protected Coordinate createNewAntinode(Coordinate originalCoordinate, int deltaX, int deltaY) {
        Coordinate antinodeCoordinate = new Coordinate(originalCoordinate.x() + deltaX, originalCoordinate.y() + deltaY);
        if (checkInsideBoard(antinodeCoordinate)) {
            antinodesCooridinates.add(antinodeCoordinate);
            return antinodeCoordinate;
        }
        return null;
    }

    private boolean checkInsideBoard(Coordinate coordinate) {
        return (coordinate.x() >= 0 && coordinate.y() >= 0 &&
            coordinate.x() < antennasBoard.width() && coordinate.y() < antennasBoard.height());
    }

}
