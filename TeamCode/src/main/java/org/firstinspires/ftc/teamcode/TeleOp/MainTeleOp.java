package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subSystems.chassis;

@TeleOp(name = "teleOp", group = "LinearOpMode")
public class MainTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        chassis chassis = new chassis(hardwareMap);
        double motorPowers[];

        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {
            motorPowers = chassis.mecanumPowers(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            chassis.assignWheelPower(motorPowers);

            telemetry.addData("angle", chassis.getHeading());
            telemetry.addData("input", "%7f:%7f:%7f",gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.addData("motorOutputs", "%7f:%7f:%7f:%7f", motorPowers[0], motorPowers[1], motorPowers[2], motorPowers[3]);
        }
    }
}
