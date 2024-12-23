package mk.aoc24.day10;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class DistinctHikingTrailSearchTest {

    DistinctHikingTrailSearch distinctHikingTrailSearch = new DistinctHikingTrailSearch();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day10p12test.txt");;
        assertThat(distinctHikingTrailSearch.solve(url)).isEqualTo("81");
    }

}
