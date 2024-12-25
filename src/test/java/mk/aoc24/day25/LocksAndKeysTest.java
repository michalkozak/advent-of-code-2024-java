package mk.aoc24.day25;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class LocksAndKeysTest {

    LocksAndKeys locksAndKeys = new LocksAndKeys(7);

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day25p12test.txt");
        assertThat(locksAndKeys.solve(url)).isEqualTo("3");
    }

}
