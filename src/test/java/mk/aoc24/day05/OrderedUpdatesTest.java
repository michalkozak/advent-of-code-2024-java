package mk.aoc24.day05;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class OrderedUpdatesTest {

    OrderedUpdates orderedUpdates = new OrderedUpdates();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day05p12test.txt");;
        assertThat(orderedUpdates.solve(url)).isEqualTo("143");
    }

}
