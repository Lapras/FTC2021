package org.firstinspires.ftc.teamcode.testOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class OdometryCalibration extends LinearOpMode {
    private DcMotor leftEncoder, rightEncoder, frontEncoder;
    @Override
    public void runOpMode() throws InterruptedException {
        leftEncoder = hardwareMap.get(DcMotor.class, "leftEncoder");
        rightEncoder = hardwareMap.get(DcMotor.class, "rightEncoder");
        frontEncoder = hardwareMap.get(DcMotor.class, "frontEncoder");

        rightEncoder.setDirection(DcMotorSimple.Direction.REVERSE);
        frontEncoder.setDirection(DcMotorSimple.Direction.REVERSE);

        leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addLine("Ready to Start");
        telemetry.update();
        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("leftEncoderVal", leftEncoder.getCurrentPosition());
            telemetry.addData("rightEncoderVal", rightEncoder.getCurrentPosition());
            telemetry.addData("frontEncoderVal", frontEncoder.getCurrentPosition());
            telemetry.update();
        }
    }
}
