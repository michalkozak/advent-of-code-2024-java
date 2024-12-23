package mk.aoc24.day10;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import mk.aoc24.board.Coordinate;

public class DistinctHikingTrailSearch extends HikingTrailSearch {

    protected Set<List<Coordinate>> hikingTrails = new HashSet<>();

    @Override
    protected String computeResult() {
        for (int i = 0; i < board.width(); i++) {
            for (int j = 0; j < board.height(); j++) {
                if (board.get(i, j) == '0') {
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
                                                    for (Coordinate coordinate9 : neighborsWith9) {
                                                        hikingTrails.add(List.of(new Coordinate(i, j),
                                                            coordinate1, coordinate2, coordinate3, coordinate4, coordinate5,
                                                            coordinate6, coordinate7, coordinate8, coordinate9));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return String.valueOf(hikingTrails.size());
    }

}
