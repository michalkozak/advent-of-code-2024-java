package mk.aoc24.day07;

import static mk.aoc24.helper.MathHelper.power;

import mk.aoc24.helper.IntBaseHelper;

public class ConcatenationCalibrateEquations extends CalibrateEquations {

    @Override
    protected String computeResult() {
        for (int i = 0; i < results.size(); i++) {
            for (int j = 0; j < power(3, components.get(i).size() - 1, 1); j++) {
                String ternary = IntBaseHelper.convertIntToBase(j, 3, components.get(i).size() - 1);
                long computed = components.get(i).getFirst();
                for (int k = 0; k < ternary.length(); k++) {
                    if (ternary.charAt(k) == '0') {
                        computed += components.get(i).get(k + 1);
                    } else if (ternary.charAt(k) == '1') {
                        computed *= components.get(i).get(k + 1);
                    } else {
                        String concatenation = String.valueOf(computed) + String.valueOf(components.get(i).get(k + 1));
                        computed = Long.parseLong(concatenation);
                    }
                }
                if (computed == results.get(i)) {
                    sum += computed;
                    break;
                }
            }
        }
        return String.valueOf(sum);
    }

}
