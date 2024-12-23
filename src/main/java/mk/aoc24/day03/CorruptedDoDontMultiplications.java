package mk.aoc24.day03;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CorruptedDoDontMultiplications extends CorruptedMultiplications {

    private static final String DO_TERM = "do()";
    private static final String DONT_TERM = "don't()";

    @Override
    protected void parseLines(Stream<String> lines) {
        parseJoinedLine(lines.collect(Collectors.joining()));
    }

    private void parseJoinedLine(String line) {
        int prevLastDoIndex = 0;
        int prevLastDontIndex = 0;
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            MatchResult matchResult = matcher.toMatchResult();
            int lastIndexOfDo = findLastIndexOf(line, DO_TERM, prevLastDoIndex, matchResult.start());
            if (lastIndexOfDo != -1) {
                prevLastDoIndex = lastIndexOfDo;
            }
            int lastIndexOfDont = findLastIndexOf(line, DONT_TERM, prevLastDontIndex, matchResult.start());
            if (lastIndexOfDont != -1) {
                prevLastDontIndex = lastIndexOfDont;
            }
            if (multiplicationIsValid(lastIndexOfDo, lastIndexOfDont)) {
                String multiplication = matchResult.group();
                multiplication = multiplication.substring(4, multiplication.length() - 1);
                String[] factors = multiplication.split(",");
                products.add(Long.parseLong(factors[0]) * Long.parseLong(factors[1]));
            }
        }
    }

    private int findLastIndexOf(String line, String term, int start, int end) {
        int lastIndex = -1;
        int idx = line.indexOf(term, start, end);
        while (idx != -1) {
            lastIndex = idx;
            idx = line.indexOf(term, idx + 1, end);
        }
        return lastIndex;
    }

    private boolean multiplicationIsValid(int lastIndexOfDo, int lastIndexOfDont) {
        if (lastIndexOfDo == -1 && lastIndexOfDont == -1) {
            return true;
        } else if (lastIndexOfDo == -1) {
            return false;
        } else if (lastIndexOfDont == -1) {
            return true;
        } else {
            return lastIndexOfDo > lastIndexOfDont;
        }
    }

}
