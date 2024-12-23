package mk.aoc24.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;

public class ReportSafety extends PuzzleSolver {

    protected List<Report> reports;

    @Override
    protected void parseLines(Stream<String> lines) {
        reports = lines.map(this::parseLine).toList();
    }

    @Override
    protected String computeResult() {
        long numberOfSafetyReports = reports.stream().map(r -> findDifferences(r.levels()))
            .filter(this::areDifferencesSafe).count();
        return String.valueOf(numberOfSafetyReports);
    }

    protected Report parseLine(String line) {
        return new Report(Arrays.stream(line.split(" ")).map(Integer::parseInt).toList());
    }

    protected List<Integer> findDifferences(List<Integer> levels) {
        List<Integer> diffs = new ArrayList<>();
        Integer level = levels.getFirst();
        for (int i = 1; i < levels.size(); i++) {
            diffs.add(levels.get(i) - level);
            level = levels.get(i);
        }
        return diffs;
    }

    protected boolean areDifferencesSafe(List<Integer> diffs) {
        return diffs.stream().allMatch(d -> (d < 0 && d >= -3))
            || diffs.stream().allMatch(d -> (d > 0 && d <= 3));
    }

}
