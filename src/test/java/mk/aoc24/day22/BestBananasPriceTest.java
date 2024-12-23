package mk.aoc24.day22;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import org.junit.jupiter.api.Test;

class BestBananasPriceTest {

    @Test
    void shouldSolveFor10Steps() {
        BestBananasPrice bestBananasPrice = new BestBananasPrice(10);
        URL url = getClass().getClassLoader().getResource("day22p12atest.txt");
        assertThat(bestBananasPrice.solve(url)).isEqualTo("6");
    }

    @Test
    void shouldSolve() {
        BestBananasPrice bestBananasPrice = new BestBananasPrice(2000);
        URL url = getClass().getClassLoader().getResource("day22p12btest.txt");
        assertThat(bestBananasPrice.solve(url)).isEqualTo("24");
    }

}
