package au.org.teambacon.control;

import com.qualcomm.robotcore.hardware.DcMotorController;

public class BMotorController {
    protected DcMotorController MotorController;

    protected BMotor Motor1;

    protected BMotor Motor2;

    public BMotorController(DcMotorController motorcontroller, BMotor motor1, BMotor motor2) {
        this.MotorController = motorcontroller;

        this.Motor1 = motor1;
        this.Motor2 = motor2;
    }

    public DcMotorController getMotorController() {
        return this.MotorController;
    }

    public void setPower(double power) {
        this.Motor1.setPower(power);
        this.Motor2.setPower(power);
    }

    public void setDeviceMode(DcMotorController.DeviceMode deviceMode) {
        this.MotorController.setMotorControllerDeviceMode(deviceMode);
    }

    public DcMotorController.DeviceMode getDeviceMode() {
        return this.MotorController.getMotorControllerDeviceMode();
    }
}
