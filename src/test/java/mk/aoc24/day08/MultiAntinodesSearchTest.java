package mk.aoc24.day08;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class MultiAntinodesSearchTest {

    MultiAntinodesSearch multiAntinodesSearch = new MultiAntinodesSearch();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day08p12test.txt");;
        assertThat(multiAntinodesSearch.solve(url)).isEqualTo("34");
    }

}
