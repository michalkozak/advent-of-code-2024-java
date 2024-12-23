package mk.aoc24.day06;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InfinitePathAvoidTest {

    InfinitePathAvoid infinitePathAvoid = new InfinitePathAvoid();

    @ParameterizedTest
    @MethodSource("provideBoardsWithSolutions")
    void shouldSolve(String filename, String expected) {
        URL url = getClass().getClassLoader().getResource(filename);;
        assertThat(infinitePathAvoid.solve(url)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideBoardsWithSolutions() {
        return Stream.of(
            Arguments.of("day06p1test.txt", "6"),
            Arguments.of("day06p2atest.txt", "9"),
            Arguments.of("day06p2btest.txt", "8"),
            Arguments.of("day06p2ctest.txt", "5")
        );
    }

}
