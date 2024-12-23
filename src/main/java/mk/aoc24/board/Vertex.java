package mk.aoc24.board;

import java.util.Collection;

public record Vertex<T>(
    T coordinate,
    Collection<T> edges
) {
}
