package org.firstinspires.ftc.teamcode.joystickreplay;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.*;

import java.util.List;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.*;

@Autonomous(name="Joystick Replay", group="Linear Opmode")
public class JoystickReplay extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private ControlHandler handler = null;
    private InputReader reader = getInputReader();
    private List<GamepadPair> log = reader.readInput();

    public InputReader getInputReader() {
        return new FileInputReader();
    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        DcMotor leftDrive1  = hardwareMap.get(DcMotor.class, "left_drive_1");
        DcMotor leftDrive2  = hardwareMap.get(DcMotor.class, "left_drive_2");
        DcMotor rightDrive1 = hardwareMap.get(DcMotor.class, "right_drive_1");
        DcMotor rightDrive2 = hardwareMap.get(DcMotor.class, "right_drive_2");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive1.setDirection(REVERSE);
        leftDrive2.setDirection(REVERSE);
        rightDrive1.setDirection(FORWARD);
        rightDrive2.setDirection(FORWARD);

        handler = new BigwheelControlHandler(leftDrive1, leftDrive2, rightDrive1, rightDrive2,
                runtime, telemetry);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        for (int i = 0; opModeIsActive() && i < log.size(); i++) {
            GamepadPair pair = log.get(i);
            handler.runIteration(pair);
            sleep(100);
        }
    }
}
