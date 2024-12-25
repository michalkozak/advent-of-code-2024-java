package mk.aoc24.calculator;

public class ExpressionElement<T> {

    private final T value;
    private final Operator<T> operator;
    private final Parenthesis parenthesis;

    public static <T> ExpressionElement<T> value(T value) {
        return new ExpressionElement<>(value);
    }

    public static <T> ExpressionElement<T> operator(Operator<T> operato) {
        return new ExpressionElement<>(operato);
    }

    public static <T> ExpressionElement<T> leftParenthesis() {
        return new ExpressionElement<>(Parenthesis.LEFT);
    }

    public static <T> ExpressionElement<T> rightParenthesis() {
        return new ExpressionElement<>(Parenthesis.RIGHT);
    }

    private ExpressionElement(T value) {
        this.value = value;
        this.operator = null;
        this.parenthesis = null;
    }

    private ExpressionElement(Operator<T> operator) {
        this.value = null;
        this.operator = operator;
        this.parenthesis = null;
    }

    private ExpressionElement(Parenthesis parenthesis) {
        this.value = null;
        this.operator = null;
        this.parenthesis = parenthesis;
    }

    public boolean isValue() {
        return value != null;
    }

    public boolean isOperator() {
        return operator != null;
    }

    public boolean isParenthesis() {
        return parenthesis != null;
    }

    public boolean isLeftParenthesis() {
        return parenthesis != null && parenthesis == Parenthesis.LEFT;
    }

    public boolean isRightParenthesis() {
        return parenthesis != null && parenthesis == Parenthesis.RIGHT;
    }

    public T value() {
        if (isValue()) {
            return value;
        }
        throw new IllegalStateException("missing value");
    }

    public Operator<T> operator() {
        if (isOperator()) {
            return operator;
        }
        throw new IllegalStateException("missing operator");
    }

    public Parenthesis parenthesis() {
        if (isParenthesis()) {
            return parenthesis;
        }
        throw new IllegalStateException("missing parenthesis");
    }

}
