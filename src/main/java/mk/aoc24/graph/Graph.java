package mk.aoc24.graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph<T> {

    private final Map<T, Set<T>> vertices;

    public Graph() {
        this.vertices = new HashMap<>();
    }
    public Graph(Map<T, Set<T>> vertices) {
        this.vertices = vertices;
    }

    public void addEdge(T fromNode, T toNode) {
        Set<T> adjacents = vertices.computeIfAbsent(fromNode, k -> new HashSet<>());
        adjacents.add(toNode);
    }

    public boolean isEdge(T fromNode, T toNode) {
        return vertices.get(fromNode).contains(toNode);
    }

    public Set<T> getAllNodes() {
        return vertices.keySet();
    }

    public List<T> getAllNodesSortedByNumberOfAdjacentsDescending() {
        return vertices.keySet().stream().sorted(Comparator.comparingInt(k -> vertices.get(k).size()).reversed()).toList();
    }

    public Set<T> getAdjacentNodes(T node) {
        return vertices.get(node);
    }

    public boolean areAllNodeAdjacentsConnected(T node) {
        for (T adjacent : getAdjacentNodes(node)) {
            for (T otherAdjacent : getAdjacentNodes(node)) {
                if (!otherAdjacent.equals(adjacent)
                    && (!isEdge(otherAdjacent, node) || !isEdge(otherAdjacent, adjacent))) {
                    return false;
                }
            }
        }
        return true;
    }

    public Graph<T> newGraphWithoutNode(String node) {
        Map<T, Set<T>> newVertices = new HashMap<>();
        for (Map.Entry<T, Set<T>> entry : vertices.entrySet()) {
            if (!node.equals(entry.getKey())) {
                Set<T> newAdjacents = entry.getValue().stream().filter(n -> !n.equals(node)).collect(Collectors.toSet());
                newVertices.put(entry.getKey(), newAdjacents);
            }
        }
        return new Graph<>(newVertices);
    }

    @SuppressWarnings("java:S106")
    public void print() {
        for (Map.Entry<T, Set<T>> entry : vertices.entrySet()) {
            System.out.print(entry.getKey() + " = ");
            System.out.println(entry.getValue());
        }
    }

}
