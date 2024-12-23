package mk.aoc24.day13;

import java.util.stream.Stream;

public class ConvertedClawMachine extends ClawMachine {

    private static final long CONVERSION = 10000000000000L;

    @Override
    protected void parseLines(Stream<String> lines) {
        super.parseLines(lines);
        for (Instruction instruction : machineInstructions) {
            instruction.p = new Point(instruction.p.x() + CONVERSION, instruction.p.y() + CONVERSION);
        }
    }

}
