package mk.aoc24.calculator;

import java.util.List;

public interface Operator<T> {

    String syntax();

    int nary();

    int priority();

    boolean hasLeftAssociativity();

    T compute(List<T> values);
}
