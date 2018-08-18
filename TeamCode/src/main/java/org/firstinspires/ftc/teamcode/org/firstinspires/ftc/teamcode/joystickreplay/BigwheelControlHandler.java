package org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.joystickreplay;

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
        double drive = -pair.y;
        double turn  =  pair.x;

        double leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        double rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        leftDrive1.setPower(leftPower);
        leftDrive2.setPower(leftPower);
        rightDrive1.setPower(rightPower);
        rightDrive2.setPower(rightPower);


        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.update();
    }
}
