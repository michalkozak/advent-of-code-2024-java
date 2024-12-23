package mk.aoc24.day19;

public class StripedTowelsPossibilities extends StripedTowels {

    @Override
    protected String computeResult() {
        long count = 0;
        for (String design : designs) {
            count += howManyWayToBuild(design);
        }
        return String.valueOf(count);
    }

}
