package mk.aoc24.day24;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdditionSystem extends LogicCircuit {

    private static final Random RANDOM = new Random();

    @Override
    protected String computeResult() {
        List<String> gatesToSwap = findGatesToSwap();
        String result = gatesToSwap.stream().sorted().collect(Collectors.joining(","));

        fixAdditionCircuit(gatesToSwap);

        long x = readLong(getInitialsOfType("x"));
        long y = readLong(getInitialsOfType("y"));
        long z = x + y;

        String binary = computeCircuit(initials, midWires, endWires);
        long c = Long.parseLong(binary, 2);
        assert c == z;

        Map<String, Integer> randX = randInit("x");
        x = readLong(randX);
        Map<String, Integer> randY = randInit("y");
        y = readLong(randY);
        z = x + y;

        Map<String, Integer> randXY = new HashMap<>();
        randXY.putAll(randX);
        randXY.putAll(randY);
        binary = computeCircuit(randXY, midWires, endWires);

        c = Long.parseLong(binary, 2);
        assert c == z;

        return result;
    }

    private void fixAdditionCircuit(List<String> gatesToSwap) {
        int toSwapSize = gatesToSwap.size();
        while (toSwapSize > 0) {
            for (int i = 1; i < gatesToSwap.size(); i++) {
                String gate1 = gatesToSwap.getFirst();
                String gate2 = gatesToSwap.get(i);
                swapGates(gate1, gate2);
                List<String> toSwap = findGatesToSwap();
                if (toSwap.size() == toSwapSize - 2) {
                    toSwapSize = toSwap.size();
                    gatesToSwap.remove(gate1);
                    gatesToSwap.remove(gate2);
                    break;
                } else {
                    swapGates(gate1, gate2);
                }
            }
        }
    }

    private void swapGates(String head1, String head2) {
        Map<String, String> gates1;
        Map<String, String> gates2;
        if (head1.startsWith("z")) {
            gates1 = endWires;
        } else {
            gates1 = midWires;
        }
        if (head2.startsWith("z")) {
            gates2 = endWires;
        } else {
            gates2 = midWires;
        }
        String endExpr1 = gates1.remove(head1);
        gates1.put(head1, gates2.remove(head2));
        gates2.put(head2, endExpr1);
    }

    private List<String> findGatesToSwap() {
        Set<String> gatesToSwap = new HashSet<>();
        gatesToSwap.addAll(findEndGatesWithoutXor());
        gatesToSwap.addAll(findMidGatesWithXorButNoInitialsOrEnds());
        gatesToSwap.addAll(findMidGatesWithAndAndToExpandAndOrXor());
        gatesToSwap.addAll(findMidGatesWithXorAndToExpandOr());
        return new ArrayList<>(gatesToSwap);
    }

    private Set<String> findEndGatesWithoutXor() {
        String endGateHead = endWires.keySet().stream().max(Comparator.naturalOrder()).orElseThrow();
        Set<String> result = new HashSet<>();
        for (Map.Entry<String, String> endGateExpr : endWires.entrySet()) {
            if (!endGateExpr.getValue().contains("^") && !endGateExpr.getKey().equals(endGateHead)) {
                result.add(endGateExpr.getKey());
            }
        }
        return result;
    }

    private Set<String> findMidGatesWithXorButNoInitialsOrEnds() {
        Set<String> result = new HashSet<>();
        for (Map.Entry<String, String> midGateExpr : midWires.entrySet()) {
            if (midGateExpr.getValue().contains("^")) {
                if (!midGateExpr.getValue().startsWith("x") && !midGateExpr.getValue().startsWith("y") && !midGateExpr.getValue().startsWith("z")) {
                    result.add(midGateExpr.getKey());
                }
            }
        }
        return result;
    }

    private Set<String> findMidGatesWithAndAndToExpandAndOrXor() {
        Set<String> result = new HashSet<>();
        for (Map.Entry<String, String> midGateExpr : midWires.entrySet()) {
            if (midGateExpr.getValue().contains("&") && !midGateExpr.getValue().contains("x00") && !midGateExpr.getValue().contains("y00")) {
                for (String gateExpr : Stream.of(midWires.values(), endWires.values()).flatMap(Collection::stream).toList()) {
                    if (gateExpr.contains(midGateExpr.getKey()) && (gateExpr.contains("&") || gateExpr.contains("^"))) {
                        result.add(midGateExpr.getKey());
                    }
                }
            }
        }
        return result;
    }

    private Set<String> findMidGatesWithXorAndToExpandOr() {
        Set<String> result = new HashSet<>();
        for (Map.Entry<String, String> midGateExpr : midWires.entrySet()) {
            if (midGateExpr.getValue().contains("^")) {
                for (String gateExpr : Stream.of(midWires.values(), endWires.values()).flatMap(Collection::stream).toList()) {
                    if (gateExpr.contains(midGateExpr.getKey()) && gateExpr.contains("|")) {
                        result.add(midGateExpr.getKey());
                    }
                }
            }
        }
        return result;
    }


    private Map<String, Integer> getInitialsOfType(String type) {
        Map<String, Integer> initialsType = new HashMap<>();
        for (Map.Entry<String, Integer> initial : initials.entrySet()) {
            if (initial.getKey().startsWith(type)) {
                initialsType.put(initial.getKey().substring(1), initial.getValue());
            }
        }
        return initialsType;
    }

    private long readLong(Map<String, Integer> inits) {
        StringBuilder binaryBuilder = new StringBuilder();
        inits.keySet().stream().sorted().forEach(i -> binaryBuilder.append(inits.get(i)));
        String binary = binaryBuilder.reverse().toString();
        return Long.parseLong(binary, 2);
    }

    private Map<String, Integer> randInit(String type) {
        Map<String, Integer> inits = new HashMap<>();
        for (int j = 0; j < 45; j++) {
            String no = Integer.toString(j);
            String key = type + "0".repeat(2 - no.length()) + no;
            Integer value = RANDOM.nextInt(0, 2);
            inits.put(key, value);
        }
        return inits;
    }

}
