package org.firstinspires.ftc.teamcode.joystickreplay;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by IronReign on 8/18/2018.
 */

public class FileOutputWriter implements OutputWriter {

    @Override
    public void writeOutput(List<GamepadPair> log) {
        try {
            Log.i("ReplayData", "Starting Output");
            PrintWriter writer = new PrintWriter(new FileWriter("/sdcard/FIRST/replayData.txt"));
            for (GamepadPair pair : log) {
                writer.printf("%f,%f,%d,%d,%d,%d,%n", pair.x, pair.y,
                        pair.bX?1:0, pair.bY?1:0, pair.bA?1:0, pair.bB?1:0);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
