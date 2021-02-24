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
                .splineTo(new Vector2d(12,-60), 0)
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end(), 90)
                .splineToSplineHeading(new Pose2d(-12, -24, Math.toRadians(90)), Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-36, -24, Math.toRadians(180)), Math.toRadians(180))
                .build();

        Trajectory traj3 = drive.trajectoryBuilder(traj2.end(), 0)
                .splineToSplineHeading(new Pose2d(12, -42, Math.toRadians(270)), 0)
                .build();
        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(traj1);
        //release wobble

        drive.followTrajectory(traj2);
        //grab second wobble

        drive.followTrajectory(traj3);
    }
}
