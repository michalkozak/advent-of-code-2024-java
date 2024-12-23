package mk.aoc24.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;

public class CorruptedMultiplications extends PuzzleSolver {

    protected Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");

    protected List<Long> products = new ArrayList<>();


    @Override
    protected void parseLines(Stream<String> lines) {
        lines.forEach(this::parseLine);
    }

    @Override
    protected String computeResult() {
        Long result = products.stream().reduce(0L, Long::sum);
        return String.valueOf(result);
    }

    private void parseLine(String line) {
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            MatchResult matchResult = matcher.toMatchResult();
            String multiplication = matchResult.group();
            multiplication = multiplication.substring(4, multiplication.length() - 1);
            String[] factors = multiplication.split(",");
            products.add(Long.parseLong(factors[0]) * Long.parseLong(factors[1]));
        }
    }

}
