package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class WobbleArm {

    private Servo armServo, upperGrab, lowerGrab;
    private boolean extended;
    private boolean grabbing;

    public WobbleArm(HardwareMap hardwareMap) {
        armServo = hardwareMap.get(Servo.class,("armServo"));
        upperGrab = hardwareMap.get(Servo.class,("upperGrab"));
        lowerGrab = hardwareMap.get(Servo.class,("lowerGrab"));
        extended = false;
    }
    public WobbleArm(HardwareMap hardwareMap, boolean extended) {
        armServo = hardwareMap.get(Servo.class,("armServo"));
        upperGrab = hardwareMap.get(Servo.class,("upperGrab"));
        lowerGrab = hardwareMap.get(Servo.class,("lowerGrab"));
        this.extended = extended;
    }

    public void armToggle() {
        if(extended) {
            armServo.setPosition(0.78);
        } else {
            armServo.setPosition(.2);
        }
    }

    public void grabToggle() {
        if(grabbing) {
            upperGrab.setPosition(0);
            lowerGrab.setPosition(0);
        } else {
            upperGrab.setPosition(1);
            lowerGrab.setPosition(1);
        }
    }
}
