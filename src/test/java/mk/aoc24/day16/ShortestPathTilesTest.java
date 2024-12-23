package mk.aoc24.day16;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ShortestPathTilesTest {

    ShortestPathTiles shortestPathTiles = new ShortestPathTiles();

    @ParameterizedTest
    @MethodSource("provideMapsWithSolutions")
    void shouldSolve(String filename, String expected) {
        URL url = getClass().getClassLoader().getResource(filename);;
        assertThat(shortestPathTiles.solve(url)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideMapsWithSolutions() {
        return Stream.of(
            Arguments.of("day16p12atest.txt", "45"),
            Arguments.of("day16p12btest.txt", "64")
        );
    }

}