package mk.aoc24.either;

public final class Left<L, R> extends Either<L, R> {

    private final L value;

    Left(L left) {
        this.value = left;
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public L getLeft() {
        return value;
    }

    @Override
    public R getRight() {
        throw new IllegalStateException("missing value");
    }

}
