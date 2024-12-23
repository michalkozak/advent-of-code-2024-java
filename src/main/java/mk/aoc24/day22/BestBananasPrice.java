package mk.aoc24.day22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestBananasPrice extends MonkeyMarket {

    public BestBananasPrice(int steps) {
        super(steps);
    }

    @Override
    protected String computeResult() {
        Map<ConsecutiveChanges, Integer> changesToPricesForAllBuyers = new HashMap<>();
        for (long secret : secrets) {
            List<Integer> prices = countPrices(secret);
            List<Integer> changes = countPriceChanges(prices);
            Map<ConsecutiveChanges, Integer> changesToPrices = mapChangesToPrices(changes, prices);
            for (Map.Entry<ConsecutiveChanges, Integer> entry : changesToPrices.entrySet()) {
                changesToPricesForAllBuyers.computeIfPresent(entry.getKey(), (k, v) -> v + entry.getValue());
                changesToPricesForAllBuyers.computeIfAbsent(entry.getKey(), k -> entry.getValue());
            }
        }

        int max = changesToPricesForAllBuyers.values().stream().max(Integer::compareTo).orElseThrow();
        return String.valueOf(max);
    }


    private List<Integer> countPrices(Long secret) {
        List<Integer> prices = new ArrayList<>();
        prices.add((int) (secret % 10));
        for (int i = 0; i < steps; i++) {
            secret = computeSecretForOneStep(secret);
            prices.add((int) (secret % 10));
        }
        return prices;
    }

    private List<Integer> countPriceChanges(List<Integer> prices) {
        List<Integer> changes = new ArrayList<>();
        for (int i = 0; i < prices.size() - 1; i++) {
            changes.add(prices.get(i + 1) - prices.get(i));
        }
        return changes;
    }

    private Map<ConsecutiveChanges, Integer> mapChangesToPrices(List<Integer> changes, List<Integer> prices) {
        Map<ConsecutiveChanges, Integer> result = new HashMap<>();
        for (int i = 0; i < changes.size() - 3; i++) {
            ConsecutiveChanges consecutiveChanges = new ConsecutiveChanges(changes.get(i), changes.get(i + 1), changes.get(i + 2), changes.get(i + 3));
            result.putIfAbsent(consecutiveChanges, prices.get(i + 4));
        }
        return result;
    }

}
