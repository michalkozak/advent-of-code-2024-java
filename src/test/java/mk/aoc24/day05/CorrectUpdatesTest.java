package mk.aoc24.day05;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class CorrectUpdatesTest {

    CorrectUpdates correctUpdates = new CorrectUpdates();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day05p12test.txt");;
        assertThat(correctUpdates.solve(url)).isEqualTo("123");
    }

}
