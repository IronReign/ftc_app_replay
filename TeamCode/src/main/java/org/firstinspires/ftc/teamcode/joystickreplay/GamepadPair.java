package org.firstinspires.ftc.teamcode.joystickreplay;

/**
 * Created by IronReign on 8/18/2018.
 */

public class GamepadPair {
    public double x, y;
    public boolean bX, bY, bA, bB;
    public GamepadPair() {}
    public GamepadPair(double x, double y) {
        this.x=x; this.y=y;
    }
    public GamepadPair(double x, double y, boolean bX, boolean bY, boolean bA, boolean bB) {
        this(x, y);
        this.bX = bX; this.bY = bY;
        this.bA = bA; this.bB = bB;
    }
    @Override
    public String toString() {
        return String.format("(X: %.2f, Y: %.2f, %s%s%s%s)", x, y,
                fmtBtn(bX, "X"), fmtBtn(bY, "Y"), fmtBtn(bA, "A"), fmtBtn(bB, "B"));
    }

    public static String fmtBtn(boolean b, String s) {
        return b ? s : "";
    }
}