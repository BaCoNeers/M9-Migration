package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class EncoderTestingOp extends OpMode {
    public DcMotor drive_left;

    public Encoder drive_left_encoder;

    public int state = 0;

    public class Encoder {
        public int CurrentPositionModifier = 0;
        public int OldPosition = 0;

        protected DcMotor Motor;

        public Encoder(DcMotor motor) {
            this.Motor = motor;
        }

        public DcMotor getMotor() {
            return this.Motor;
        }

        public void setCurrentPosition(int position) {
            this.CurrentPositionModifier = position;
            this.OldPosition = this.Motor.getCurrentPosition();
        }

        public int getCurrentPosition() {
            return (this.Motor.getCurrentPosition() - this.OldPosition) + this.CurrentPositionModifier;
        }

        public void setTargetPosition(int target) {
            this.Motor.setTargetPosition((this.OldPosition + target) - this.CurrentPositionModifier);
        }

        public int getTargetPosition() {
            return (this.Motor.getTargetPosition() - this.OldPosition) + this.CurrentPositionModifier;
        }
    }

    @Override
    public void init() {
        drive_left = hardwareMap.dcMotor.get("MOTOR");
        drive_left.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        drive_left_encoder = new Encoder(drive_left);
    }

    @Override
    public void loop() {
        if (this.drive_left.getChannelMode() == DcMotorController.RunMode.RUN_TO_POSITION) { // because RUN_TO_POSITION is useless
            /*if (drive_left_encoder.getCurrentPosition() < 0 && drive_left_encoder.getTargetPosition() < 0) { // whether or not the target is a negative position
                if (drive_left_encoder.getCurrentPosition() <= drive_left_encoder.getTargetPosition()) {
                    drive_left.setPower(0); // stop motor
                    drive_left_encoder.setCurrentPosition(0); // fake reset encoder
                    this.state++; // next state
                }
            } else {
                if (drive_left_encoder.getCurrentPosition() >= drive_left_encoder.getTargetPosition()) {
                    drive_left.setPower(0); // stop motor
                    drive_left_encoder.setCurrentPosition(0); // fake reset encoder
                    this.state++; // next state
                }
            }*/

            if (drive_left_encoder.getCurrentPosition() >= drive_left_encoder.getTargetPosition()) {
                drive_left.setPower(0);
                drive_left_encoder.setCurrentPosition(0);
                this.state++;
            }
        }

        telemetry.addData("State", this.state);

        telemetry.addData("Current position", drive_left_encoder.getCurrentPosition());
        telemetry.addData("Target position", drive_left_encoder.getTargetPosition());

        telemetry.addData("Real current position", drive_left.getCurrentPosition());
        telemetry.addData("Real target position", drive_left.getTargetPosition());

        switch (this.state) {
            case 0:
                drive_left.setPower(1);
                drive_left_encoder.setTargetPosition(1220);
                break;
            case 1:
                drive_left.setPower(0.5);
                drive_left_encoder.setTargetPosition(720);
                break;
            case 2:
                drive_left_encoder.setCurrentPosition(100);
                // current position must be set only once; so we give it its on state
                this.state++;
                break;
            case 3:
                drive_left_encoder.setTargetPosition(500);
                break;
            case 4:
                drive_left_encoder.setTargetPosition(-700);
                break;
            case 5:
                drive_left_encoder.setTargetPosition(-1000);
            default:
                break;
        }
    }
}