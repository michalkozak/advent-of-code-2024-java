package mk.aoc24.day02;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class ReportAlmostSafetyTest {

    ReportAlmostSafety reportAlmostSafety = new ReportAlmostSafety();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day02p2test.txt");;
        assertThat(reportAlmostSafety.solve(url)).isEqualTo("7");
    }

}
