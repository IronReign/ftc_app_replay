package org.firstinspires.ftc.teamcode.joystickreplay;

import android.util.Log;

import java.util.List;

/**
 * Created by IronReign on 8/18/2018.
 */

public class JavaOutputWriter implements OutputWriter {


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

    @Override
    public void writeOutput(List<GamepadPair> log) {
        logLargeString("ReplayData", format(log));
    }
}
