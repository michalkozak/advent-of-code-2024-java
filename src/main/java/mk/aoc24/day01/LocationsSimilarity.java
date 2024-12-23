package mk.aoc24.day01;

import java.util.HashMap;
import java.util.Map;

public class LocationsSimilarity extends LocationsDistance {

    @Override
    protected String computeResult() {
        Map<Integer, Long> leftLocationOccurrences = new HashMap<>();
        for (Integer leftLocation : leftLocations) {
            leftLocationOccurrences.computeIfAbsent(leftLocation, location ->
                rightLocations.stream().filter(el -> el.equals(location)).count());
        }
        long similarity = 0;
        for (Integer leftLocation : leftLocations) {
            similarity += leftLocation * leftLocationOccurrences.get(leftLocation);
        }
        return String.valueOf(similarity);
    }

}
