package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.quickStartDrive.SampleMecanumDrive;

import java.util.Vector;

@Autonomous(group = "drive")
public class redThree extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        //Trajectories should be initialized inside the run method so that they can be oontinued using the
        //Drive.getPoseEstimate() to reduce error.
       /* drive.setPoseEstimate(new Pose2d(-60, -48, 0));
        Trajectory traj1 = drive.trajectoryBuilder(drive.getPoseEstimate())
                .splineTo(new Vector2d(12,-60), 0)
                .build();

        drive.followTrajectory(traj1);*/
        //release wobble
        drive.setPoseEstimate(new Pose2d(12, -60, 0));
        drive.turn(Math.toRadians(90));
        Trajectory traj2 = drive.trajectoryBuilder(drive.getPoseEstimate(), 90)
                .splineTo(new Vector2d(-12, -24), 180)
                //.splineTo(new Vector2d(-36, -24), 180)
                .build();

        drive.followTrajectory(traj2);
        //grab second wobble
      /*  Trajectory traj3 = drive.trajectoryBuilder(drive.getPoseEstimate(), 0)
                .splineToSplineHeading(new Pose2d(12, -48, 270), 0)
                .build();

        drive.followTrajectory(traj3);*/
    }
}
