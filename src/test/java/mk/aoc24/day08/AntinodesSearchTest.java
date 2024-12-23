package mk.aoc24.day08;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class AntinodesSearchTest {

    AntinodesSearch antinodesSearch = new AntinodesSearch();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day08p12test.txt");;
        assertThat(antinodesSearch.solve(url)).isEqualTo("14");
    }

}
