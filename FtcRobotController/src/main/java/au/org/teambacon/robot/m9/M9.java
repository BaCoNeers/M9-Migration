package au.org.teambacon.robot.m9;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import au.org.teambacon.control.BMotor;
import au.org.teambacon.control.BMotorController;
import au.org.teambacon.control.BServo;
import au.org.teambacon.wrapper.BRobot;

public abstract class M9 extends BRobot {
    @Override
    public void bdefine() {
        // super'ed for copy-paste usage
        super.DriveController = new BMotorController("drive_controller");

        BMotorController motor_controller1 = new BMotorController("motor_controller1");
        BMotorController motor_controller2 = new BMotorController("motor_controller2");


        super.DriveLeft = new BMotor("drive_left", super.DriveController, BMotor.BMotorType.TETRIX_ENCODER_LEGACY, DcMotor.Direction.FORWARD);
<<<<<<< HEAD
        super.DriveRight = new BMotor("drive_right", super.DriveController, BMotor.BMotorType.TETRIX_ENCODER_LEGACY, DcMotor.Direction.REVERSE);


        super.addServo(new BServo("servo_bucket"), "bucket");
        super.addServo(new BServo("servo_goalattach"), "goalattach");

        super.addServo(new BServo("servo_leftarm"), "leftarm");
        super.addServo(new BServo("servo_rightarm"), "rightarm");


        super.addMotor(new BMotor("motor_screwlift", motor_controller1, BMotor.BMotorType.LEGACY, DcMotor.Direction.FORWARD), "screwlift");
        super.addMotor(new BMotor("motor_lift1", motor_controller1, BMotor.BMotorType.LEGACY, DcMotor.Direction.REVERSE), "lift1");

        super.addMotor(new BMotor("motor_lift2", motor_controller2, BMotor.BMotorType.LEGACY, DcMotor.Direction.REVERSE), "lift2");
        super.addMotor(new BMotor("motor_lift3", motor_controller2, BMotor.BMotorType.LEGACY, DcMotor.Direction.REVERSE), "lift3");
=======
        super.DriveRight = new BMotor("drive_right", super.DriveController, BMotor.BMotorType.TETRIX_ENCODER_LEGACY, DcMotor.Direction.FORWARD);

        super.addMotor(new BMotor("motor_screwlift", new BMotorController("motorcontroller_1"), BMotor.BMotorType.LEGACY, DcMotor.Direction.FORWARD));
>>>>>>> origin/master
    }
}
