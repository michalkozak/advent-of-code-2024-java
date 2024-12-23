package mk.aoc24.day08;

import mk.aoc24.board.Coordinate;

public class MultiAntinodesSearch extends AntinodesSearch {

    @Override
    protected void createAntinodes(Coordinate frequencyCoordinate1, Coordinate frequencyCoordinate2, int deltaX, int deltaY) {
        antinodesCooridinates.add(frequencyCoordinate1);
        antinodesCooridinates.add(frequencyCoordinate2);

        Coordinate antinodeCoordinate1 = frequencyCoordinate1;
        while (antinodeCoordinate1 != null) {
            antinodeCoordinate1 = createNewAntinode(antinodeCoordinate1, deltaX, deltaY);
        }
        Coordinate antinodeCoordinate2 = frequencyCoordinate2;
        while (antinodeCoordinate2 != null) {
            antinodeCoordinate2 = createNewAntinode(antinodeCoordinate2, -deltaX, -deltaY);
        }
    }

}
