package mk.aoc24.day04;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class XmasSearchTest {

    XmasSearch xmasSearch = new XmasSearch();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day04p12test.txt");;
        assertThat(xmasSearch.solve(url)).isEqualTo("18");
    }

}
