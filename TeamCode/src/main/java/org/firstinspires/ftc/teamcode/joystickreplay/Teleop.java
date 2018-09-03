package org.firstinspires.ftc.teamcode.joystickreplay;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.*;

import java.util.ArrayList;
import java.util.List;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.*;

@TeleOp(name="Teleop", group="Linear Opmode")
public class Teleop extends JoystickReplayRecorder {

    @Override public OutputWriter[] getOutputWriters() { return new OutputWriter[0]; };

}
