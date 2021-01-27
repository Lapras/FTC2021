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

        waitForStart();

        if (isStopRequested()) return;
        //Trajectories should be initialized inside the run method so that they can be oontinued using the
        //Drive.getPoseEstimate() to reduce error.
        drive.setPoseEstimate(new Pose2d(-60, -24, 0));
        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d(-60,-24, 0))
                .splineTo(new Vector2d(-24,-12), 0)
                .splineTo(new Vector2d(38, -38), 0)
                .build();

        drive.followTrajectory(traj1);
        //firstTrajectory from starting point, around disks,
        //release wobble before second trajectory
        Trajectory traj2 = drive.trajectoryBuilder(drive.getPoseEstimate(), 180)
                .splineToConstantHeading(new Vector2d(12, -12), 90)
                .build();

        drive.followTrajectory(traj2);
        //second trajectory to succesfully navigate
    }
}
