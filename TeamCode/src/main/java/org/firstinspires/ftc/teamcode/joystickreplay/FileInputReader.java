package org.firstinspires.ftc.teamcode.joystickreplay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

/**
 * Created by IronReign on 8/18/2018.
 */

public class FileInputReader implements InputReader {
    @Override
    public List<GamepadPair> readInput() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/sdcard/FIRST/replayData.txt"));
            String line;
            List<GamepadPair> log = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                log.add(new GamepadPair(parseDouble(split[0]), parseDouble(split[1])));
            }
            reader.close();
            return log;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
