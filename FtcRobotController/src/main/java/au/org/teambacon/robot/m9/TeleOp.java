package au.org.teambacon.robot.m9;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

import au.org.teambacon.wrapper.BRobot;

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
            // harvester
        }

        if (gamepad1.b || gamepad2.b) {
            super.getServo("bucket").setPosition(((super.getServo("bucket").getPosition() == 0.04) ? 0.47 : 0.04));
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
            super.getServo("goalattach").setPosition(((super.getServo("goalattach").getPosition() == 0.9) ? 0.24 : 0.9));
        }

        if (gamepad1.left_bumper) {
            super.getServo("leftarm").setPosition(((super.getServo("leftarm").getPosition() == 0.37) ? 0.88 : 0.37));
        }

        if (gamepad1.right_bumper) {
            super.getServo("rightarm").setPosition(((super.getServo("rightarm").getPosition() == 0.61) ? 0.06 : 0.61));
        }

        // calculate drive motor speed
        float rotation = gamepad1.left_stick_x * 0.8f;

        float power = gamepad1.left_trigger - gamepad1.right_trigger;

        float leftPower = power - rotation;
        float rightPower = power + rotation;

        leftPower = Range.clip(leftPower, -1, 1);
        rightPower = Range.clip(rightPower, -1, 1);

        // set drive motors to calculated power
        super.DriveLeft.setPower(leftPower);
        super.DriveRight.setPower(rightPower);

        // lift movement
        super.getMotor("lift1").setPower(gamepad2.left_stick_y);
        super.getMotor("lift2").setPower(gamepad2.left_stick_y);
        super.getMotor("lift3").setPower(gamepad2.left_stick_y);
    }
}