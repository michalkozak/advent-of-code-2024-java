package mk.aoc24.day07;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class CalibrateEquationsTest {

    CalibrateEquations calibrateEquations = new CalibrateEquations();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day07p12test.txt");;
        assertThat(calibrateEquations.solve(url)).isEqualTo("3749");
    }

}
