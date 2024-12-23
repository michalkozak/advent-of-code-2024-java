package mk.aoc24.day23;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class LanPartyPasswordTest {

    LanPartyPassword lanPartyPassword = new LanPartyPassword();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day23p12test.txt");
        assertThat(lanPartyPassword.solve(url)).isEqualTo("co,de,ka,ta");
    }

}
