package au.org.teambacon.robot.m9;

import com.qualcomm.robotcore.hardware.DcMotor;

import au.org.teambacon.control.BMotor;
import au.org.teambacon.control.BMotorController;
import au.org.teambacon.wrapper.BRobot;

public class M9 extends BRobot {
    @Override
    public void bdefine() {
        // super'ed for copy-paste usage
        super.DriveController = new BMotorController("drive_controller");

        super.DriveLeft = new BMotor("drive_left", super.DriveController, BMotor.BMotorType.TETRIX_ENCODER_LEGACY, DcMotor.Direction.FORWARD);
        super.DriveRight = new BMotor("drive_right", super.DriveController, BMotor.BMotorType.TETRIX_ENCODER_LEGACY, DcMotor.Direction.FORWARD);
    }
}
