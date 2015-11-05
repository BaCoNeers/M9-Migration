package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoTesting extends OpMode {
    public Servo servo;

    @Override
    public void init() {
        servo = hardwareMap.servo.get("servo");
    }

    @Override
    public void loop() {
        if (gamepad1.x) {
            servo.setPosition(0d);
        } else if (gamepad1.a) {
            servo.setPosition(0.5d);
        } else if (gamepad1.b) {
            servo.setPosition(1d);
        }
    }
}