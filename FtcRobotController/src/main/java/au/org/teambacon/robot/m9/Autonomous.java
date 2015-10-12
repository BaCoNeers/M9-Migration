package au.org.teambacon.robot.m9;

import au.org.teambacon.actions.DriveAction;
import au.org.teambacon.utils.Rotations;

public class Autonomous extends M9 {
    @Override
    public void binit() {
        super.bdefine(); // define controls
    }

    @Override
    public void bdefineauto() { // define autonomous steps
        new DriveAction(1, Rotations.convertToTicks(1));
        new DriveAction(0.5, Rotations.convertToTicks(2));
    }

    @Override
    public void bloop() {
        
    }
}
