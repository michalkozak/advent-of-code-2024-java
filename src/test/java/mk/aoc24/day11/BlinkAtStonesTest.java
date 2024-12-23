package mk.aoc24.day11;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class BlinkAtStonesTest {

    BlinkAtStones blinkAtStones = new BlinkAtStones();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day11p12test.txt");;
        assertThat(blinkAtStones.solve(url)).isEqualTo("55312");
    }

}
