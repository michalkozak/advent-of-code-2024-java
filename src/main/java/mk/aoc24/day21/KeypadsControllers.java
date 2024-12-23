package mk.aoc24.day21;

import static java.util.Map.entry;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;

public class KeypadsControllers extends PuzzleSolver {

    protected List<String> doorCodes;

    @Override
    protected void parseLines(Stream<String> lines) {
        doorCodes = lines.toList();
    }

    @Override
    protected String computeResult() {
        long sum = 0;
        for (String doorCode : doorCodes) {
            sum += findComplexity(doorCode, 2);
        }
        return String.valueOf(sum);
    }

    protected long findComplexity(String doorCode, int numberOfDirectionalRobots) {
        int number = Integer.parseInt(doorCode.substring(0, doorCode.length() - 1));
        List<NumericalKeypad> code = new ArrayList<>();
        for (char c : doorCode.toCharArray()) {
            code.add(NumericalKeypad.of(c));
        }
        long min = findMinLengthForNumerical(code, numberOfDirectionalRobots - 1);
        return min * number;
    }

    protected long findMinLengthForNumerical(List<NumericalKeypad> code, int level) {
        long result = 0;
        code.addFirst(NumericalKeypad.ACTIVATE);
        for (int i = 0; i < code.size() - 1; i++) {
            NumericalKeypad start = code.get(i);
            NumericalKeypad end = code.get(i + 1);
            Set<List<DirectionalKeypad>> shortestPaths = findShortestPaths(start, end);
            long min = Long.MAX_VALUE;
            for (List<DirectionalKeypad> shortestPath : shortestPaths) {
                min = Math.min(min, findMinLengthForDirectional(shortestPath, level));
            }
            result += min;
        }
        return result;
    }

    protected long findMinLengthForDirectional(List<DirectionalKeypad> code, int level) {
        long result = 0;
        code.addFirst(DirectionalKeypad.ACTIVATE);
        for (int i = 0; i < code.size() - 1; i++) {
            DirectionalKeypad start = code.get(i);
            DirectionalKeypad end = code.get(i + 1);
            Set<List<DirectionalKeypad>> shortestPaths = findShortestPaths(start, end);
            if (level == 0) {
                result += minPathLength(shortestPaths);
            } else {
                long min = Long.MAX_VALUE;
                for (List<DirectionalKeypad> shortestPath : shortestPaths) {
                    min = Math.min(min, findMinLengthForDirectional(shortestPath, level - 1));
                }
                result += min;
            }
        }
        return result;
    }

    private long minPathLength(Set<List<DirectionalKeypad>> paths) {
        long result = Long.MAX_VALUE;
        for (List<DirectionalKeypad> path : paths) {
            result = Math.min(result, path.size());
        }
        return result;
    }

    private <T extends Keypad<T>> Set<List<DirectionalKeypad>> findShortestPaths(T start, T end) {
        Deque<Map.Entry<T, List<DirectionalKeypad>>> deque = new ArrayDeque<>();
        deque.add(entry(start, new ArrayList<>()));
        Integer min = null;
        Set<List<DirectionalKeypad>> shortestPaths = new HashSet<>();
        while (!deque.isEmpty()) {
            Entry<T, List<DirectionalKeypad>> d = deque.poll();
            if (d.getKey().equals(end)) {
                if (min == null) {
                    min = d.getValue().size();
                }
                if (d.getValue().size() == min) {
                    List<DirectionalKeypad> shortestPath = new ArrayList<>(d.getValue());
                    shortestPath.add(DirectionalKeypad.ACTIVATE);
                    shortestPaths.add(shortestPath);
                }
                continue;
            }
            if (min != null && d.getValue().size() >= min) {
                continue;
            }
            for (Entry<T, DirectionalKeypad> neighbor : d.getKey().movesToNeighbors().entrySet()) {
                ArrayList<DirectionalKeypad> path = new ArrayList<>(d.getValue());
                path.add(neighbor.getValue());
                deque.offer(entry(neighbor.getKey(), path));
            }
        }
        return shortestPaths;
    }

}
