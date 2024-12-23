package mk.aoc24.day18;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class NoExitTest {

    NoExit noExit = new NoExit(7, 7, 12);

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day18p12test.txt");;
        assertThat(noExit.solve(url)).isEqualTo("6,1");
    }

}
