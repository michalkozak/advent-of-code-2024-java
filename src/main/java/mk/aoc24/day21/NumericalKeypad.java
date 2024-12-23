package mk.aoc24.day21;

import java.util.Map;

public enum NumericalKeypad implements Keypad<NumericalKeypad> {
    B7('7'),
    B8('8'),
    B9('9'),
    B4('4'),
    B5('5'),
    B6('6'),
    B1('1'),
    B2('2'),
    B3('3'),
    B0('0'),
    ACTIVATE('A');

    private final char code;

    NumericalKeypad(char code) {
        this.code = code;
    }

    public static NumericalKeypad of(char code) {
        return switch (code) {
            case 'A' -> ACTIVATE;
            case '0' -> B0;
            case '1' -> B1;
            case '2' -> B2;
            case '3' -> B3;
            case '4' -> B4;
            case '5' -> B5;
            case '6' -> B6;
            case '7' -> B7;
            case '8' -> B8;
            case '9' -> B9;
            default -> throw new IllegalArgumentException("Unknown code: " + code);
        };
    }

    public char code() {
        return code;
    }

    @Override
    public Map<NumericalKeypad, DirectionalKeypad> movesToNeighbors() {
        return switch (this) {
            case B7 -> Map.of(
                NumericalKeypad.B8, DirectionalKeypad.RIGHT,
                NumericalKeypad.B4, DirectionalKeypad.DOWN);
            case B8 -> Map.of(
                NumericalKeypad.B7, DirectionalKeypad.LEFT,
                NumericalKeypad.B9, DirectionalKeypad.RIGHT,
                NumericalKeypad.B5, DirectionalKeypad.DOWN);
            case B9 -> Map.of(
                NumericalKeypad.B8, DirectionalKeypad.LEFT,
                NumericalKeypad.B6, DirectionalKeypad.DOWN);
            case B4 -> Map.of(
                NumericalKeypad.B7, DirectionalKeypad.UP,
                NumericalKeypad.B5, DirectionalKeypad.RIGHT,
                NumericalKeypad.B1, DirectionalKeypad.DOWN);
            case B5 -> Map.of(
                NumericalKeypad.B8, DirectionalKeypad.UP,
                NumericalKeypad.B4, DirectionalKeypad.LEFT,
                NumericalKeypad.B6, DirectionalKeypad.RIGHT,
                NumericalKeypad.B2, DirectionalKeypad.DOWN);
            case B6 -> Map.of(
                NumericalKeypad.B9, DirectionalKeypad.UP,
                NumericalKeypad.B5, DirectionalKeypad.LEFT,
                NumericalKeypad.B3, DirectionalKeypad.DOWN);
            case B1 -> Map.of(
                NumericalKeypad.B4, DirectionalKeypad.UP,
                NumericalKeypad.B2, DirectionalKeypad.RIGHT);
            case B2 -> Map.of(
                NumericalKeypad.B5, DirectionalKeypad.UP,
                NumericalKeypad.B1, DirectionalKeypad.LEFT,
                NumericalKeypad.B3, DirectionalKeypad.RIGHT,
                NumericalKeypad.B0, DirectionalKeypad.DOWN);
            case B3 -> Map.of(
                NumericalKeypad.B6, DirectionalKeypad.UP,
                NumericalKeypad.B2, DirectionalKeypad.LEFT,
                NumericalKeypad.ACTIVATE, DirectionalKeypad.DOWN);
            case B0 -> Map.of(
                NumericalKeypad.B2, DirectionalKeypad.UP,
                NumericalKeypad.ACTIVATE, DirectionalKeypad.RIGHT);
            case ACTIVATE -> Map.of(
                NumericalKeypad.B3, DirectionalKeypad.UP,
                NumericalKeypad.B0, DirectionalKeypad.LEFT);
        };
    }

}
