package mk.aoc24.day17;

import java.math.BigInteger;
import java.util.List;

public class ProgramCopy extends ThreeBitComputer {

    @Override
    protected String computeResult() {
        int programSize = program.size();
        long initA = BigInteger.valueOf(8).pow(programSize - 1).longValue();
        reg.a(initA);
        List<Integer> output = null;
        for (var i = 0; i < programSize - 1; i++) {
            long pow8 = BigInteger.valueOf(8).pow(i).longValue();
            long pow2 = BigInteger.valueOf(2).pow(i).longValue();
            int k = 0;
            do {
                long kpow8 = k * pow8;
                for (int j = 0; j < pow2; j++) {
                    long deltaA = j * (pow8 / pow2) + kpow8;
                    reg.a(initA + deltaA);
                    output = execute();
                    if (equalToIndex(program, output, i + 1)) {
                        initA += deltaA;
                        break;
                    }
                }
                k++;
            } while (!equalToIndex(program, output, i + 1));
        }

        return String.valueOf(initA);
    }

    private boolean equalToIndex(List<Integer> list1, List<Integer> list2, int index) {
        for (int i = 0; i <= index; i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

}
