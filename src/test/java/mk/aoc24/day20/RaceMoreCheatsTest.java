package mk.aoc24.day20;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class RaceMoreCheatsTest {

    RaceMoreCheats raceMoreCheats = new RaceMoreCheats(50);

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day20p12test.txt");;
        assertThat(raceMoreCheats.solve(url)).isEqualTo("285");
    }

}
