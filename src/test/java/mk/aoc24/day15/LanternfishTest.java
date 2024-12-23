package mk.aoc24.day15;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LanternfishTest {

    Lanternfish lanternfish = new Lanternfish();

    @ParameterizedTest
    @MethodSource("provideMapsWithSolutions")
    void shouldSolve(String filename, String expected) {
        URL url = getClass().getClassLoader().getResource(filename);;
        assertThat(lanternfish.solve(url)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideMapsWithSolutions() {
        return Stream.of(
            Arguments.of("day15p1atest.txt", "2028"),
            Arguments.of("day15p12btest.txt", "10092")
        );
    }

}
