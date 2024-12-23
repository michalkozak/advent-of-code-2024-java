package mk.aoc24.day09;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DiskDefragmentTest {

    DiskDefragment diskDefragment = new DiskDefragment();

    @ParameterizedTest
    @MethodSource("provideDisksWithSolutions")
    void shouldSolve(String filename, String expected) {
        URL url = getClass().getClassLoader().getResource(filename);;
        assertThat(diskDefragment.solve(url)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideDisksWithSolutions() {
        return Stream.of(
            Arguments.of("day09p12atest.txt", "1928"),
            Arguments.of("day09p12btest.txt", "1766")
        );
    }

}
