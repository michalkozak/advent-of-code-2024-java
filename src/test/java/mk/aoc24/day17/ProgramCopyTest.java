package mk.aoc24.day17;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class ProgramCopyTest {

    ProgramCopy programCopy = new ProgramCopy();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day17p2test.txt");;
        assertThat(programCopy.solve(url)).isEqualTo("117440");
    }

}
