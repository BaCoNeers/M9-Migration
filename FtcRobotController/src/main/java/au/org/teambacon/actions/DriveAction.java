package au.org.teambacon.actions;

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
        DriveLeft.setTarget(this.TicksLeft);
        DriveRight.setTarget(this.TicksRight);
    }

    public void start() {
        DriveLeft.setPower(this.PowerLeft);
        DriveRight.setPower(this.PowerRight);
    }

    public boolean loop() {
        return true;
    }

    public void end() {

    }
}
