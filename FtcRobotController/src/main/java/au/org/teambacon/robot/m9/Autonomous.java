package au.org.teambacon.robot.m9;

import au.org.teambacon.actions.DriveAction;
import au.org.teambacon.utils.Calculate;

public class Autonomous extends M9 {
    @Override
    public void binit() {
        super.bdefine(); // define controls
    }

    @Override
    public void bdefineauto() { // define autonomous steps
        new DriveAction(1, Calculate.rotationsToTicks(1));
        new DriveAction(0.5, Calculate.rotationsToTicks(2));
    }

    @Override
    public void bloop() {
        
    }
}
