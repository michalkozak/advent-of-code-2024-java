package mk.aoc24.day01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;

public class LocationsDistance extends PuzzleSolver {

    protected final List<Integer> leftLocations = new ArrayList<>();
    protected final List<Integer> rightLocations = new ArrayList<>();

    @Override
    protected void parseLines(Stream<String> lines) {
        lines.forEach(this::readLocationsToList);
    }

    protected void readLocationsToList(String line) {
        String[] split = line.split(" {3}");
        leftLocations.add(Integer.parseInt(split[0]));
        rightLocations.add(Integer.parseInt(split[1]));
    }

    @Override
    protected String computeResult() {
        Iterator<Integer> sortedLeftLocationsIterator = leftLocations.stream().sorted().toList().iterator();
        Iterator<Integer> sortedRightLocationsIterator = rightLocations.stream().sorted().toList().iterator();
        int distance = 0;
        while (sortedLeftLocationsIterator.hasNext() && sortedRightLocationsIterator.hasNext()) {
            int leftLocation = sortedLeftLocationsIterator.next();
            int rightLocation = sortedRightLocationsIterator.next();
            distance += Math.abs(leftLocation - rightLocation);
        }
        return String.valueOf(distance);
    }

}
