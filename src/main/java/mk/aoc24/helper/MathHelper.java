package mk.aoc24.helper;

public class MathHelper {

    private MathHelper() {
    }

    public static long power(long n, int p, long accumulator) {
        if (p == 0) {
            return accumulator;
        } else {
            return power(n, p - 1, accumulator * n);
        }
    }

}
