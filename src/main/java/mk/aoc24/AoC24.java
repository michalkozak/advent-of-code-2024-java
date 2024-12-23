package mk.aoc24;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("java:S106")
public class AoC24 {

    public static void main(String[] args) {
        System.out.println("Advent od Code 2024");
        LocalTime start = LocalTime.now();
        if (args.length > 0) {
            new AoC24().puzzles(Arrays.stream(args).map(DayPuzzle::valueOf).toList());
        } else {
            new AoC24().puzzles(Arrays.stream(DayPuzzle.values()).toList());
        }
        LocalTime end = LocalTime.now();
        System.out.println("Computation took " + Duration.between(start, end));
    }

    public void puzzles(List<DayPuzzle> dayPuzzles) {
        dayPuzzles.forEach(this::puzzle);
    }

    public void puzzle(DayPuzzle dayPuzzle) {
        System.out.printf("Day %s (part %s) [%s]: %s%n", dayPuzzle.getDay(), dayPuzzle.getPart(), dayPuzzle.getOriginalUrl(), dayPuzzle.solve());
    }

}
