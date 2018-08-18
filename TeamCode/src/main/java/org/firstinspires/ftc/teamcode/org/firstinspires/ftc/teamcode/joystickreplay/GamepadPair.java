package org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.joystickreplay;

/**
 * Created by IronReign on 8/18/2018.
 */

public class GamepadPair {
    public double x, y;
    public GamepadPair() {}
    public GamepadPair(double x, double y) {
        this.x=x; this.y=y;
    }
    @Override
    public String toString() {
        return String.format("(X: %.2f, Y: %.2f)", x, y);
    }
}