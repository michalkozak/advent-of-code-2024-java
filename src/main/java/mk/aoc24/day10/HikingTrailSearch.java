package mk.aoc24.day10;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.board.Board;
import mk.aoc24.board.Coordinate;

public class HikingTrailSearch extends PuzzleSolver {

    protected Board board;
    protected int numberOfHikingTrailTops = 0;

    @Override
    protected void parseLines(Stream<String> lines) {
        board = new Board(lines);
    }

    @Override
    protected String computeResult() {
        for (int i = 0; i < board.width(); i++) {
            for (int j = 0; j < board.height(); j++) {
                if (board.get(i, j) == '0') {
                    Set<Coordinate> hikingTrailTops = new HashSet<>();
                    Set<Coordinate> neighborsWith1 = board.findNeighborsWithValue(new Coordinate(i, j), '1');
                    for (Coordinate coordinate1 : neighborsWith1) {
                        Set<Coordinate> neighborsWith2 = board.findNeighborsWithValue(coordinate1, '2');
                        for (Coordinate coordinate2 : neighborsWith2) {
                            Set<Coordinate> neighborsWith3 = board.findNeighborsWithValue(coordinate2, '3');
                            for (Coordinate coordinate3 : neighborsWith3) {
                                Set<Coordinate> neighborsWith4 = board.findNeighborsWithValue(coordinate3, '4');
                                for (Coordinate coordinate4 : neighborsWith4) {
                                    Set<Coordinate> neighborsWith5 = board.findNeighborsWithValue(coordinate4, '5');
                                    for (Coordinate coordinate5 : neighborsWith5) {
                                        Set<Coordinate> neighborsWith6 = board.findNeighborsWithValue(coordinate5, '6');
                                        for (Coordinate coordinate6 : neighborsWith6) {
                                            Set<Coordinate> neighborsWith7 = board.findNeighborsWithValue(coordinate6, '7');
                                            for (Coordinate coordinate7 : neighborsWith7) {
                                                Set<Coordinate> neighborsWith8 = board.findNeighborsWithValue(coordinate7, '8');
                                                for (Coordinate coordinate8 : neighborsWith8) {
                                                    Set<Coordinate> neighborsWith9 = board.findNeighborsWithValue(coordinate8, '9');
                                                    hikingTrailTops.addAll(neighborsWith9);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    numberOfHikingTrailTops += hikingTrailTops.size();
                }
            }
        }

        return String.valueOf(numberOfHikingTrailTops);
    }

}
