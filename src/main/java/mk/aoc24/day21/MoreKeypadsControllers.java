package mk.aoc24.day21;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MoreKeypadsControllers extends KeypadsControllers {

    private final Map<CacheEntry, Long> directionalCache = new HashMap<>();

    @Override
    protected String computeResult() {
        long sum = 0;
        for (String doorCode : doorCodes) {
            sum += findComplexity(doorCode, 25);
        }
        return String.valueOf(sum);
    }

    @Override
    protected long findMinLengthForDirectional(List<DirectionalKeypad> code, int level) {
        CacheEntry cacheEntry = new CacheEntry(
            code.stream().map(DirectionalKeypad::code).map(String::valueOf).collect(Collectors.joining()),
            level);
        if (directionalCache.containsKey(cacheEntry)) {
            return directionalCache.get(cacheEntry);
        }
        long result = super.findMinLengthForDirectional(code, level);
        directionalCache.put(cacheEntry, result);
        return result;
    }

}
