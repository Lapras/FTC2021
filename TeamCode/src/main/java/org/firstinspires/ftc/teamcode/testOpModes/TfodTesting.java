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
        VisionSensor vision = new VisionSensor(hardwareMap);

        while (opModeIsActive()) {
            for(int i = 0; i < vision.getRecognitionSize(); i ++) {
                telemetry.addData("Recognition " + i +": ", vision.getLabel(i));
            }
            telemetry.update();
        }
    }
}
