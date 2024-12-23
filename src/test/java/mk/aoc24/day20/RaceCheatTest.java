package mk.aoc24.day20;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class RaceCheatTest {

    RaceCheat raceCheat = new RaceCheat(4);

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day20p12test.txt");;
        assertThat(raceCheat.solve(url)).isEqualTo("30");
    }

}
