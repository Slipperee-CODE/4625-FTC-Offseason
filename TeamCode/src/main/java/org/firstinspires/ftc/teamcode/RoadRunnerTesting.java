package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.customclasses.Robot;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "RoadRunnerTesting")
public class RoadRunnerTesting extends LinearOpMode
{
    @Override
    public void runOpMode()
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Robot robot = new Robot(hardwareMap);


        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));
        drive.setPoseEstimate(startPose);


        Trajectory test = drive.trajectoryBuilder(new Pose2d())
                .splineToSplineHeading(new Pose2d(10,10),Math.toRadians(180))
                .addDisplacementMarker(() -> {
                    //Run Robot Code to Start/Stop Systems Here
                })
                .splineToSplineHeading(new Pose2d(20,20),Math.toRadians(180))
                .build();

        Trajectory test2 = drive.trajectoryBuilder(test.end())
                .splineToSplineHeading(new Pose2d(30,30),Math.toRadians(180))
                .addDisplacementMarker(() -> {
                    //Run Robot Code to Start/Stop Systems Here
                })
                .splineToSplineHeading(new Pose2d(0,0),Math.toRadians(180))
                .build();


        drive.followTrajectory(test);
        drive.followTrajectory(test2);
    }



}

