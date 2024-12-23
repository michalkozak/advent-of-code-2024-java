package mk.aoc24.day23;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import mk.aoc24.graph.Graph;

public class LanPartyPassword extends ConnectedComputers {

    private final Deque<Graph<String>> deque = new ArrayDeque<>();

    @Override
    protected String computeResult() {
        Set<String> connectedComputers = findMaxGroupOfConnectedComputers();
        return connectedComputers.stream().sorted().collect(Collectors.joining(","));
    }

    private Set<String> findMaxGroupOfConnectedComputers() {
        deque.offer(network);
        while (!deque.isEmpty()) {
            Graph<String> graph = deque.poll();
            Optional<Set<String>> groupOfConnectedComputers = findGroupOfConnectedComputers(graph);
            if (groupOfConnectedComputers.isPresent()) {
                return groupOfConnectedComputers.get();
            }
            List<Graph<String>> subGraphs = getAllSubGraphsWithoutOneNode(graph);
            for (Graph<String> subGraph : subGraphs) {
                deque.offer(subGraph);
            }
        }

        throw new IllegalStateException("Could not find max group of connected computers");
    }

    private Optional<Set<String>> findGroupOfConnectedComputers(Graph<String> graph) {
        for (String node : graph.getAllNodesSortedByNumberOfAdjacentsDescending()) {
            if (graph.areAllNodeAdjacentsConnected(node)) {
                Set<String> connectedComputers = new HashSet<>();
                connectedComputers.add(node);
                connectedComputers.addAll(graph.getAdjacentNodes(node));
                return Optional.of(connectedComputers);
            }
        }
        return Optional.empty();
    }

    private List<Graph<String>> getAllSubGraphsWithoutOneNode(Graph<String> graph) {
        List<Graph<String>> subGraphs = new ArrayList<>();
        for (String node : graph.getAllNodes()) {
            subGraphs.add(graph.newGraphWithoutNode(node));
        }
        return subGraphs;
    }

}
