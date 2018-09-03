package org.firstinspires.ftc.teamcode.joystickreplay;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by IronReign on 8/18/2018.
 */

public class BigwheelControlHandler implements ControlHandler {

    private final DcMotor leftDrive1;
    private final DcMotor leftDrive2;
    private final DcMotor rightDrive1;
    private final DcMotor rightDrive2;
    private final ElapsedTime runtime;
    private final Telemetry telemetry;

    private boolean xHold = false;
    private int reverse = 1;

    public BigwheelControlHandler(DcMotor leftDrive1, DcMotor leftDrive2,
                                  DcMotor rightDrive1, DcMotor rightDrive2,
                                  ElapsedTime runtime, Telemetry telemetry) {
        this.leftDrive1 = leftDrive1;
        this.leftDrive2 = leftDrive2;
        this.rightDrive1 = rightDrive1;
        this.rightDrive2 = rightDrive2;
        this.runtime = runtime;
        this.telemetry = telemetry;
    }

    @Override
    public void runIteration(GamepadPair pair) {
        double drive = reverse * -pair.y;
        double turn = pair.x;

        double leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        double rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        leftDrive1.setPower(leftPower);
        leftDrive2.setPower(leftPower);
        rightDrive1.setPower(rightPower);
        rightDrive2.setPower(rightPower);

        if(xHold) {
            if (!pair.bX)
                xHold = false;
        } else {
            xHold = pair.bX;
            if(xHold) {
                reverse *= -1;
            }
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Direction", reverse == 1 ? "Forward" : "Backward");
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.update();
    }
}
