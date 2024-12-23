package mk.aoc24.day14;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class ChristmasTreeRobotsTest {

    ChristmasTreeRobots christmasTreeRobots = new ChristmasTreeRobots(11, 7);

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day14p2test.txt");;
        assertThat(christmasTreeRobots.solve(url)).isEqualTo("0");
    }

}