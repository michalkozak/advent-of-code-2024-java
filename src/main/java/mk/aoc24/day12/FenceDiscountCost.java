package mk.aoc24.day12;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import mk.aoc24.board.Coordinate;

public class FenceDiscountCost extends FenceCost {

    @Override
    protected String computeResult() {
        int cost = 0;

        for (Entry<String, Set<Coordinate>> area : areas.entrySet()) {
            Set<Coordinate> upFences = new HashSet<>();
            Set<Coordinate> downFences = new HashSet<>();
            Set<Coordinate> leftFences = new HashSet<>();
            Set<Coordinate> rightFences = new HashSet<>();

            for (Coordinate coordinate : area.getValue()) {
                Coordinate neighborUp = new Coordinate(coordinate.x(), coordinate.y() - 1);
                Coordinate neighborDown = new Coordinate(coordinate.x(), coordinate.y() + 1);
                Coordinate neighborLeft = new Coordinate(coordinate.x() - 1, coordinate.y());
                Coordinate neighborRight = new Coordinate(coordinate.x() + 1, coordinate.y());
                Set<Coordinate> neighbors = board.findNeighborsWithValue(coordinate, area.getKey().charAt(0));
                if (!neighbors.contains(neighborUp)) {
                    upFences.add(coordinate);
                }
                if (!neighbors.contains(neighborDown)) {
                    downFences.add(coordinate);
                }
                if (!neighbors.contains(neighborLeft)) {
                    leftFences.add(coordinate);
                }
                if (!neighbors.contains(neighborRight)) {
                    rightFences.add(coordinate);
                }
            }

            int length = groupByVerticalCoordinateAndCountHorizontalFences(upFences);
            length += groupByVerticalCoordinateAndCountHorizontalFences(downFences);
            length += groupByHorizontalCoordinateAndCountVerticalFences(leftFences);
            length += groupByHorizontalCoordinateAndCountVerticalFences(rightFences);

            cost += area.getValue().size() * length;
        }

        return String.valueOf(cost);
    }

    private int groupByVerticalCoordinateAndCountHorizontalFences(Set<Coordinate> horizontalFences) {
        Map<Integer, List<Coordinate>> horizontalFencesSides = horizontalFences.stream().collect(Collectors.groupingBy(Coordinate::y));
        int numberOfFences = 0;
        for (List<Coordinate> horizontalCoordinates: horizontalFencesSides.values()) {
            numberOfFences += countHorizontalFences(horizontalCoordinates);
        }
        return numberOfFences;
    }

    private int groupByHorizontalCoordinateAndCountVerticalFences(Set<Coordinate> verticalFences) {
        Map<Integer, List<Coordinate>> verticalFencesSides = verticalFences.stream().collect(Collectors.groupingBy(Coordinate::x));
        int numberOfFences = 0;
        for (List<Coordinate> verticalCoordinates: verticalFencesSides.values()) {
            numberOfFences += countVerticalFences(verticalCoordinates);
        }
        return numberOfFences;
    }


    private int countHorizontalFences(List<Coordinate> coordinates) {
        List<Coordinate> sortedCoordinates = coordinates.stream().sorted(Comparator.comparingInt(Coordinate::x)).toList();
        int numberOfFences = 0;
        int x = sortedCoordinates.getFirst().x();
        int k = -1;
        for (Coordinate coordinate : sortedCoordinates) {
            k++;
            if (x + k != coordinate.x()) {
                numberOfFences++;
                x = coordinate.x();
                k = 0;
            }
        }
        return numberOfFences + 1;
    }

    private int countVerticalFences(List<Coordinate> coordinates) {
        List<Coordinate> sortedCoordinates = coordinates.stream().sorted(Comparator.comparingInt(Coordinate::y)).toList();
        int numberOfFences = 0;
        int y = sortedCoordinates.getFirst().y();
        int k = -1;
        for (Coordinate coordinate : sortedCoordinates) {
            k++;
            if (y + k != coordinate.y()) {
                numberOfFences++;
                y = coordinate.y();
                k = 0;
            }
        }
        return numberOfFences + 1;
    }

}
