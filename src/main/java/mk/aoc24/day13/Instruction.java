package mk.aoc24.day13;

public class Instruction {

    final Point a;
    final Point b;
    Point p;

    public Instruction(Point buttonA, Point buttonB, Point prize) {
        this.a = buttonA;
        this.b = buttonB;
        this.p = prize;
    }

}
