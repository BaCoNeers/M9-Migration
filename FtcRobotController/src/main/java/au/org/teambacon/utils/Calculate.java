package au.org.teambacon.utils;

import au.org.teambacon.wrapper.BRobot;

public class Calculate {
    public static int rotationsToTicks(double rotations) {
        return (int) (rotations * BRobot.DriveLeft.getType().getEncoderPPR());
    }

    public static int degreesToTicks(double degrees) {
        return (int) (BRobot.DriveLeft.getType().getEncoderPPR() / (degrees / 360));
    }

    /*

    public static int distancemToTicks(double distance) {
        return (int) ;
    }*/
}
