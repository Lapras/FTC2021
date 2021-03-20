package org.firstinspires.ftc.teamcode.subSystems;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter {
    private DcMotorEx shootMotor;

    private CRServo shootServo;

    private PIDCoefficients coeffs = new PIDCoefficients(10, 0, .05);
    private PIDFController controller = new PIDFController(coeffs);
    //FTC Roadrunner PID Controller, and Values

    private double TICKS_PER_REVOLUTION;
    //Initializing variables. Shootmotor is a DcMotorEx in this case because of the GetVelocity() function


    public Shooter(HardwareMap hardwareMap) {
        shootMotor = hardwareMap.get(DcMotorEx.class, "shootMotor");
        shootServo = hardwareMap.get(CRServo.class, "shootServo");

        shootMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shootMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //Initialization
    }

    public void motorSpin(double targetRPM) {
        double RPMIncrement = 10;
        if(shootMotor.getPower() < targetRPM) {
            RPMIncrement = 10;
        }
        if(shootMotor.getPower() > targetRPM) {
            RPMIncrement = -10;
        }
        shootMotor.setVelocity(shootMotor.getVelocity() + RPMIncrement);
        //Extremely basic linear control loop.
        // Increases/Decreases the RPM of the motor by 10 each time it's called
    }

    public void setPIDTarget(double targetRPM) {
        controller.setTargetPosition(targetRPM);
    }
    //Sets the target RPM of the motor for the PID Controller

    public void motorPIDUpdate() {
        shootMotor.setVelocity(RPM_TO_TICKS(controller.update(getRPM())));
    }
    //Initializes FTC Roadrunner's PID Control loop and updates it, setting a
    //new velocity based off the PID controller

    public void motorStop() {
        shootMotor.setPower(0);
    }
    //STOPS the motor. As ZeroPowerBehavior is float, the motor will slowly
    //"spin down"

    public double getVelocity() {
        return shootMotor.getVelocity();
    }
    //Returns the velocity of Shooter Motor

    public void startShooting() {
        shootServo.setPower(1);
    }
    //begins to Rotate the shooting Servo

    public void stopShooting() {
        shootServo.setPower(0);
    }
    //Stops? the rotation of the shooting servo. CR servos could operate on a scale of -1 to 1
    //or 0 to 1 (0.5 is stopped), I don't know

    private double TICKS_TO_RPM(double ticks) {
        return (ticks/TICKS_PER_REVOLUTION)*60;
    }
    //Converts a Ticks/Second value (read from GetVelocity and SetVelocity) to RPM

    private double getRPM() {
        return TICKS_TO_RPM(shootMotor.getVelocity());
    }
    //Returns the RPM of the motor for a given velocity

    private double RPM_TO_TICKS(double rpm) {
        return rpm/60*TICKS_PER_REVOLUTION;
    }
    //Returns ticks/second given an RPM. This is used to input into setVelocity()
    //Basically, using RPM makes things easier to understand
}
