package au.org.teambacon.control;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import au.org.teambacon.wrapper.BRobot;

public class BMotor {
    protected DcMotor Motor;

    protected BMotorController Controller;

    protected BMotorType Type;

    protected int LegacyTarget = 0;

    public BMotor(String name, BMotorController controller, BMotorType type, DcMotor.Direction direction) {
        this.Motor = BRobot.Instance.hardwareMap.dcMotor.get(name);
        this.Controller = controller;
        this.Type = type;
        this.Motor.setDirection(direction);
    }

    public DcMotor getMotor() {
        return this.Motor;
    }

    public BMotorController getController() {
        return this.Controller;
    }

    public BMotorType getType() {
        return this.Type;
    }

    public int getPosition() {
        this.makeSafe(DcMotorController.DeviceMode.READ_ONLY);

        return this.Motor.getCurrentPosition();
    }

    public void setMode(DcMotorController.RunMode mode) {
        this.makeSafe(DcMotorController.DeviceMode.WRITE_ONLY);

        this.makeSafe(mode);
    }

    public DcMotorController.RunMode getMode() {
        this.makeSafe(DcMotorController.DeviceMode.READ_ONLY);

        return this.Motor.getChannelMode();
    }

    public void setPower(double power) {
        this.makeSafe(DcMotorController.DeviceMode.WRITE_ONLY);

        this.Motor.setPower(power);
    }

    public double getPower() {
        this.makeSafe(DcMotorController.DeviceMode.READ_ONLY);

        return this.Motor.getPower();
    }

    public void setDirection(DcMotor.Direction direction) {
        this.Motor.setDirection(direction);
    }

    public DcMotor.Direction getDirection() {
        return this.Motor.getDirection();
    }

    public void setTarget(int target) {
        if (this.Type.isLegacy()) {
            this.LegacyTarget = target;
            return;
        }

        this.Motor.setTargetPosition(target);
    }

    public int getTarget() {
        if (this.Type.isLegacy())
            return this.LegacyTarget;

        return this.Motor.getTargetPosition();
    }

    public enum BMotorType {
        NEVEREST (0, false),
        TETRIX (0, false),
        NEVEREST_ENCODER (1120, false),
        TETRIX_ENCODER (1440, false),
        NEVEREST_ENCODER_LEGACY (1120, true),
        TETRIX_ENCODER_LEGACY (1440, true);

        private final int EncoderPPR;
        private final boolean Legacy;

        BMotorType(int encoderPPR, boolean legacy) {
            this.EncoderPPR = encoderPPR;
            this.Legacy = legacy;
        }

        public int getEncoderPPR() {
            return EncoderPPR;
        }

        public boolean hasEncoder() {
            if (EncoderPPR > 0)
                return true;

            return false;
        }

        public boolean isLegacy() {
            return Legacy;
        }
    }

    private void makeSafe(DcMotorController.RunMode mode) {
        if (!this.Type.hasEncoder())
            return;

        this.makeSafe(DcMotorController.DeviceMode.READ_ONLY);
        while (this.Motor.getChannelMode() != mode) {
            this.makeSafe(DcMotorController.DeviceMode.WRITE_ONLY);

            this.Motor.setChannelMode(mode);

            this.makeSafe(DcMotorController.DeviceMode.READ_ONLY);
        }
    }

    private void makeSafe(DcMotorController.DeviceMode mode) {
        if (!this.getType().isLegacy())
            return;

        while (this.Controller.getDeviceMode() != mode)
            this.Controller.setDeviceMode(mode);
    }
}
