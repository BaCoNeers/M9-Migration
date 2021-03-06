package au.org.teambacon.actions;

import com.qualcomm.robotcore.hardware.DcMotorController;

import au.org.teambacon.wrapper.BRobot;

public class DriveAction extends Action {
    protected double PowerLeft;
    protected double PowerRight;
    protected int TicksLeft;
    protected int TicksRight;

    public DriveAction(double power, int ticks) {
        if (!DriveLeft.getType().hasEncoder() && !DriveRight.getType().hasEncoder())
            return;

        super.register(this);

        this.PowerLeft = -power;
        this.PowerRight = -power;
        this.TicksLeft = ticks;
        this.TicksRight = ticks;
    }

    public DriveAction(double powerLeft, double powerRight, int ticksLeft, int ticksRight) {
        if (!DriveLeft.getType().hasEncoder() && !DriveRight.getType().hasEncoder())
            return;

        super.register(this);

        this.PowerLeft = powerLeft;
        this.PowerRight = powerRight;
        this.TicksLeft = ticksLeft;
        this.TicksRight = ticksRight;
    }

    public void init() {
        DriveLeft.setTarget(this.TicksLeft); // no
        DriveRight.setTarget(this.TicksRight); // no

        DriveLeft.getController().setDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY); // help out
        DriveRight.getController().setDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY); // help out

        DriveLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS); // write
        DriveRight.setMode(DcMotorController.RunMode.RESET_ENCODERS); // write

        BRobot.flush();
    }

    public void prime() {
        BRobot.flush();

        DriveLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS); // write
        DriveRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS); // write

        DriveLeft.setPower(this.PowerLeft); // write
        DriveRight.setPower(this.PowerRight); // write
    }

    public void start() {
        BRobot.flush();

        DriveLeft.getController().setDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        DriveRight.getController().setDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
    }

    public boolean loop() {
        BRobot.Instance.telemetry.addData("Left", DriveLeft.getTarget());
        BRobot.Instance.telemetry.addData("Left1", DriveLeft.getPower());
        BRobot.Instance.telemetry.addData("Left2", DriveLeft.getPosition());
        BRobot.Instance.telemetry.addData("Right", DriveRight.getTarget());
        BRobot.Instance.telemetry.addData("Right1", DriveRight.getPower());
        BRobot.Instance.telemetry.addData("Right2", DriveRight.getPosition());


        if (DriveLeft.getTarget() >= 0) { // no
            if (DriveLeft.getPosition() >= DriveLeft.getTarget() && DriveRight.getPosition() >= DriveRight.getPosition()) { // read
                DriveLeft.getController().setDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY); // help out end()
                DriveRight.getController().setDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY); // help out end()
                return true;
            }
        } else {
            if (DriveLeft.getPosition() <= DriveLeft.getTarget() && DriveRight.getPosition() <= DriveRight.getPosition()) { // read
                DriveLeft.getController().setDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY); // help out end()
                DriveRight.getController().setDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY); // help out end()
                return true;
            }
        }

        return false;
    }

    public void end() {
        DriveLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        DriveRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        DriveLeft.setPower(0); // write
        DriveRight.setPower(0); // write
    }
}
