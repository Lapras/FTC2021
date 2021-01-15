package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Chassis {
    //class for Chassis/Drive in general including wheel motors
    //and imu
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private BNO055IMU imu;

    public Chassis(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        //constructor for Chassis Class
        //THIS CANNOT BE USED IN TANDEM WTIH SampleMecanumDrive();
        //as such this is for Tele-op ONLY! I still have to figure out
        // a way to integrate Roadrunner with tele op
    }

    public void assignWheelPower(double powers[]) {
        frontLeft.setPower(powers[0]);
        frontRight.setPower(powers[1]);
        backRight.setPower((powers[2]));
        backLeft.setPower((powers[3]));
        //simple class to assign the power of each wheel to an array of doubles, starting with the front left at index 0, going clockwise
    }

    public double[] getMotorPowers() {
        return new double[] {
                frontLeft.getPower(), frontRight.getPower(), backLeft.getPower(), backRight.getPower()
        };
        //returns motor powers in the same format as assignWheelPower()
    }

    public double getHeading() {
        return Math.toDegrees(imu.getAngularOrientation().firstAngle);
    }
    //gets the heading from the imu (not tested with new update yet)

    public double[] mecanumPowers(double xInput, double yInput, double rotation) {
        if(Math.abs(xInput) <= 0.05) xInput = 0;
        if(Math.abs(yInput) <= 0.05) yInput = 0;
        if(Math.abs(rotation) <= 0.05) rotation = 0;
        //set deadzones

        double frontLeftPower = rotation + yInput + xInput;
        double frontRightPower = -rotation + yInput - xInput;
        double backRightPower = -rotation + yInput + xInput;
        double backLeftPower = rotation + yInput - xInput;
        //each axis of motion corresponds to a forward
        // or backwards drive of a specific motor.
        // Add or subtract these values to combine all inputs

        double max = Math.abs(Math.max(1, frontLeftPower));
        max = Math.abs(Math.max(max, frontRightPower));
        max = Math.abs(Math.max(max, backRightPower));
        max = Math.abs(Math.max(max, backLeftPower));
        frontLeftPower /= max;
        frontRightPower /= max;
        backRightPower /= max;
        backLeftPower /= max;
        //normalize all values
        return new double[]{frontLeftPower, frontRightPower, backRightPower, backLeftPower};
    }
    //mecanumPowers takes an x, y, and rotation input and translates this to motor powers for a mecanum drive
    //does not directly set motor powers so this can be used for telemetry as well
}