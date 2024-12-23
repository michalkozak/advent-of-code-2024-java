package mk.aoc24.day22;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MonkeyMarketTest {

    @ParameterizedTest
    @MethodSource("provideStepWithSolutions")
    void shouldSolveStepByStep(int step, String expected) {
        MonkeyMarket monkeyMarket = new MonkeyMarket(step);
        URL url = getClass().getClassLoader().getResource("day22p12atest.txt");;
        assertThat(monkeyMarket.solve(url)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStepWithSolutions() {
        return Stream.of(
            Arguments.of("0", "123"),
            Arguments.of("1", "15887950"),
            Arguments.of("2", "16495136"),
            Arguments.of("3", "527345"),
            Arguments.of("4", "704524"),
            Arguments.of("5", "1553684"),
            Arguments.of("6", "12683156"),
            Arguments.of("7", "11100544"),
            Arguments.of("8", "12249484"),
            Arguments.of("9", "7753432"),
            Arguments.of("10", "5908254")
        );
    }

    @Test
    void shouldSolve() {
        MonkeyMarket monkeyMarket = new MonkeyMarket(2000);
        URL url = getClass().getClassLoader().getResource("day22p12btest.txt");;
        assertThat(monkeyMarket.solve(url)).isEqualTo("37327623");
    }

}
