package org.firstinspires.ftc.teamcode.testOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.subSystems.Chassis;
import org.firstinspires.ftc.teamcode.subSystems.WobbleArm;
import org.firstinspires.ftc.teamcode.subSystems.VisionSensor;

@TeleOp
public class TfodTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        VisionSensor vision = new VisionSensor(hardwareMap);
        vision.activateTfod();
        waitForStart();
        while (opModeIsActive()) {
            vision.updateRecognitions();
            if(vision.getRecognitions() != null) {
                for (int i = 0; i < vision.getRecognitionSize(); i ++) {
                    telemetry.addData("RecognitionLabel:", vision.getLabel(i));
                }
            } else {
                telemetry.addLine("No recognitions");
            }
            if(vision.isNull()) {
                telemetry.addLine("Tfod exists");
            } else {
                telemetry.addLine("Tfod does not exist");
            }
            telemetry.update();
        }
    }
}
