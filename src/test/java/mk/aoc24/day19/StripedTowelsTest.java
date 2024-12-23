package mk.aoc24.day19;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class StripedTowelsTest {

    StripedTowels stripedTowels = new StripedTowels();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day19p12test.txt");;
        assertThat(stripedTowels.solve(url)).isEqualTo("6");
    }

}
