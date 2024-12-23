package mk.aoc24.day17;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class ThreeBitComputerTest {

    ThreeBitComputer threeBitComputer = new ThreeBitComputer();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day17p1test.txt");;
        assertThat(threeBitComputer.solve(url)).isEqualTo("4,6,3,5,6,3,5,2,1,0");
    }

}
