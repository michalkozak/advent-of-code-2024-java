package mk.aoc24.day23;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import mk.aoc24.either.Either;
import mk.aoc24.graph.Graph;

public class LanPartyPassword extends ConnectedComputers {

    private final Deque<Graph<String>> deque = new ArrayDeque<>();

    @Override
    protected String computeResult() {
        Set<String> connectedComputers = findMaxGroupOfConnectedComputers();
        return connectedComputers.stream().sorted().collect(Collectors.joining(","));
    }

    private Set<String> findMaxGroupOfConnectedComputers() {
        Optional<Set<String>> groupOfConnectedComputers = findGroupOfConnectedComputers(network);
        if (groupOfConnectedComputers.isPresent()) {
            return groupOfConnectedComputers.get();
        }
        deque.offer(network);
        while (!deque.isEmpty()) {
            Graph<String> graph = deque.poll();
            Either<List<Graph<String>>, Set<String>> subGraphsOrResult = generateAndCheckSubGraphs(graph);
            if (subGraphsOrResult.isRight()) {
                return subGraphsOrResult.getRight();
            }
            for (Graph<String> subGraph : subGraphsOrResult.getLeft()) {
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

    private Either<List<Graph<String>>, Set<String>> generateAndCheckSubGraphs(Graph<String> graph) {
        List<Graph<String>> subGraphs = new ArrayList<>();
        for (String node : graph.getAllNodesSortedByNumberOfAdjacentsDescending()) {
            Graph<String> subGraph = graph.newGraphWithoutNode(node);
            Optional<Set<String>> groupOfConnectedComputers = findGroupOfConnectedComputers(subGraph);
            if (groupOfConnectedComputers.isPresent()) {
                return Either.right(groupOfConnectedComputers.get());
            }
            subGraphs.add(subGraph);
        }
        return Either.left(subGraphs);
    }

}
