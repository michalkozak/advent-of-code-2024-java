package mk.aoc24.board;

public class Dijkstra<T> {

    protected final T coordinate;
    protected int distance;

    public Dijkstra(T coordinate) {
        this.coordinate = coordinate;
        this.distance = Integer.MAX_VALUE;
    }

    public Dijkstra(T coordinate, int distance) {
        this.coordinate = coordinate;
        this.distance = distance;
    }

    public T coordinate() {
        return coordinate;
    }

    public int distance() {
        return distance;
    }

    public void distance(int distance) {
        this.distance = distance;
    }

}
