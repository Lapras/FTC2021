package org.firstinspires.ftc.teamcode.testOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subSystems.Shooter;

@TeleOp
public class ShooterTesting extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Shooter shooter = new Shooter(hardwareMap);

        while(opModeIsActive()) {
            shooter.motorSpin(1);
            telemetry.addData("MotorPower: ", shooter.getPower());
            getRuntime();
        }
    }
}
