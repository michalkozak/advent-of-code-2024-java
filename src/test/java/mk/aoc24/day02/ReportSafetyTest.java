package mk.aoc24.day02;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class ReportSafetyTest {

    ReportSafety reportSafety = new ReportSafety();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day02p1test.txt");;
        assertThat(reportSafety.solve(url)).isEqualTo("2");
    }

}
