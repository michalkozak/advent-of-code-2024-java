package mk.aoc24.day24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.calculator.ExpressionElement;
import mk.aoc24.calculator.ReversePolishNotation;

public class LogicCircuit extends PuzzleSolver {

    private static final Pattern MID_WIRE_PATTERN = Pattern.compile("(\\w{3}) (\\w{2,3}) (\\w{3}) -> (\\w{3})$");
    private static final Pattern END_WIRE_PATTERN = Pattern.compile("(\\w{3}) (\\w{2,3}) (\\w{3}) -> z(\\d{2})$");

    protected Map<String, Integer> initials = new HashMap<>();
    protected Map<String, String> midWires = new HashMap<>();
    protected Map<String, String> endWires = new HashMap<>();

    @Override
    protected void parseLines(Stream<String> lines) {
        List<String> list = lines.toList();
        parseInitialValues(list.stream().takeWhile(l -> !l.isEmpty()));
        parseWires(list.stream().dropWhile(l -> !l.isEmpty()));
    }

    private void parseInitialValues(Stream<String> lines) {
        lines.forEach(this::parseInitialValue);
    }

    private void parseInitialValue(String line) {
        String[] parts = line.split(": ");
        initials.put(parts[0], Integer.parseInt(parts[1]));
    }

    private void parseWires(Stream<String> lines) {
        lines.forEach(this::parseWire);
    }

    private void parseWire(String line) {
        Matcher endMatcher = END_WIRE_PATTERN.matcher(line);
        if (endMatcher.matches()) {
            GateOperator gateOperator = GateOperator.of(endMatcher.group(2));
            String expr = "( " + endMatcher.group(1) + " " + gateOperator.syntax() + " " + endMatcher.group(3) + " )";
            endWires.put("z" + endMatcher.group(4), expr);
        } else {
            Matcher midMatcher = MID_WIRE_PATTERN.matcher(line);
            if (midMatcher.matches()) {
                GateOperator gateOperator = GateOperator.of(midMatcher.group(2));
                String expr = midMatcher.group(1) + " " + gateOperator.syntax() + " " + midMatcher.group(3);
                midWires.put(midMatcher.group(4), expr);
            }
        }
    }

    @Override
    protected String computeResult() {
        String binary = computeCircuit(initials, midWires, endWires);
        Long result = Long.parseLong(binary, 2);
        return String.valueOf(result);
    }

    protected String computeCircuit(Map<String, Integer> data, Map<String, String> midGates, Map<String, String> endGates) {
        Map<String, Integer> endGatesResults = new HashMap<>();
        for (Map.Entry<String, String> endGateExpr : endGates.entrySet()) {
            Integer result = computeEndGate(data, midGates, endGateExpr.getValue());
            endGatesResults.put(endGateExpr.getKey(), result);
        }
        StringBuilder binaryResult = new StringBuilder();
        endGatesResults.keySet().stream().sorted().forEach(i -> binaryResult.append(endGatesResults.get(i)));
        return binaryResult.reverse().toString();
    }

    protected Integer computeEndGate(Map<String, Integer> data, Map<String, String> midGates, String endGateExpr) {
        int endGateExprLength = 0;
        while (endGateExprLength < endGateExpr.length()) {
            endGateExprLength = endGateExpr.length();
            for (Map.Entry<String, String> midGateExpr : midGates.entrySet()) {
                if (endGateExpr.contains(midGateExpr.getKey())) {
                    endGateExpr = endGateExpr.replace(midGateExpr.getKey(), "( " + midGateExpr.getValue() + " )");
                }
            }
        }

        for (Entry<String, Integer> init : data.entrySet()) {
            if (endGateExpr.contains(init.getKey())) {
                endGateExpr = endGateExpr.replace(init.getKey(), String.valueOf(init.getValue()));
            }
        }

        List<ExpressionElement<Integer>> expression = parse(endGateExpr);
        return ReversePolishNotation.evaluate(expression, true);
    }

    protected List<ExpressionElement<Integer>> parse(String expression) {
        List<ExpressionElement<Integer>> result = new ArrayList<>();
        String[] elements = expression.split(" ");
        for (String element : elements) {
            switch (element) {
                case "(" -> result.add(ExpressionElement.leftParenthesis());
                case ")" -> result.add(ExpressionElement.rightParenthesis());
                case "&" -> result.add(ExpressionElement.operator(GateOperator.AND));
                case "|" -> result.add(ExpressionElement.operator(GateOperator.OR));
                case "^" -> result.add(ExpressionElement.operator(GateOperator.XOR));
                default -> result.add(ExpressionElement.value(Integer.parseInt(element)));
            }
        }
        return result;
    }

}
