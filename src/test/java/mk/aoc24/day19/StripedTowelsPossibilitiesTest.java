package mk.aoc24.day19;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class StripedTowelsPossibilitiesTest {

    StripedTowelsPossibilities stripedTowelsPossibilities = new StripedTowelsPossibilities();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day19p12test.txt");;
        assertThat(stripedTowelsPossibilities.solve(url)).isEqualTo("16");
    }

}
