package mk.aoc24.day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;

public class ThreeBitComputer extends PuzzleSolver {

    protected Registers reg = new Registers();
    protected List<Integer> program;

    @Override
    protected void parseLines(Stream<String> lines) {
        Iterator<String> lineIterator = lines.iterator();
        reg.a(parseReg(lineIterator.next()));
        reg.b(parseReg(lineIterator.next()));
        reg.c(parseReg(lineIterator.next()));
        lineIterator.next();
        program = parseProgram(lineIterator.next());
    }

    private int parseReg(String line) {
        int idx = line.indexOf(": ");
        return Integer.parseInt(line.substring(idx + 2));
    }


    private List<Integer> parseProgram(String line) {
        int idx = line.indexOf(": ");
        return Arrays.stream(line.substring(idx + 2).split(",")).map(Integer::parseInt).toList();
    }

    @Override
    protected String computeResult() {
        List<Integer> output = execute();
        return output.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    protected List<Integer> execute() {
        List<Integer> output = new ArrayList<>();
        int pointer = 0;
        while (pointer < program.size()) {
            Instruction instr = Instruction.of(program.get(pointer));
            Optional<Integer> result = instr.compute(program.get(pointer + 1), reg, output);
            if (result.isPresent()) {
                pointer = result.get();
            } else {
                pointer += 2;
            }
        }
        return output;
    }

}
