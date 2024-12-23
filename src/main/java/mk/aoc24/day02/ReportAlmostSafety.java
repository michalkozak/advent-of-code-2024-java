package mk.aoc24.day02;

import java.util.ArrayList;
import java.util.List;

public class ReportAlmostSafety extends ReportSafety {

    @Override
    protected String computeResult() {
        long numberOfSafetyReports = reports.stream().map(r -> findDifferences(r.levels()))
            .filter(this::areDifferencesSafeWithSingleBadLevel).count();
        return String.valueOf(numberOfSafetyReports);
    }

    protected boolean areDifferencesSafeWithSingleBadLevel(List<Integer> diffs) {
        boolean safe = areDifferencesSafe(diffs);
        if (safe) {
            return true;
        } else {
            for (int i = 0; i <= diffs.size(); i++) {
                List<Integer> modDiffs = new ArrayList<>(diffs);
                Integer removed = modDiffs.remove(i != diffs.size() ? i : diffs.size() - 1);
                if (i > 0 && i < diffs.size()) {
                    modDiffs.set(i - 1, modDiffs.get(i - 1) + removed);
                }
                if (areDifferencesSafe(modDiffs)) {
                    return true;
                }
            }
        }
        return false;
    }

}
