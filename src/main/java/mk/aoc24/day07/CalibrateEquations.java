package mk.aoc24.day07;

import static mk.aoc24.helper.MathHelper.power;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.helper.IntBaseHelper;

public class CalibrateEquations extends PuzzleSolver {

    protected List<Long> results = new ArrayList<>();
    protected List<List<Long>> components = new ArrayList<>();
    protected long sum = 0;

    @Override
    protected void parseLines(Stream<String> lines) {
        lines.forEach(this::parseLine);
    }

    private void parseLine(String line) {
        String[] parts = line.split(":");
        results.add(Long.parseLong(parts[0]));
        components.add(Arrays.stream(parts[1].trim().split(" ")).map(Long::parseLong).toList());
    }

    @Override
    protected String computeResult() {
        for (int i = 0; i < results.size(); i++) {
            for (int j = 0; j < power(2, components.get(i).size() - 1, 1); j++) {
                String binary = IntBaseHelper.convertIntToBase(j, 2, components.get(i).size() - 1);
                long computed = components.get(i).getFirst();
                for (int k = 0; k < binary.length(); k++) {
                    if (binary.charAt(k) == '0') {
                        computed += components.get(i).get(k + 1);
                    } else {
                        computed *= components.get(i).get(k + 1);
                    }
                }
                if (computed == results.get(i)) {
                    sum += computed;
                    break;
                }
            }
        }
        return String.valueOf(sum);
    }

}
