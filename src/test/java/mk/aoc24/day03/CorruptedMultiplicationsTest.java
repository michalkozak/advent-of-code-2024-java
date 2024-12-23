package mk.aoc24.day03;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class CorruptedMultiplicationsTest {

    CorruptedMultiplications corruptedMultiplications = new CorruptedMultiplications();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day03p1test.txt");;
        assertThat(corruptedMultiplications.solve(url)).isEqualTo("190");
    }

}
