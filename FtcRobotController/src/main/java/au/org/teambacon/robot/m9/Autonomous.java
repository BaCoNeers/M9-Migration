package au.org.teambacon.robot.m9;

import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import au.org.teambacon.actions.DriveAction;
import au.org.teambacon.utils.Calculate;
import au.org.teambacon.wrapper.BRobot;

public class Autonomous extends M9 {
    @Override
    public void binit() {
        super.bdefine();
    }

    @Override
    public void bdefineauto() { // define autonomous steps
        new DriveAction(0.1, Calculate.rotationsToTicks(3.134));
        //new DriveAction(0.5, Calculate.rotationsToTicks(2));
    }

    @Override
    public void bloop() {
    }
}
//Classy Muslim
//Jewish Magician
//Catholic Office Worker
//Buddhist Airline Pilot