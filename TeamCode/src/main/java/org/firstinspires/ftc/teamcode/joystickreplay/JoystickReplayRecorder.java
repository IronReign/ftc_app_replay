package org.firstinspires.ftc.teamcode.joystickreplay;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.*;

import java.util.ArrayList;
import java.util.List;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.*;

@TeleOp(name="Joystick Replay Recorder", group="Linear Opmode")
public class JoystickReplayRecorder extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private List<GamepadPair> log;
    private ControlHandler handler;
    OutputWriter[] writers = { new JavaOutputWriter(), new FileOutputWriter() };

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
        log = new ArrayList<>();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        for (int i = 0; opModeIsActive(); i++) {
            telemetry.addData("Iteration", "%d", i);
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.left_stick_x;
            GamepadPair pair = new GamepadPair(gamepad1.left_stick_x, gamepad1.left_stick_y);
            log.add(pair);
            handler.runIteration(pair);
            sleep(100);
        }

        for (OutputWriter writer : writers) {
            writer.writeOutput(log);
        }
    }

    private static String format(List<GamepadPair> log) {
        StringBuilder str = new StringBuilder(
                "package org.firstinspires.ftc.teamcode.joystickreplay;\n\n" +

                        "public final class JoystickReplayData {\n\n" +

                        "\tprivate JoystickReplayData() {throw new RuntimeException();}\n\n" +

                        "\tpublic static java.util.List<GamepadPair> log = new java.util.ArrayList(").append(log.size()).append(") {{\n");

        for (GamepadPair pair : log) {
            str.append(             "\t\t\tadd(new GamepadPair(").append(pair.x).append(",").append(pair.y).append("));\n");
        }

        return str.append(      "\t}};\n" +
                "}\n")
                .toString();
    }

    public void logLargeString(String TAG, String str) {
        if(str.length() > 3000) {
            Log.i(TAG, str.substring(0, 3000));
            logLargeString(TAG, str.substring(3000));
        } else {
            Log.i(TAG, str); // continuation
        }
    }

}
