package mk.aoc24.day13;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class ConvertedClawMachineTest {

    ConvertedClawMachine convertedClawMachine = new ConvertedClawMachine();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day13p12test.txt");;
        assertThat(convertedClawMachine.solve(url)).isEqualTo("875318608908");
    }


}
