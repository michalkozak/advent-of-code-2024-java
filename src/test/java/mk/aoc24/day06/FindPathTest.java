package mk.aoc24.day06;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class FindPathTest {

    FindPath findPath = new FindPath();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day06p1test.txt");;
        assertThat(findPath.solve(url)).isEqualTo("41");
    }

}
