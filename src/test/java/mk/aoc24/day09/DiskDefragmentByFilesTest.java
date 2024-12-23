package mk.aoc24.day09;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DiskDefragmentByFilesTest {

    DiskDefragmentByFiles diskDefragmentByFiles = new DiskDefragmentByFiles();

    @ParameterizedTest
    @MethodSource("provideDisksWithSolutions")
    void shouldSolve(String filename, String expected) {
        URL url = getClass().getClassLoader().getResource(filename);;
        assertThat(diskDefragmentByFiles.solve(url)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideDisksWithSolutions() {
        return Stream.of(
            Arguments.of("day09p12atest.txt", "2858"),
            Arguments.of("day09p12btest.txt", "2144")
        );
    }

}
