package au.org.teambacon.control;

import android.graphics.Path;

import com.ftdi.j2xx.D2xxManager;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import au.org.teambacon.wrapper.BRobot;

public class BMotor {
    protected DcMotor Motor;

    protected BMotorController Controller;

    protected BMotorType Type;

    private boolean RunToPosition = true;

    public BMotor(DcMotor motor) {
        this.Motor = motor;
    }

    public BMotor(DcMotor motor, DcMotor.Direction direction) {
        this.Motor = motor;
        this.Motor.setDirection(direction);
    }

    public BMotor(DcMotor motor, BMotorType type) {
        this.Motor = motor;
        this.setType(type);
    }

    public BMotor(DcMotor motor, DcMotor.Direction direction, BMotorType type) {
        this.Motor = motor;
        this.Motor.setDirection(direction);
        this.setType(type);
    }

    public BMotor(String name) {
        this.Motor = BRobot.Instance.hardwareMap.dcMotor.get(name);
    }

    public BMotor(String name, DcMotor.Direction direction) {
        this.Motor = BRobot.Instance.hardwareMap.dcMotor.get(name);
        this.Motor.setDirection(direction);
    }

    public BMotor(String name, BMotorType type) {
        this.Motor = BRobot.Instance.hardwareMap.dcMotor.get(name);
        this.setType(type);
    }

    public BMotor(String name, DcMotor.Direction direction, BMotorType type) {
        this.Motor = BRobot.Instance.hardwareMap.dcMotor.get(name);
        this.Motor.setDirection(direction);
        this.setType(type);
    }

    public void add() {
        BRobot.Instance.Motors.add(this);
    }

    public DcMotor getMotor() {
        return this.Motor;
    }

    public void setType(BMotorType type) {
        this.Type = type;
    }

    public BMotorType getType() {
        return this.Type;
    }

    public void setDirection(DcMotor.Direction direction) {
        this.Motor.setDirection(direction);
    }

    public DcMotor.Direction getDirection() {
        return this.Motor.getDirection();
    }

    public void resetEncoder() {
        this.Motor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    public void useEncoder() {
        this.Motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public boolean update() {
        if (!this.RunToPosition || !this.reachedTarget())
            return false;

        this.setPower(0);
        this.resetEncoder();

        if (this.hasReset()) {
            this.useEncoder();
            return true;
        }

        return false;
    }

    public void setController(BMotorController controller) {
        this.Controller = controller;
    }

    public BMotorController getController() {
        return this.Controller;
    }

    public void setTargetPosition(int target) {
        this.Motor.setTargetPosition(target);
    }

    public int getTargetPosition() {
        return this.Motor.getTargetPosition();
    }

    public void setTargetRotations(double rotations) {
        this.setTargetPosition((int) (rotations * this.getType().getEncoderPPR()));
    }

    public double getCurrentRotations() {
        return ((double) this.getTargetPosition()) / this.getType().getEncoderPPR();
    }

    public int getCurrentPosition() {
        return this.Motor.getCurrentPosition();
    }

    public boolean reachedTarget() {
        if (this.getCurrentPosition() >= this.getTargetPosition())
            return true;

        return false;
    }

    public boolean hasReset() {
        if (this.Motor.getCurrentPosition() == 0)
            return true;

        return false;
    }

    public void setPower(double power) {
        this.Motor.setPower(power);
    }

    public double getPower() {
        return this.Motor.getPower();
    }

    public enum BMotorType {
        NEVEREST (1120),
        TETRIX (1440);

        private final int EncoderPPR;

        BMotorType(int encoderPPR) {
            this.EncoderPPR = encoderPPR;
        }

        public int getEncoderPPR() {
            return EncoderPPR;
        }
    }
}