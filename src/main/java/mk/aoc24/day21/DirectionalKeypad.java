package mk.aoc24.day21;

import java.util.Map;

public enum DirectionalKeypad implements Keypad<DirectionalKeypad> {
    UP('^'),
    ACTIVATE('A'),
    LEFT('<'),
    DOWN('v'),
    RIGHT('>');

    private final char code;

    DirectionalKeypad(char code) {
        this.code = code;
    }

    public char code() {
        return code;
    }

    @Override
    public Map<DirectionalKeypad, DirectionalKeypad> movesToNeighbors() {
        return switch (this) {
            case UP -> Map.of(
                DirectionalKeypad.ACTIVATE, DirectionalKeypad.RIGHT,
                DirectionalKeypad.DOWN, DirectionalKeypad.DOWN);
            case ACTIVATE -> Map.of(
                DirectionalKeypad.UP, DirectionalKeypad.LEFT,
                DirectionalKeypad.RIGHT, DirectionalKeypad.DOWN);
            case LEFT -> Map.of(
                DirectionalKeypad.DOWN, DirectionalKeypad.RIGHT);
            case DOWN -> Map.of(
                DirectionalKeypad.UP, DirectionalKeypad.UP,
                DirectionalKeypad.LEFT, DirectionalKeypad.LEFT,
                DirectionalKeypad.RIGHT, DirectionalKeypad.RIGHT);
            case RIGHT -> Map.of(
                DirectionalKeypad.ACTIVATE, DirectionalKeypad.UP,
                DirectionalKeypad.DOWN, DirectionalKeypad.LEFT);
        };
    }

}
