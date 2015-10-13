package au.org.teambacon.robot.m9;

import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import au.org.teambacon.actions.DriveAction;
import au.org.teambacon.utils.Calculate;

public class Autonomous extends M9 {
    //public OpticalDistanceSensor distance = hardwareMap.opticalDistanceSensor.get("distance");

    @Override
    public void binit() {
        super.bdefine(); // define controls

    }

    @Override
    public void bdefineauto() { // define autonomous steps
        new DriveAction(0.3, 1440);
        //new DriveAction(0.5, Calculate.rotationsToTicks(2));
    }

    @Override
    public void bloop() {
        //telemetry.addData("distance", distance.getLightDetected());
    }
}
