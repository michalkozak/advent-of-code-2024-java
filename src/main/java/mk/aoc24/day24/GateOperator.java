package mk.aoc24.day24;

import java.util.List;
import mk.aoc24.calculator.Operator;

public enum GateOperator implements Operator<Integer> {

    AND("&", 2, 1, true),
    OR("|", 2, 1, true),
    XOR("^", 2, 1, true);

    private final String syntax;
    private final int nary;
    private final int priority;
    private final boolean leftAssociativity;

    public static GateOperator of(String symbol) {
        return switch (symbol) {
            case "AND" -> AND;
            case "OR" -> OR;
            case "XOR" -> XOR;
            default -> throw new IllegalArgumentException("Unknown symbol: " + symbol);
        };
    }

    GateOperator(String syntax, int nary, int priority, boolean leftAssociativity) {
        if (nary <= 0) {
            throw new IllegalArgumentException("nary must be positive");
        }
        this.syntax = syntax;
        this.nary = nary;
        this.priority = priority;
        this.leftAssociativity = leftAssociativity;
    }

        @Override
        public String syntax() {
            return syntax;
        }

        @Override
        public int nary() {
            return nary;
        }

        @Override
        public int priority() {
            return priority;
        }

        @Override
        public boolean hasLeftAssociativity() {
            return leftAssociativity;
        }

        @Override
        public Integer compute(List<Integer> values) {
            return switch (this) {
                case AND -> values.stream().reduce((a, b) -> a & b).orElseThrow();
                case OR -> values.stream().reduce((a, b) -> a | b).orElseThrow();
                case XOR -> values.stream().reduce((a, b) -> a ^ b).orElseThrow();
            };
        }

}
