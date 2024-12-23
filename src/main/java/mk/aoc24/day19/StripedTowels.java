package mk.aoc24.day19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;

public class StripedTowels extends PuzzleSolver  {

    protected List<String> towels = new ArrayList<>();
    protected List<String> designs = new ArrayList<>();
    protected Map<String, Long> cache = new HashMap<>();

    @Override
    protected void parseLines(Stream<String> lines) {
        Iterator<String> iterator = lines.iterator();
        towels = parseTowels(iterator.next());
        iterator.next();
        while (iterator.hasNext()) {
            designs.add(iterator.next());
        }
    }

    private List<String> parseTowels(String line) {
        return Arrays.stream(line.split(", ")).toList();
    }

    @Override
    protected String computeResult() {
        int count = 0;
        for (String design : designs) {
            if (howManyWayToBuild(design) > 0) {
                count++;
            }
        }
        return String.valueOf(count);
    }

    protected long howManyWayToBuild(String design) {
        if (cache.containsKey(design)) {
            return cache.get(design);
        }
        long result = 0L;
        if (design.isEmpty()) {
            result = 1L;
        }
        for (String towel : towels) {
            if (design.startsWith(towel)) {
                result += howManyWayToBuild(design.substring(towel.length()));
            }
        }
        cache.put(design, result);
        return result;
    }

}
