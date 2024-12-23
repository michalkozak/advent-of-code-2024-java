package mk.aoc24.day03;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class CorruptedDoDontMultiplicationsTest {

    CorruptedDoDontMultiplications corruptedDoDontMultiplications = new CorruptedDoDontMultiplications();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day03p2test.txt");;
        assertThat(corruptedDoDontMultiplications.solve(url)).isEqualTo("234");
    }

}
