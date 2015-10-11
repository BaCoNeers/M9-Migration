package com.qualcomm.ftcrobotcontroller.opmodes;

import au.org.teambacon.control.BMotor;
import au.org.teambacon.robot.m9.M9;

public class AutoOp extends M9 {
  BMotor Motor;

  @Override
  public void binit() {
    Motor = new BMotor(hardwareMap.dcMotor.get("drive_left"));
    Motor.setType(BMotor.BMotorType.NEVEREST);
  }

  @Override
  public void bloop() {
    telemetry.addData("power:", Double.toString(Motor.getPower()));
    telemetry.addData("target:", Integer.toString(Motor.getTargetPosition()));
    telemetry.addData("rotations:", Double.toString(Motor.getCurrentRotations()));
    telemetry.addData("position:", Integer.toString(Motor.getCurrentPosition()));

    if (Motor.update())
      State++;

    switch (State) {
      case 0:
        Motor.setTargetRotations(0.5);
        Motor.setPower(0.3);
        break;
      case 1:
        Motor.setTargetPosition(1440);
        Motor.setPower(0.6);
        break;
      case 2:
        Motor.setTargetPosition(2000);
        Motor.setPower(1);
    }
  }
}