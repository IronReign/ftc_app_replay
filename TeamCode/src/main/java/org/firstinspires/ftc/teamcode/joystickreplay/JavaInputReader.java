package org.firstinspires.ftc.teamcode.joystickreplay;

import java.util.List;

/**
 * Created by IronReign on 8/18/2018.
 */

public class JavaInputReader implements InputReader {
    @Override
    public List<GamepadPair> readInput() {
        return JoystickReplayData.log;
    }
}
