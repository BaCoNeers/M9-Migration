package au.org.teambacon.actions;

import au.org.teambacon.control.BMotor;
import au.org.teambacon.wrapper.BRobot;

public abstract class Action {
    public BMotor DriveLeft = BRobot.DriveLeft;
    public BMotor DriveRight = BRobot.DriveRight;

    public final void register(Action action) {
        BRobot.actionHandler.registerAction(action);
    }

    public abstract void init();

    public abstract void prime();

    public abstract void start();

    public abstract boolean loop();

    public abstract void end();
}