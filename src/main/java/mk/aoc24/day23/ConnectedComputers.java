package mk.aoc24.day23;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.graph.Graph;

public class ConnectedComputers extends PuzzleSolver {

    protected Graph<String> network = new Graph<>();

    @Override
    protected void parseLines(Stream<String> lines) {
        lines.forEach(this::parseLine);
    }

    private void parseLine(String line) {
        String[] parts = line.split("-");
        network.addEdge(parts[0], parts[1]);
        network.addEdge(parts[1], parts[0]);
    }

    @Override
    protected String computeResult() {
        Set<Set<String>> sets = find3ConnectedComputersWithT();
        return String.valueOf(sets.size());
    }

    private Set<Set<String>> find3ConnectedComputersWithT() {
        Set<Set<String>> result = new HashSet<>();
        for (String node : network.getAllNodes()) {
            for (String adj1 : network.getAdjacentNodes(node)) {
                for (String adj2 : network.getAdjacentNodes(node)) {
                    if (!adj1.equals(adj2)) {
                        if (network.isEdge(adj1, adj2) && network.isEdge(adj1, node)) {
                            if (node.startsWith("t") || adj1.startsWith("t") || adj2.startsWith("t")) {
                                Set<String> connectedComputers = new HashSet<>();
                                connectedComputers.add(node);
                                connectedComputers.add(adj1);
                                connectedComputers.add(adj2);
                                result.add(connectedComputers);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

}
