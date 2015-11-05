package au.org.teambacon.control;

import com.qualcomm.robotcore.hardware.Servo;

import au.org.teambacon.wrapper.BRobot;

public class BServo {
    protected Servo Servo;

    protected double Position = 0;

    public BServo(String name) {
        this.Servo = BRobot.Instance.hardwareMap.servo.get(name);
    }

    public Servo getServo() {
        return this.Servo;
    }

    public void setPosition(double position) {
        this.Servo.setPosition(position);

        this.Position = position;
    }

    public double getPosition() {
        return this.Position;
    }
}
