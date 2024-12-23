package mk.aoc24.board;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.BiFunction;

public class BoardGraph<T> {

    private final Map<T, Vertex<T>> vertices;

    public BoardGraph(Map<T, Vertex<T>> vertices) {
        this.vertices = vertices;
    }

    public BoardGraph(BoardGraph<T> original) {
        this.vertices = new HashMap<>(original.vertices);
    }

    public Map<T, Vertex<T>> vertices() {
        return vertices;
    }

    public Vertex<T> vertex(T coordinate) {
        return vertices.get(coordinate);
    }

    public Map<T, Path<T>> shortestPathByDijkstra(T start, BiFunction<Dijkstra<T>, T, Integer> distanceFunction) {
        PriorityQueue<Dijkstra<T>> priorityQueue = new PriorityQueue<>(new DijkstraComparator<>());
        priorityQueue.add(new Dijkstra<>(start, 0));
        Map<T, Path<T>> paths = new HashMap<>();
        for (T coordinate : vertices.keySet()) {
            if (coordinate.equals(start)) {
                paths.put(start, new Path<>(0));
            } else {
                paths.put(coordinate, new Path<>());
            }
        }

        while (!priorityQueue.isEmpty()) {
            Dijkstra<T> d = priorityQueue.poll();
            for (T edge : vertices.get(d.coordinate()).edges()) {
                int distance = d.distance() + distanceFunction.apply(d, edge);
                if (distance < paths.get(edge).distance()) {
                    paths.get(edge).distance(distance);
                    paths.get(edge).previous(d.coordinate());
                    priorityQueue.add(new Dijkstra<>(edge, distance));
                }
            }
        }

        return paths;
    }

}
