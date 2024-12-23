package mk.aoc24.day18;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class MemorySpaceTest {

    MemorySpace memorySpace = new MemorySpace(7, 7, 12);

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day18p12test.txt");;
        assertThat(memorySpace.solve(url)).isEqualTo("22");
    }

}
