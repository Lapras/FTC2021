package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subSystems.Chassis;

@TeleOp(name = "teleOp", group = "LinearOpMode")
public class MainTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Chassis Chassis = new Chassis(hardwareMap);
        double motorPowers[];
        //initializing subsystems and any variables before start

        //TODO: waiting for start message
        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {
            motorPowers = Chassis.mecanumPowers(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            Chassis.assignWheelPower(motorPowers);
            //uses mecanumPowers from Chassis() and assignWheelPower, basic driving

            telemetry.addData("angle", Chassis.getHeading());
            telemetry.addData("input", "%7f:%7f:%7f",gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.addData("motorOutputs", "%7f:%7f:%7f:%7f", motorPowers[0], motorPowers[1], motorPowers[2], motorPowers[3]);
            telemetry.update();
            //telemetry for teleop
        }
        //Main loop to run during teleop portion
    }
}
