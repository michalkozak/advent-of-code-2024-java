package mk.aoc24.day24;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LogicCircuitTest {

    LogicCircuit logicCircuit = new LogicCircuit();

    @ParameterizedTest
    @MethodSource("provideGatesWithSolutions")
    void shouldSolve(String filename, String expected) {
        URL url = getClass().getClassLoader().getResource(filename);;
        assertThat(logicCircuit.solve(url)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideGatesWithSolutions() {
        return Stream.of(
            Arguments.of("day24p12atest.txt", "4"),
            Arguments.of("day24p12btest.txt", "2024")
        );
    }

}
