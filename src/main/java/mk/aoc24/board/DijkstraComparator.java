package mk.aoc24.board;

import java.util.Comparator;

public class DijkstraComparator<T> implements Comparator<Dijkstra<T>> {

    @Override
    public int compare(Dijkstra d1, Dijkstra d2) {
        return d1.distance() - d2.distance();
    }

}
