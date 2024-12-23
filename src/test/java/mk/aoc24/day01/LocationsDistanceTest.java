package mk.aoc24.day01;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class LocationsDistanceTest {

    LocationsDistance locationsDistance = new LocationsDistance();

    @Test
    void shouldSolve() {
        URL url = getClass().getClassLoader().getResource("day01p12test.txt");;
        assertThat(locationsDistance.solve(url)).isEqualTo("11");
    }

}
