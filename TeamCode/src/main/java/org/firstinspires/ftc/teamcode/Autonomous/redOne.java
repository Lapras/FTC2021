package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Pose2dKt;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.quickStartDrive.SampleMecanumDrive;

import java.util.Vector;

@Autonomous(group = "drive")
public class redOne extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(new Pose2d(-60, -24, 0));
        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d(-60,-24, 0))
                .splineToConstantHeading(new Vector2d(-24,-12), 0)
                .splineToConstantHeading(new Vector2d(38, -38), 0)
                .build();
        //firstTrajectory from starting point, around disks,
        //release wobble before second trajecto
        //Trajectories should be initialized before calling code to reduce delay

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
                .lineTo(new Vector2d(12, -12))
                .build();
        //second trajectory to succesfully navigate
        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(traj1);

        drive.followTrajectory(traj2);
    }
}
