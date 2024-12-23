package mk.aoc24.day11;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class MoreBlinkAtStonesTest {

    MoreBlinkAtStones moreBlinkAtStones = new MoreBlinkAtStones();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day11p12test.txt");;
        assertThat(moreBlinkAtStones.solve(url)).isEqualTo("65601038650482");
    }

}
