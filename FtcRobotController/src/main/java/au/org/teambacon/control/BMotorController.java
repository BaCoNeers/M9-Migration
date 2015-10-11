package au.org.teambacon.control;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class BMotorController {
    public DcMotorController MotorController;

    public BMotor Motor1;

    public BMotor Motor2;

    public BMotorController(DcMotorController motorcontroller, BMotor motor1, BMotor motor2) {
        this.MotorController = motorcontroller;

        this.Motor1 = motor1;
        this.Motor2 = motor2;
    }

    public void setPower(double power) {
        this.Motor1.setPower(power);
        this.Motor2.setPower(power);
    }
}
