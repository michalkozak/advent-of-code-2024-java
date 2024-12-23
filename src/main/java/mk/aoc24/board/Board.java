package mk.aoc24.board;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Board {

    private final Character[][] fields;
    private final int width;
    private final int height;

    public Board(Stream<String> lines) {
        Character[][] rotatedBoard = parseLines(lines);
        fields = new Character[rotatedBoard[0].length][rotatedBoard.length];
        for (int i = 0; i < rotatedBoard.length; i++) {
            for (int j = 0; j < rotatedBoard[i].length; j++) {
                fields[j][i] = rotatedBoard[i][j];
            }
        }
        width = fields.length;
        height = rotatedBoard.length;
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        fields = new Character[width][height];
    }

    public Board(Board original) {
        fields = new Character[original.width][original.height];
        for (int i = 0; original.width > i; i++) {
            System.arraycopy(original.fields[i], 0, fields[i], 0, original.height);
        }
        width = original.width;
        height = original.height;
    }

    public Board(int[][] numbers) {
        width = numbers.length;
        height = numbers[0].length;
        fields = new Character[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (numbers[i][j] == 0) {
                    fields[i][j] = '.';
                } else {
                    fields[i][j] = '#';
                }
            }
        }
    }

    private Character[][] parseLines(Stream<String> lines) {
        return lines.map(this::parseLine).toList().toArray(Character[][]::new);
    }

    private Character[] parseLine(String line) {
        Character[] row = new Character[line.length()];
        for (int i = 0; i < line.length(); i++) {
            row[i] = line.charAt(i);
        }
        return row;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public Character get(int x, int y) {
        return fields[x][y];
    }

    public Character get(Coordinate coordinate) {
        return get(coordinate.x(), coordinate.y());
    }

    public void set(int x, int y, Character character) {
        fields[x][y] = character;
    }

    public void set(Coordinate coordinate, Character character) {
        set(coordinate.x(), coordinate.y(), character);
    }

    public Coordinate findCoordinateOf(char character) {
        for (int x = 0; width > x; x++) {
            for (int y = 0; y < height; y++) {
                if (get(x, y) == character){
                    return new Coordinate(x, y);
                }
            }
        }
        throw new IllegalStateException("Not found");
    }

    public boolean contains(Coordinate coordinate) {
        return coordinate.x() >= 0 && coordinate.x() < width && coordinate.y() >= 0 && coordinate.y() < height;
    }

    public Set<Coordinate> findNeighbors(Coordinate coordinate) {
        return Set.of(
            new Coordinate(coordinate.x() - 1, coordinate.y()),
            new Coordinate(coordinate.x() + 1, coordinate.y()),
            new Coordinate(coordinate.x(), coordinate.y() - 1),
            new Coordinate(coordinate.x(), coordinate.y() + 1));
    }

    public Set<Coordinate> findNeighborsWithValue(Coordinate coordinate, char value) {
        Set<Coordinate> result = new HashSet<>();
            for (Coordinate neighbor : findNeighbors(coordinate)) {
                if (contains(neighbor) && get(neighbor) == value) {
                    result.add(neighbor);
            }
        }
        return result;
    }

    public Set<Coordinate> findNeighborsOtherThanValue(Coordinate coordinate, char value) {
        Set<Coordinate> result = new HashSet<>();
        for (Coordinate neighbor : findNeighbors(coordinate)) {
            if (contains(neighbor) && get(neighbor) != value) {
                result.add(neighbor);
            }
        }
        return result;
    }

    public Set<Direction> findDirectionsWithoutValue(Coordinate coordinate, char value) {
        Set<Direction> result = new HashSet<>();
        for (Coordinate neighbor : findNeighbors(coordinate)) {
            if (contains(neighbor) && get(neighbor) != value) {
                if (neighbor.y() == coordinate.y() - 1) {
                    result.add(Direction.UP);
                } else if (neighbor.y() == coordinate.y() + 1) {
                    result.add(Direction.DOWN);
                } else if (neighbor.x() == coordinate.x() - 1) {
                    result.add(Direction.LEFT);
                } else {
                    result.add(Direction.RIGHT);
                }
            }
        }
        return result;
    }

    public Coordinate neighbor(Coordinate coordinate, Direction direction) {
        return switch (direction) {
            case UP -> new Coordinate(coordinate.x(), coordinate.y() - 1);
            case DOWN -> new Coordinate( coordinate.x(), coordinate.y() + 1);
            case LEFT -> new Coordinate(coordinate.x() - 1, coordinate.y());
            case RIGHT -> new Coordinate(coordinate.x() + 1, coordinate.y());
        };
    }

    public DirectedCoordinate directedNeighbor(Coordinate coordinate, Direction direction) {
        return switch (direction) {
            case UP -> new DirectedCoordinate(neighbor(coordinate, direction), Direction.DOWN);
            case DOWN -> new DirectedCoordinate(neighbor(coordinate, direction), Direction.UP);
            case LEFT -> new DirectedCoordinate(neighbor(coordinate, direction), Direction.RIGHT);
            case RIGHT -> new DirectedCoordinate(neighbor(coordinate, direction), Direction.LEFT);
        };
    }


    public BoardGraph<Coordinate> toGraph(char obstacle) {
        Map<Coordinate, Vertex<Coordinate>> vertices = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (get(x, y) != obstacle) {
                    Coordinate coordinate = new Coordinate(x, y);
                    Set<Coordinate> neighbors = findNeighborsOtherThanValue(coordinate, obstacle);
                    vertices.put(coordinate, new Vertex<>(coordinate, neighbors));
                }
            }
        }
        return new BoardGraph<>(vertices);
    }

    public BoardGraph<DirectedCoordinate> toDirectedGraph(char obstacle) {
        Map<DirectedCoordinate, Vertex<DirectedCoordinate>> vertices = new HashMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (get(x, y) != obstacle) {
                    Coordinate coordinate = new Coordinate(x, y);
                    Set<Direction> availableDirections = findDirectionsWithoutValue(coordinate, obstacle);
                    for (Direction direction : availableDirections) {
                        DirectedCoordinate directedCoordinate = new DirectedCoordinate(coordinate, direction);
                        vertices.put(directedCoordinate, new Vertex<>(directedCoordinate, availableDirections.stream()
                            .map(d -> d == direction ? directedNeighbor(coordinate, d) : new DirectedCoordinate(coordinate, d))
                            .collect(Collectors.toSet()))
                        );
                    }
                }
            }
        }
        return new BoardGraph<>(vertices);
    }


    @SuppressWarnings("java:S106")
    public void print() {
        System.out.print(" ");
        IntStream.range(0, width).forEach(no -> System.out.print(no % 10));
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.print(i % 10);
            for (int j = 0; j < width; j++) {
                System.out.print(fields[j][i]);
            }
            System.out.println();
        }
    }

}
