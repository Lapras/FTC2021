package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subSystems.WobbleArm;

import org.firstinspires.ftc.teamcode.quickStartDrive.SampleMecanumDrive;

import java.util.Vector;

@Autonomous(group = "drive")
public class redThree extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        WobbleArm wobbleArm = new WobbleArm(hardwareMap);

        drive.setPoseEstimate(new Pose2d(-60, -48, 0));
        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d(-60, -48, 0))
                .splineTo(new Vector2d(1,-60), 0)
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end(), 90)
                .splineToSplineHeading(new Pose2d(-12, -24, Math.toRadians(90)), Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-24, -24, Math.toRadians(180)), Math.toRadians(180))
                .build();

        Trajectory traj3 = drive.trajectoryBuilder(traj2.end(), 0)
                .splineToSplineHeading(new Pose2d(15, -36, Math.toRadians(270)), 0)
                .build();
        waitForStart();

        if (isStopRequested()) return;

        wobbleArm.armExtend();
        sleep(1000);
        wobbleArm.grabGrab();
        sleep(2000);

        drive.followTrajectory(traj1);
        //release wobble
        wobbleArm.grabRelease();
        sleep(1000);
        wobbleArm.armRetract();
        sleep(2000);

        drive.followTrajectory(traj2);
        //grab second wobble

        wobbleArm.armExtend();
        sleep(1000);
        wobbleArm.grabGrab();
        sleep(2000);

        drive.followTrajectory(traj3);

        wobbleArm.grabRelease();
        sleep(1000);
        wobbleArm.armRetract();
        sleep(2000);
    }
}
