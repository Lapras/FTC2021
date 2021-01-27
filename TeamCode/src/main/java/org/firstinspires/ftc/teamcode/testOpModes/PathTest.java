package org.firstinspires.ftc.teamcode.testOpModes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Pose2dKt;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.quickStartDrive.SampleMecanumDrive;

import java.util.Vector;

@Autonomous(group = "drive")
public class PathTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        //TODO: maybe initialize all trajectories before watiForStart()? Until we start using variables in trajectories
        Trajectory traj = drive.trajectoryBuilder(new Pose2d(-24,-24, 0))
                .splineToConstantHeading(new Vector2d(0,0), 90)
                .splineToSplineHeading(new Pose2d(-24,24, 180), 180)
                .splineToSplineHeading(new Pose2d(-48, 0, 270), 270)
                .splineTo(new Vector2d(-24, -24), 0)

                .build();

        drive.followTrajectory(traj);
    }
    //this class is just a test, something that will simply initialize
    //the roadrunner stuff and let it being tested with different trajectories
}
