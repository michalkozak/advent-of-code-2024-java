package mk.aoc24.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.helper.CollectorHelper;

public class ClawMachine extends PuzzleSolver {

    protected List<Instruction> machineInstructions = new ArrayList<>();

    @Override
    protected void parseLines(Stream<String> lines) {
        lines.collect(CollectorHelper.toBlocks(4)).forEach(this::parseBlock);
    }

    private void parseBlock(List<String> block) {
        Point buttonA = null;
        Point buttonB = null;
        for (String line : block) {
            if (!line.isBlank()) {
                if (line.startsWith("Button A")) {
                    buttonA = parseButton(line);
                } else if (line.startsWith("Button B")) {
                    buttonB = parseButton(line);
                } else if (line.startsWith("Prize")) {
                    machineInstructions.add(new Instruction(buttonA, buttonB, parsePrize(line)));
                }
            }
        }
    }

    private Point parseButton(String line) {
        int idx0 = line.indexOf("X+");
        int idx1 = line.indexOf(",");
        int idx2 = line.indexOf("Y+");
        return new Point(Integer.parseInt(line.substring(idx0 + 2, idx1)), Integer.parseInt(line.substring(idx2 + 2)));
    }

    private Point parsePrize(String line) {
        int idx0 = line.indexOf("X=");
        int idx1 = line.indexOf(",");
        int idx2 = line.indexOf("Y=");
        return new Point(Integer.parseInt(line.substring(idx0 + 2, idx1)), Integer.parseInt(line.substring(idx2 + 2)));
    }


    @Override
    protected String computeResult() {
        long cost = 0;

        for (Instruction inst : machineInstructions) {
/*
            from the equations
            a * inst.a.x() + b * inst.b.x() = inst.p.x()
            a * inst.a.y() + b * inst.b.y() = inst.p.y()
            derive
                inst.a.x() * inst.p.y() - inst.a.y() * inst.p.x()
            b = -------------------------------------------------
                inst.a.x() * inst.b.y() - inst.a.y() * inst.b.x()
*/
            long d = inst.a.x() * inst.b.y() - inst.a.y() * inst.b.x();
            long db = inst.a.x() * inst.p.y() - inst.a.y() * inst.p.x();
            if (db % d != 0) {
                continue;
            }
            long b = db / d;
/*
            and derive
                inst.b.y() * inst.p.x() - inst.b.x() * inst.p.y()
            b = -------------------------------------------------
                inst.a.x() * inst.b.y() - inst.a.y() * inst.b.x()
*/
            long da = inst.b.y() * inst.p.x() - inst.b.x() * inst.p.y();
            if (da % d != 0) {
                continue;
            }
            long a = da / d;
            cost += 3 * a + b;
        }

        return String.valueOf(cost);
    }


}
