package mk.aoc24.calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

public class ReversePolishNotation<T> {

    private final Deque<ExpressionElement<T>> evalStack;

    public static <T> T evaluate(List<ExpressionElement<T>> input) {
        return new ReversePolishNotation<T>().eval(input, false);
    }

    public static <T> T evaluate(List<ExpressionElement<T>> input, boolean infix) {
        return new ReversePolishNotation<T>().eval(input, infix);
    }

    private ReversePolishNotation() {
        evalStack = new ArrayDeque<>();
    }

    private T eval(List<ExpressionElement<T>> input, boolean infix) {
        if (infix) {
            input = infixToReversePolishNotation(input);
        }
        input.forEach(this::eval);
        return evalStack.pop().value();
    }

    private void eval(ExpressionElement<T> expressionElement) {
        if (expressionElement.isValue()) {
            evalStack.push(expressionElement);
        } else {
            List<T> values = new ArrayList<>();
            IntStream.range(0, expressionElement.operator().nary()).forEach(i -> values.addFirst(evalStack.pop().value()));
            T result = compute(expressionElement.operator(), values);
            evalStack.push(ExpressionElement.value(result));
        }
    }

    public T compute(Operator<T> operator, List<T> values) {
        return operator.compute(values);
    }

    // Shunting-yard algorithm
    private List<ExpressionElement<T>> infixToReversePolishNotation(List<ExpressionElement<T>> expression) {
        List<ExpressionElement<T>> output = new ArrayList<>();
        Deque<ExpressionElement<T>> stack = new ArrayDeque<>();

        for (ExpressionElement<T> element : expression) {
            if (element.isValue()) {
                output.add(element);
            } else if (element.isLeftParenthesis()) {
                stack.push(element);
            } else if (element.isRightParenthesis()) {
                while (!stack.isEmpty() && !stack.peek().isLeftParenthesis()) {
                    output.add(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && stack.peek().isOperator() && element.operator().hasLeftAssociativity()
                    && element.operator().priority() <= stack.peek().operator().priority()) {
                    output.add(stack.pop());
                }
                stack.push(element);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek().isLeftParenthesis()) {
                throw new IllegalStateException("Expression is invalid");
            }
            output.add(stack.pop());
        }

        return output;
    }

}
