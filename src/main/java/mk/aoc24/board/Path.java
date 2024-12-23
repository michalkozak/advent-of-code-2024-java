package mk.aoc24.board;

public class Path<T> {

    protected int distance;
    protected T previous;

    public Path() {
        this.distance = Integer.MAX_VALUE;
        this.previous = null;
    }

    public Path(int distance) {
        this.distance = distance;
        this.previous = null;
    }

    public int distance() {
        return distance;
    }

    public void distance(int distance) {
        this.distance = distance;
    }

    public T previous() {
        return previous;
    }

    public void previous(T previous) {
        this.previous = previous;
    }

}
