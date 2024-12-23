package mk.aoc24.day23;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class ConnectedComputersTest {

    ConnectedComputers connectedComputers = new ConnectedComputers();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day23p12test.txt");
        assertThat(connectedComputers.solve(url)).isEqualTo("7");
    }

}
