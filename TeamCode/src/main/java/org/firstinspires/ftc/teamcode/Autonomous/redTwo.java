package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subSystems.WobbleArm;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(group = "drive")
public class redTwo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        WobbleArm wobbleArm = new WobbleArm(hardwareMap);

        drive.setPoseEstimate(new Pose2d(-60, -48, 0));
        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d(-60,-48, 0))
                .splineToConstantHeading(new Vector2d(6,-60), 0)
                .build();

        waitForStart();
        //test
        if (isStopRequested()) return;

        wobbleArm.armExtend();
        sleep(1000);
        wobbleArm.grabGrab();
        sleep(2000);
        //Trajectories should be initialized inside the run method so that they can be oontinued using the
        //Drive.getPoseEstimate() to reduce error.

        drive.followTrajectory(traj1);
        //release wobble

        wobbleArm.grabRelease();
        sleep(1000);
        wobbleArm.armRetract();
        sleep(2000);
    }
}
