package mk.aoc24.day21;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KeypadsControllersTest {

    KeypadsControllers keypadsControllers = new KeypadsControllers();

    @ParameterizedTest
    @MethodSource("provideCodesWithSolutions")
    void shouldSolve(String filename, String expected) {
        URL url = getClass().getClassLoader().getResource(filename);;
        assertThat(keypadsControllers.solve(url)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCodesWithSolutions() {
        return Stream.of(
            Arguments.of("day21p12atest.txt", "1972"),
            Arguments.of("day21p12btest.txt", "126384")
        );
    }

}
