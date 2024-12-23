package mk.aoc24;

import java.net.URL;
import mk.aoc24.day01.LocationsDistance;
import mk.aoc24.day01.LocationsSimilarity;
import mk.aoc24.day02.ReportAlmostSafety;
import mk.aoc24.day02.ReportSafety;
import mk.aoc24.day03.CorruptedDoDontMultiplications;
import mk.aoc24.day03.CorruptedMultiplications;
import mk.aoc24.day04.XXmasSearch;
import mk.aoc24.day04.XmasSearch;
import mk.aoc24.day05.CorrectUpdates;
import mk.aoc24.day05.OrderedUpdates;
import mk.aoc24.day06.FindPath;
import mk.aoc24.day06.InfinitePathAvoid;
import mk.aoc24.day07.CalibrateEquations;
import mk.aoc24.day07.ConcatenationCalibrateEquations;
import mk.aoc24.day08.AntinodesSearch;
import mk.aoc24.day08.MultiAntinodesSearch;
import mk.aoc24.day09.DiskDefragment;
import mk.aoc24.day09.DiskDefragmentByFiles;
import mk.aoc24.day10.DistinctHikingTrailSearch;
import mk.aoc24.day10.HikingTrailSearch;
import mk.aoc24.day11.BlinkAtStones;
import mk.aoc24.day11.MoreBlinkAtStones;
import mk.aoc24.day12.FenceCost;
import mk.aoc24.day12.FenceDiscountCost;
import mk.aoc24.day13.ClawMachine;
import mk.aoc24.day13.ConvertedClawMachine;
import mk.aoc24.day14.BathroomRobots;
import mk.aoc24.day14.ChristmasTreeRobots;
import mk.aoc24.day15.Lanternfish;
import mk.aoc24.day15.LanternfishTwice;
import mk.aoc24.day16.ShortestPath;
import mk.aoc24.day16.ShortestPathTiles;
import mk.aoc24.day17.ProgramCopy;
import mk.aoc24.day17.ThreeBitComputer;
import mk.aoc24.day18.MemorySpace;
import mk.aoc24.day18.NoExit;
import mk.aoc24.day19.StripedTowels;
import mk.aoc24.day19.StripedTowelsPossibilities;
import mk.aoc24.day20.RaceCheat;
import mk.aoc24.day20.RaceMoreCheats;
import mk.aoc24.day21.KeypadsControllers;
import mk.aoc24.day21.MoreKeypadsControllers;
import mk.aoc24.day22.BestBananasPrice;
import mk.aoc24.day22.MonkeyMarket;
import mk.aoc24.day23.ConnectedComputers;
import mk.aoc24.day23.LanPartyPassword;

public enum DayPuzzle {
    DAY_01_PUZZLE_1(new LocationsDistance()),
    DAY_01_PUZZLE_2(new LocationsSimilarity()),
    DAY_02_PUZZLE_1(new ReportSafety()),
    DAY_02_PUZZLE_2(new ReportAlmostSafety()),
    DAY_03_PUZZLE_1(new CorruptedMultiplications()),
    DAY_03_PUZZLE_2(new CorruptedDoDontMultiplications()),
    DAY_04_PUZZLE_1(new XmasSearch()),
    DAY_04_PUZZLE_2(new XXmasSearch()),
    DAY_05_PUZZLE_1(new OrderedUpdates()),
    DAY_05_PUZZLE_2(new CorrectUpdates()),
    DAY_06_PUZZLE_1(new FindPath()),
    DAY_06_PUZZLE_2(new InfinitePathAvoid()),
    DAY_07_PUZZLE_1(new CalibrateEquations()),
    DAY_07_PUZZLE_2(new ConcatenationCalibrateEquations()),
    DAY_08_PUZZLE_1(new AntinodesSearch()),
    DAY_08_PUZZLE_2(new MultiAntinodesSearch()),
    DAY_09_PUZZLE_1(new DiskDefragment()),
    DAY_09_PUZZLE_2(new DiskDefragmentByFiles()),
    DAY_10_PUZZLE_1(new HikingTrailSearch()),
    DAY_10_PUZZLE_2(new DistinctHikingTrailSearch()),
    DAY_11_PUZZLE_1(new BlinkAtStones()),
    DAY_11_PUZZLE_2(new MoreBlinkAtStones()),
    DAY_12_PUZZLE_1(new FenceCost()),
    DAY_12_PUZZLE_2(new FenceDiscountCost()),
    DAY_13_PUZZLE_1(new ClawMachine()),
    DAY_13_PUZZLE_2(new ConvertedClawMachine()),
    DAY_14_PUZZLE_1(new BathroomRobots(101, 103)),
    DAY_14_PUZZLE_2(new ChristmasTreeRobots(101, 103)),
    DAY_15_PUZZLE_1(new Lanternfish()),
    DAY_15_PUZZLE_2(new LanternfishTwice()),
    DAY_16_PUZZLE_1(new ShortestPath()),
    DAY_16_PUZZLE_2(new ShortestPathTiles()),
    DAY_17_PUZZLE_1(new ThreeBitComputer()),
    DAY_17_PUZZLE_2(new ProgramCopy()),
    DAY_18_PUZZLE_1(new MemorySpace(71, 71, 1024)),
    DAY_18_PUZZLE_2(new NoExit(71, 71, 1024)),
    DAY_19_PUZZLE_1(new StripedTowels()),
    DAY_19_PUZZLE_2(new StripedTowelsPossibilities()),
    DAY_20_PUZZLE_1(new RaceCheat(100)),
    DAY_20_PUZZLE_2(new RaceMoreCheats(100)),
    DAY_21_PUZZLE_1(new KeypadsControllers()),
    DAY_21_PUZZLE_2(new MoreKeypadsControllers()),
    DAY_22_PUZZLE_1(new MonkeyMarket(2000)),
    DAY_22_PUZZLE_2(new BestBananasPrice(2000)),
    DAY_23_PUZZLE_1(new ConnectedComputers()),
    DAY_23_PUZZLE_2(new LanPartyPassword());

    private final PuzzleSolver solver;

    DayPuzzle(PuzzleSolver solver) {
        this.solver = solver;
    }

    public String getOriginalUrl() {
        return String.format("https://adventofcode.com/2024/day/%d/input", getDayNumber());
    }

    public URL getUrl() {
        return getClass().getClassLoader().getResource(String.format("day%s.txt", getDay()));
    }

    public int getDayNumber() {
        return Integer.parseInt(getDay());
    }

    public String getDay() {
        return name().split("_")[1];
    }

    public String getPart() {
        return name().split("_")[3];
    }

    public String solve() {
        return solver.solve(getUrl());
    }

}