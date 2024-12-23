package mk.aoc24.day10;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class HikingTrailSearchTest {

    HikingTrailSearch hikingTrailSearch = new HikingTrailSearch();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day10p12test.txt");;
        assertThat(hikingTrailSearch.solve(url)).isEqualTo("36");
    }

}
