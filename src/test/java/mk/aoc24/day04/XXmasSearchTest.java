package mk.aoc24.day04;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class XXmasSearchTest {

    XXmasSearch xxmasSearch = new XXmasSearch();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day04p12test.txt");;
        assertThat(xxmasSearch.solve(url)).isEqualTo("9");
    }

}
