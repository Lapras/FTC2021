package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.quickStartDrive.SampleMecanumDrive;

@Autonomous(group = "drive")
public class redTwo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d(-60,-48, 0))
                .splineToConstantHeading(new Vector2d(6,-60), 0)
                .build();

        waitForStart();
        //test
        if (isStopRequested()) return;

        //Trajectories should be initialized inside the run method so that they can be oontinued using the
        //Drive.getPoseEstimate() to reduce error.
        drive.setPoseEstimate(new Pose2d(-60, -48, 0));

        drive.followTrajectory(traj1);
        //release wobble
    }
}
