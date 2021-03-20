package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class WobbleArm {

    private Servo armServo, upperGrab, lowerGrab;
    private boolean extended; //true/false extended/retracted
    private boolean grabbing;//true/false grabbing/open
    //Initializes Servos, and extended and grabbing variables

    public WobbleArm(HardwareMap hardwareMap) {
        armServo = hardwareMap.get(Servo.class,("armServo"));
        upperGrab = hardwareMap.get(Servo.class,("upperGrab"));
        lowerGrab = hardwareMap.get(Servo.class,("lowerGrab"));
        extended = false;
        grabbing = false;
    }
    //Constructor

    public WobbleArm(HardwareMap hardwareMap, boolean extended) {
        armServo = hardwareMap.get(Servo.class,("armServo"));
        upperGrab = hardwareMap.get(Servo.class,("upperGrab"));
        lowerGrab = hardwareMap.get(Servo.class,("lowerGrab"));
        this.extended = extended;
    }
    //second constructor, sets the exteneded value on initialization in case Auton needs it

    public void armToggle() {
        if(extended) {
            armServo.setPosition(.25);
        } else {
            armServo.setPosition(.8);
        }
        extended = !extended;
    }
    //Toggles the arm to extended/retracted

    public void armExtend() {
        armServo.setPosition(.8);
        extended = true;
    }
    //extends the arm

    public void armRetract() {
        armServo.setPosition(.25);
        extended = false;
    }
    //retracts the arm

    public void grabToggle() {
        if(grabbing) {
            upperGrab.setPosition(0);
            lowerGrab.setPosition(0);
        } else {
            upperGrab.setPosition(1);
            lowerGrab.setPosition(1);
        }
        grabbing = !grabbing;
    }
    //toggles the grabber from grabbing/open

    public void grabRelease() {
        upperGrab.setPosition(0);
        lowerGrab.setPosition(0);
        grabbing = false;
    }
    //releases the grabber

    public void grabGrab() {
        upperGrab.setPosition(1);
        lowerGrab.setPosition(1);
        grabbing = true;
    }
    //closes the grabber
}
