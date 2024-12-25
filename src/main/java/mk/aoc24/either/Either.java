package mk.aoc24.either;

public abstract sealed class Either<L, R> permits Left, Right {

    public static <L, R> Either<L, R> left(L left) {
        return new Left<>(left);
    }

    public static <L, R> Either<L, R> right(R right) {
        return new Right<>(right);
    }

    public abstract boolean isLeft();

    public abstract boolean isRight();

    public abstract L getLeft();

    public abstract R getRight();

}
