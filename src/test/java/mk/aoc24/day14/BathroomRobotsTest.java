package mk.aoc24.day14;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class BathroomRobotsTest {

    BathroomRobots bathroomRobots = new BathroomRobots(11, 7);

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day14p1test.txt");;
        assertThat(bathroomRobots.solve(url)).isEqualTo("12");
    }

}
