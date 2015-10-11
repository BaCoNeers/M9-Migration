package au.org.teambacon.utils;

import au.org.teambacon.wrapper.BRobot;

public class Rotations {
    public static int convertToTicks(double rotations) {
        return (int) (rotations * BRobot.DriveLeft.getType().getEncoderPPR());
    }
}
