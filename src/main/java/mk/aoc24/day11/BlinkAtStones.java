package mk.aoc24.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;

public class BlinkAtStones extends PuzzleSolver {

    protected List<Integer> stonesNumbers = new ArrayList<>();
    protected Map<CacheEntry, Long> cache = new HashMap<>();

    @Override
    protected void parseLines(Stream<String> lines) {
        String[] initialStones = lines.collect(Collectors.joining()).split(" ");
        Arrays.stream(initialStones).forEach(s -> stonesNumbers.add(Integer.parseInt(s)));
    }

    @Override
    protected String computeResult() {
        return String.valueOf(computeResultFor(25));
    }

    protected Long computeResultFor(int steps) {
        return stonesNumbers.stream().map(stonesNumber -> count(stonesNumber, steps)).reduce(0L, Long::sum);
    }

    protected long count(long stoneNumber, int steps) {
        CacheEntry cacheEntry = new CacheEntry(stoneNumber, steps);
        if (cache.containsKey(cacheEntry)) {
            return cache.get(cacheEntry);
        }
        long result;
        if (steps == 0) {
            result = 1L;
        } else {
            if (stoneNumber == 0) {
                result = count(1, steps - 1);
            } else {
                String stone = String.valueOf(stoneNumber);
                if (stone.length() % 2 == 0) {
                    String stone1 = stone.substring(0, stone.length() / 2);
                    String stone2 = stone.substring(stone.length() / 2);
                    result = count(Long.parseLong(stone1), steps - 1) + count(Long.parseLong(stone2), steps - 1);
                } else {
                    result = count(stoneNumber * 2024, steps - 1);
                }
            }
        }
        cache.put(cacheEntry, result);
        return result;
    }


}
