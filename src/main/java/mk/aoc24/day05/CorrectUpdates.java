package mk.aoc24.day05;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CorrectUpdates extends OrderedUpdates {

    @Override
    protected String computeResult() {
        int sumOfMiddleElements = updates.stream()
            .filter(this::isOrderIncorrect)
            .map(this::correctOrder)
            .map(this::getMiddleElement)
            .reduce(0, Integer::sum);
        return String.valueOf(sumOfMiddleElements);
    }

    private boolean isOrderIncorrect(List<Integer> update) {
        return !isOrderCorrect(update);
    }

    private List<Integer> correctOrder(List<Integer> update) {
        Integer[] array = update.toArray(new Integer[0]);
        for (int k = 0; k < array.length - 1; k++) {
            for (int i = array.length - 1; i > k; i--) {
                Integer element = array[i];
                if (rules.containsKey(element)) {
                    Set<Integer> afterElement = rules.get(element);
                    if (afterElement.contains(array[i - 1])) {
                        int t = array[i - 1];
                        array[i - 1] = array[i];
                        array[i] = t;
                    }
                }
            }
        }
        return Arrays.asList(array);
    }

}
