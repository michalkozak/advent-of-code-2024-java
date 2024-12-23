package mk.aoc24.day17;

public class Registers {

    long a;
    long b;
    long c;

    public long a() {
        return a;
    }

    public void a(long a) {
        this.a = a;
    }

    public long b() {
        return b;
    }

    public void b(long b) {
        this.b = b;
    }

    public long c() {
        return c;
    }

    public void c(long c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Registers{" +
            "a=" + a +
            ", b=" + b +
            ", c=" + c +
            '}';
    }

}
