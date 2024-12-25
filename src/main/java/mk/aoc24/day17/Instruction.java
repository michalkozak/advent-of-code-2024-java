package mk.aoc24.day17;

import static mk.aoc24.helper.MathHelper.power;

import java.util.List;
import java.util.Optional;

public enum Instruction {
    ADV(0),
    BXL(1),
    BST(2),
    JNZ(3),
    BXC(4),
    OUT(5),
    BDV(6),
    CDV(7);

    private int opcode;

    public static Instruction of(int opcode) {
        return switch (opcode) {
            case 0 -> ADV;
            case 1 -> BXL;
            case 2 -> BST;
            case 3 -> JNZ;
            case 4 -> BXC;
            case 5 -> OUT;
            case 6 -> BDV;
            case 7 -> CDV;
            default -> throw new IllegalArgumentException("Unknown opcode: " + opcode);
        };
    }

    Instruction(int opcode) {
        this.opcode = opcode;
    }

    public Optional<Integer> compute(int operand, Registers reg, List<Integer> output) {
        return switch (this) {
            case ADV -> {
                reg.a(compute(operand, reg));
                yield Optional.empty();
            }
            case BXL, BST, BXC, BDV -> {
                reg.b(compute(operand, reg));
                yield Optional.empty();
            }
            case JNZ -> {
                if (compute(operand, reg) == 0) {
                    yield Optional.empty();
                } else {
                    yield Optional.of(operand);
                }
            }
            case OUT -> {
                output.add((int) compute(operand, reg));
                yield Optional.empty();
            }
            case CDV -> {
                reg.c(compute(operand, reg));
                yield Optional.empty();
            }
        };
    }

    private long compute(int operand, Registers reg) {
        return switch (this) {
            case ADV, BDV, CDV -> reg.a() / power(2, (int) combo(operand, reg), 1);
            case BXL -> reg.b() ^ operand;
            case BST, OUT -> combo(operand, reg) % 8;
            case JNZ -> reg.a();
            case BXC -> reg.b() ^ reg.c();
        };
    }


    private long combo(int operand, Registers reg) {
        if (operand >= 0 && operand <= 3) {
            return operand;
        }
        if (operand == 4) {
            return reg.a();
        }
        if (operand == 5) {
            return reg.b();
        }
        if (operand == 6) {
            return reg.c();
        } else {
            throw new IllegalArgumentException("Illegal combo operand " + operand);
        }
    }

}
