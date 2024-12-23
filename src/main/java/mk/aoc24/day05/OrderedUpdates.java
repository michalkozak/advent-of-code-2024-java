package mk.aoc24.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;

public class OrderedUpdates extends PuzzleSolver {

    protected Map<Integer, Set<Integer>> rules = new HashMap<>();
    protected List<List<Integer>> updates = new ArrayList<>();

    @Override
    protected void parseLines(Stream<String> lines) {
        boolean parseRules = true;
        for (String line : lines.toList()) {
            if (line.isEmpty()) {
                parseRules = false;
                continue;
            }
            if (parseRules) {
                parseRule(line);
            } else {
                parsePages(line);
            }
        }
    }

    @Override
    protected String computeResult() {
        int sumOfMiddleElements = updates.stream()
            .filter(this::isOrderCorrect)
            .map(this::getMiddleElement)
            .reduce(0, Integer::sum);
        return String.valueOf(sumOfMiddleElements);
    }


    protected boolean isOrderCorrect(List<Integer> update) {
        for (int i = 0; i < update.size(); i++) {
            for (int j = i + 1; j < update.size(); j++) {
                Integer next = update.get(j);
                Integer prev = update.get(i);
                if (rules.containsKey(next) && rules.get(next).contains(prev)) {
                    return false;
                }
            }
        }
        return true;
    }

    protected Integer getMiddleElement(List<Integer> list) {
        return list.get(list.size() / 2);
    }

    private void parseRule(String line) {
        String[] pages = line.split("\\|");
        Integer before = Integer.parseInt(pages[0]);
        Integer after = Integer.parseInt(pages[1]);
        rules.putIfAbsent(before, new HashSet<>());
        rules.get(before).add(after);
    }

    private void parsePages(String line) {
        String[] pages = line.split(",");
        updates.add(Arrays.stream(pages).map(Integer::parseInt).toList());
    }

}
