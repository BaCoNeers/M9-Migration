package au.org.teambacon.actions;

import com.qualcomm.robotcore.hardware.DcMotorController;

import au.org.teambacon.wrapper.BRobot;

public class DriveAction extends Action {
    protected double PowerLeft;
    protected double PowerRight;
    protected int TicksLeft;
    protected int TicksRight;

    protected boolean ProgressLeft = false;
    protected boolean ProgressRight = false;

    public DriveAction(double power, int ticks) {
        if (DriveLeft.getType().hasEncoder() && DriveRight.getType().hasEncoder())
            super.register(this);

        this.PowerLeft = power;
        this.PowerRight = power;
        this.TicksLeft = ticks;
        this.TicksRight = ticks;
    }

    public DriveAction(double powerLeft, double powerRight, int ticksLeft, int ticksRight) {
        if (DriveLeft.getType().hasEncoder() && DriveRight.getType().hasEncoder())
            super.register(this);

        this.PowerLeft = powerLeft;
        this.PowerRight = powerRight;
        this.TicksLeft = ticksLeft;
        this.TicksRight = ticksRight;
    }

    public void init() {
        DriveLeft.setTarget(this.TicksLeft);
        DriveRight.setTarget(this.TicksRight);

        DriveLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        DriveRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    public void start() {
        DriveLeft.setPower(this.PowerLeft);
        DriveRight.setPower(this.PowerRight);

        DriveLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        DriveRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public boolean loop() {
        if (DriveLeft.getTarget() >= 0) {
            if (DriveLeft.getPosition() >= DriveLeft.getTarget())
                return true;
        } else {
            if (DriveLeft.getPosition() <= DriveLeft.getTarget())
                return true;
        }

        return false;
    }

    public void end() {
        DriveLeft.setPower(0);
    }
}
