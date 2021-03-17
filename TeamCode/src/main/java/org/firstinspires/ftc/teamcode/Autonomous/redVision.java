package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subSystems.WobbleArm;
import org.firstinspires.ftc.teamcode.subSystems.VisionSensor;

@Autonomous(group = "drive")
public class redVision extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        WobbleArm wobbleArm = new WobbleArm(hardwareMap);
        VisionSensor vision = new VisionSensor(hardwareMap);
        int ringAmount = 0;
        vision.activateTfod();
        drive.setPoseEstimate(new Pose2d(-60, -48, 0));
        Trajectory traj01 = drive.trajectoryBuilder(new Pose2d(-60, -48, 0))
                .splineTo(new Vector2d(1,-60), 0)
                .build();

        Trajectory traj02 = drive.trajectoryBuilder(traj01.end(), 90)
                .splineToSplineHeading(new Pose2d(-12, -24, Math.toRadians(90)), Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-24, -24, Math.toRadians(180)), Math.toRadians(180))
                .build();

        Trajectory traj03 = drive.trajectoryBuilder(traj02.end(), 0)
                .splineToSplineHeading(new Pose2d(15, -36, Math.toRadians(270)), 0)
                .build();

        Trajectory traj11 = drive.trajectoryBuilder(new Pose2d(-60, -48, 0))
                .splineTo(new Vector2d(-24,-60), 0)
                .splineTo(new Vector2d(20, -36), 0)
                .build();

        Trajectory traj12 = drive.trajectoryBuilder(traj11.end(), 90)
                .splineToSplineHeading(new Pose2d(-12, -24, Math.toRadians(180)), Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-24, -24, Math.toRadians(180)), Math.toRadians(180))
                .build();

        Trajectory traj13 = drive.trajectoryBuilder(traj12.end(), 0)
                .splineToSplineHeading(new Pose2d(15, -36, Math.toRadians(0)), 0)
                .build();

        Trajectory traj41 = drive.trajectoryBuilder(new Pose2d(-60, -48, 0))
                .splineTo(new Vector2d(42,-60), 0)
                .build();

        Trajectory traj42 = drive.trajectoryBuilder(traj41.end(), 90)
                .splineToSplineHeading(new Pose2d(-24, -24, Math.toRadians(180)), Math.toRadians(180))
                .build();

        Trajectory traj43 = drive.trajectoryBuilder(traj42.end(), 0)
                .splineToSplineHeading(new Pose2d(60, -42, Math.toRadians(0)), 0)
                .build();

        Trajectory traj44 = drive.trajectoryBuilder(traj43.end(), 180)
                .splineToConstantHeading(new Vector2d(12, -42), Math.toRadians(180))
                .build();

        while(!isStarted()) {
            vision.updateRecognitions();
            for(int i = 0; i < vision.getRecognitionSize(); i ++) {
                if(vision.getLabel(i).equals("Quad")) {
                    ringAmount = 4;
                    break;
                } else if(vision.getLabel(i).equals("Single")) {
                    ringAmount = 1;
                }
            }
            telemetry.addData("Rings detected: ", ringAmount);
            telemetry.update();
        }

        waitForStart();

        if (isStopRequested()) return;
        vision.shutDownTfod();

        switch(ringAmount) {
            case 1:
                wobbleArm.armExtend();
                sleep(1000);
                wobbleArm.grabGrab();
                sleep(2000);

                drive.followTrajectory(traj11);
                //release wobble
                wobbleArm.grabRelease();
                sleep(1000);
                wobbleArm.armRetract();
                sleep(2000);

                drive.followTrajectory(traj12);
                //grab second wobble

                wobbleArm.armExtend();
                sleep(1000);
                wobbleArm.grabGrab();
                sleep(2000);

                drive.followTrajectory(traj13);

                wobbleArm.grabRelease();
                sleep(1000);
                wobbleArm.armRetract();
                sleep(2000);
                break;
            case 4:
                wobbleArm.armExtend();
                sleep(1000);
                wobbleArm.grabGrab();
                sleep(2000);

                drive.followTrajectory(traj41);
                //release wobble
                wobbleArm.grabRelease();
                sleep(1000);
                wobbleArm.armRetract();
                sleep(2000);

                drive.followTrajectory(traj42);
                //grab second wobble

                wobbleArm.armExtend();
                sleep(1000);
                wobbleArm.grabGrab();
                sleep(2000);

                drive.followTrajectory(traj43);

                wobbleArm.grabRelease();
                sleep(1000);
                wobbleArm.armRetract();
                sleep(2000);
            default:
                wobbleArm.armExtend();
                sleep(1000);
                wobbleArm.grabGrab();
                sleep(2000);

                drive.followTrajectory(traj01);
                //release wobble
                wobbleArm.grabRelease();
                sleep(1000);
                wobbleArm.armRetract();
                sleep(2000);

                drive.followTrajectory(traj02);
                //grab second wobble

                wobbleArm.armExtend();
                sleep(1000);
                wobbleArm.grabGrab();
                sleep(2000);

                drive.followTrajectory(traj03);

                wobbleArm.grabRelease();
                sleep(1000);
                wobbleArm.armRetract();
                sleep(2000);
        }

    }
}
