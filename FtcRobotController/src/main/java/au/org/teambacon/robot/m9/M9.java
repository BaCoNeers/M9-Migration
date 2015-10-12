package au.org.teambacon.robot.m9;

import com.qualcomm.robotcore.hardware.DcMotorController;

import java.util.ArrayList;

import au.org.teambacon.control.BMotor;
import au.org.teambacon.control.BMotorController;
import au.org.teambacon.wrapper.BRobot;

public class M9 extends BRobot {
    @Override
    public void bdefine() {
        // super'ed for copy-paste usage
        super.DriveLeft = new BMotor(hardwareMap.dcMotor.get("drive_left"));
        super.DriveLeft.setType(BMotor.BMotorType.TETRIX);

        super.DriveRight = new BMotor(hardwareMap.dcMotor.get("drive_right"));
        super.DriveRight.setType(BMotor.BMotorType.TETRIX);

        super.DriveController = new BMotorController(hardwareMap.dcMotorController.get("drive_controller"), this.DriveLeft, this.DriveRight);

        super.DriveController.setDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
        BRobot.flush();

        /*new BMotor("motor_lift_1", BMotor.BMotorType.NEVEREST).add();
        new BMotor("motor_lift_2", BMotor.BMotorType.TETRIX).add();

        new BMotor("motor_lift_3", BMotor.BMotorType.TETRIX).add();
        new BMotor("motor_screwlift", BMotor.BMotorType.TETRIX).add();*/
    }
}
