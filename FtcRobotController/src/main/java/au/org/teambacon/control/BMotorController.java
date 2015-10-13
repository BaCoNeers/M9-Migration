package au.org.teambacon.control;

import com.qualcomm.robotcore.hardware.DcMotorController;

import au.org.teambacon.wrapper.BRobot;

public class BMotorController {
    protected DcMotorController MotorController;

    public BMotorController(String motorController) {
        this.MotorController = BRobot.Instance.hardwareMap.dcMotorController.get(motorController);
    }

    public DcMotorController getMotorController() {
        return this.MotorController;
    }

    public void setPower(double power) {
        this.MotorController.setMotorPower(1, power);
        this.MotorController.setMotorPower(2, power);
    }

    public void setDeviceMode(DcMotorController.DeviceMode deviceMode) {
        BRobot.flush();

        this.MotorController.setMotorControllerDeviceMode(deviceMode);
    }

    public DcMotorController.DeviceMode getDeviceMode() {
        return this.MotorController.getMotorControllerDeviceMode();
    }
}
