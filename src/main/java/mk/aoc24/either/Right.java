package mk.aoc24.either;

public final class Right<L, R> extends Either<L, R> {

    private final R value;

    Right(R right) {
        this.value = right;
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public boolean isRight() {
        return true;
    }

    @Override
    public L getLeft() {
        throw new IllegalStateException("missing value");
    }

    @Override
    public R getRight() {
        return value;
    }

}
