package au.org.teambacon.actions;

import au.org.teambacon.wrapper.BRobot;

public class DriveAction extends Action {
    protected double PowerLeft;
    protected double PowerRight;
    protected int TicksLeft;
    protected int TicksRight;

    protected boolean ProgressLeft = false;
    protected boolean ProgressRight = false;

    public DriveAction(double power, int ticks) {
        super.register(this);

        this.PowerLeft = power;
        this.PowerRight = power;
        this.TicksLeft = ticks;
        this.TicksRight = ticks;
    }

    public DriveAction(double powerLeft, double powerRight, int ticksLeft, int ticksRight) {
        super.register(this);

        this.PowerLeft = powerLeft;
        this.PowerRight = powerRight;
        this.TicksLeft = ticksLeft;
        this.TicksRight = ticksRight;
    }

    public void init() {
        DriveLeft.setTargetPosition(this.TicksLeft);
        DriveRight.setTargetPosition(this.TicksRight);
    }

    public void start() {
        DriveLeft.setPower(this.PowerLeft);
        DriveRight.setPower(this.PowerRight);
    }

    public boolean loop() {
        BRobot.Instance.telemetry.addData("left", DriveLeft.getCurrentPosition());
        BRobot.Instance.telemetry.addData("lpower", DriveLeft.getPower());
        BRobot.Instance.telemetry.addData("ltarget", DriveLeft.getTargetPosition());
        BRobot.Instance.telemetry.addData("right", DriveRight.getCurrentPosition());
        BRobot.Instance.telemetry.addData("rpower", DriveRight.getPower());
        BRobot.Instance.telemetry.addData("rtarget", DriveRight.getTargetPosition());

        if (!this.ProgressLeft)
            this.ProgressLeft = DriveLeft.update();

        if (!this.ProgressRight)
            this.ProgressRight = DriveRight.update();

        if (this.ProgressLeft && this.ProgressRight)
            return true;

        return false;
    }

    public void end() {
        DriveLeft.update();
        DriveRight.update();
    }
}
