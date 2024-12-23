package mk.aoc24.day13;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class ClawMachineTest {

    ClawMachine clawMachine = new ClawMachine();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day13p12test.txt");;
        assertThat(clawMachine.solve(url)).isEqualTo("480");
    }

}
