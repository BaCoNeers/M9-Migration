package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Testing extends OpMode {
    public DcMotor Motor;

    public DcMotorController Controller;

    public int Count = 0;

    public void init() {
        this.Motor = hardwareMap.dcMotor.get("motor");
        this.Controller = hardwareMap.dcMotorController.get("motor_controller");
    }

    public int getPosition() {
        //if (this.Controller.getMotorControllerDeviceMode() != DcMotorController.DeviceMode.READ_ONLY)
        //    this.Controller.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);

        return this.Motor.getCurrentPosition();
    }

    public void setPower(double power) {
        //if (this.Controller.getMotorControllerDeviceMode() != DcMotorController.DeviceMode.WRITE_ONLY)
        //    this.Controller.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        this.Motor.setPower(power);
    }

    public void loop() {
        this.Controller.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);

        telemetry.addData("Position", getPosition());
        telemetry.addData("Mode", Controller.getMotorControllerDeviceMode().toString());
        telemetry.addData("Power", Double.toString(Motor.getPower()));

        if (Count == 10) {
            Count = 0;
            setPower(0.1);
            return;
        }

        Count++;
    }
}
