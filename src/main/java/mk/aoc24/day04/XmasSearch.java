package mk.aoc24.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;

public class XmasSearch extends PuzzleSolver {

    protected List<List<Character>> characters = new ArrayList<>();
    protected long numberOfXmas = 0;

    @Override
    protected void parseLines(Stream<String> lines) {
        lines.forEach(this::parseLine);
    }

    @Override
    protected String computeResult() {
        countHorizontally('X', 'M', 'A', 'S');
        countHorizontally('S', 'A', 'M', 'X');
        countVertically('X', 'M', 'A', 'S');
        countVertically('S', 'A', 'M', 'X');
        countDiagonallyRight('X', 'M', 'A', 'S');
        countDiagonallyRight('S', 'A', 'M', 'X');
        countDiagonallyLeft('X', 'M', 'A', 'S');
        countDiagonallyLeft('S', 'A', 'M', 'X');
        return String.valueOf(numberOfXmas);
    }

    private void parseLine(String line) {
        List<Character> row = new ArrayList<>();
        line.chars().forEach(c -> row.add((char) c));
        characters.add(row);
    }

    private void countHorizontally(char ch1, char ch2, char ch3, char ch4) {
        for (int i = 0; i < characters.size(); i++) {
            for (int j = 0; j < characters.get(i).size() - 3; j++) {
                if (characters.get(i).get(j) == ch1 &&  characters.get(i).get(j+1) == ch2
                    && characters.get(i).get(j+2) == ch3 && characters.get(i).get(j+3) == ch4) {
                    numberOfXmas++;
                }
            }
        }
    }

    private void countVertically(char ch1, char ch2, char ch3, char ch4) {
        for (int i = 0; i < characters.size() - 3; i++) {
            for (int j = 0; j < characters.get(i).size(); j++) {
                if (characters.get(i).get(j) == ch1 && characters.get(i+1).get(j) == ch2
                    && characters.get(i+2).get(j) == ch3 && characters.get(i+3).get(j) == ch4) {
                    numberOfXmas++;
                }
            }
        }
    }

    private void countDiagonallyRight(char ch1, char ch2, char ch3, char ch4) {
        for (int i = 0; i < characters.size() - 3; i++) {
            for (int j = 0; j < characters.get(i).size() - 3; j++) {
                if (characters.get(i).get(j) == ch1 && characters.get(i+1).get(j+1) == ch2
                    && characters.get(i+2).get(j+2) == ch3 && characters.get(i+3).get(j+3) == ch4) {
                    numberOfXmas++;
                }
            }
        }
    }

    private void countDiagonallyLeft(char ch1, char ch2, char ch3, char ch4) {
        for (int i = 3; i < characters.size(); i++) {
            for (int j = 0; j < characters.get(i).size() - 3; j++) {
                if (characters.get(i).get(j) == ch1 && characters.get(i-1).get(j+1) == ch2
                    && characters.get(i-2).get(j+2) == ch3 && characters.get(i-3).get(j+3) == ch4) {
                    numberOfXmas++;
                }
            }
        }
    }

}
