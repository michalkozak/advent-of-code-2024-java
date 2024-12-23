package mk.aoc24.day07;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class ConcatenationCalibrateEquationsTest {

    ConcatenationCalibrateEquations concatenationCalibrateEquations = new ConcatenationCalibrateEquations();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day07p12test.txt");;
        assertThat(concatenationCalibrateEquations.solve(url)).isEqualTo("11387");
    }

}
