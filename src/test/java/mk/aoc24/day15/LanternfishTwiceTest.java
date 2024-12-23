package mk.aoc24.day15;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LanternfishTwiceTest {

    LanternfishTwice lanternfishTwice = new LanternfishTwice();

    @ParameterizedTest
    @MethodSource("provideMapsWithSolutions")
    void shouldSolve(String filename, String expected) {
        URL url = getClass().getClassLoader().getResource(filename);;
        assertThat(lanternfishTwice.solve(url)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideMapsWithSolutions() {
        return Stream.of(
            Arguments.of("day15p2atest.txt", "618"),
            Arguments.of("day15p12btest.txt", "9021")
        );
    }

}
