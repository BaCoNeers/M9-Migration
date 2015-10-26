package au.org.teambacon.robot.m9;

import com.qualcomm.robotcore.hardware.DcMotorController;

public class TeleOp extends M9 {
    @Override
    public void binit() {
        super.bdefine();

        super.DriveController.setDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
    }

    @Override
    public void bdefineauto() {
    }

    @Override
    public void bloop() {
        if (gamepad1.a) {
        }

        if (gamepad1.b) {
        }

        if (gamepad1.y) {
            if (super.getMotor("screwlift").getPower() == 1) {
                super.getMotor("screwlift").setPower(0);
            } else if (super.getMotor("screwlift").getPower() == 0) {
                super.getMotor("screwlift").setPower(-1);
            } else if (super.getMotor("screwlift").getPower() == -1) {
                super.getMotor("screwlift").setPower(1);
            }
        }

        if (gamepad1.x) {
        }
    }
}
