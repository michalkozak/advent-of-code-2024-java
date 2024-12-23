package mk.aoc24.day21;

import java.util.Map;

public interface Keypad<T> {

    Map<T, DirectionalKeypad> movesToNeighbors();

}
