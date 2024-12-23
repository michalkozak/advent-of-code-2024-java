package mk.aoc24.day22;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;

public class MonkeyMarket extends PuzzleSolver {

    protected final int steps;

    protected final List<Long> secrets = new ArrayList<>();

    public MonkeyMarket(int steps) {
        this.steps = steps;
    }

    @Override
    protected void parseLines(Stream<String> lines) {
        lines.map(Long::parseLong).forEach(secrets::add);
    }

    @Override
    protected String computeResult() {
        for (int i = 0; i < steps; i++) {
            secrets.replaceAll(this::computeSecretForOneStep);
        }
        long sum = secrets.stream().reduce(0L, Long::sum);
        return String.valueOf(sum);
    }

    protected long computeSecretForOneStep(long secret) {
        secret = prune(mix(secret * 64l, secret));
        secret = prune(mix(secret / 32l, secret));
        return prune(mix(secret * 2048, secret));
    }

    private long mix(long value, long currentSecret) {
        return value ^ currentSecret;
    }

    private long prune(long currentSecret) {
        return currentSecret % 16777216;
    }

}
