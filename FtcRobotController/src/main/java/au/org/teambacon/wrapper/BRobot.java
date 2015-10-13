package au.org.teambacon.wrapper;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;

import au.org.teambacon.actions.ActionHandler;
import au.org.teambacon.control.BMotor;
import au.org.teambacon.control.BMotorController;

public class BRobot extends OpMode {
    public static BMotor DriveLeft;
    public static BMotor DriveRight;
    public static BMotorController DriveController;

    public static BRobot Instance;

    public static ActionHandler actionHandler;

    protected int LoopCount = 0;

    protected int State = 0;

    protected ElapsedTime Runtime = new ElapsedTime();

    public BRobot() {
        Instance = this;

        this.actionHandler = new ActionHandler(this);
    }

    public final void init() {
        this.Runtime.reset();
        this.binit();
    }

    public final void loop() {
        this.LoopCount++;

        if (this.LoopCount == 1) {
            this.bdefineauto();
            return; // flush everything
        }

        if (this.bauto())
            return;

        telemetry.addData("State", Integer.toString(State));

        this.bloop();
    }

    public void bdefine() {
    }

    public void binit() {
    }

    public void bdefineauto() {
    }

    public final boolean bauto() {
        if (actionHandler.ActionList.isEmpty())
            return false;

        actionHandler.handle();
        return true;
    }

    public void bloop() {
    }
}
