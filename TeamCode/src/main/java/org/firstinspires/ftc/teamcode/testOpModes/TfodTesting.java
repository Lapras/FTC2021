package org.firstinspires.ftc.teamcode.testOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subSystems.Chassis;
import org.firstinspires.ftc.teamcode.subSystems.WobbleArm;
import org.firstinspires.ftc.teamcode.subSystems.VisionSensor;

@TeleOp
public class TfodTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        VisionSensor vision = new VisionSensor();
        vision.initVuforia(hardwareMap);
        vision.initTfod(hardwareMap);
        vision.activateTfod();
        waitForStart();
        while (opModeIsActive()) {
            vision.updateRecognitions();
            for(int i = 0; i < vision.getRecognitionSize(); i ++) {
                telemetry.addData("Recognition " + i +": ", vision.getLabel(i));
            }
            if(vision.getRecognitionSize() == 0) {
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
