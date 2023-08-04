package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.customclasses.Robot;
import org.firstinspires.ftc.teamcode.customclasses.TestRRMechanism;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import java.util.ArrayList;
import java.util.Arrays;

@Autonomous(name = "RoadRunnerTesting3")
public class RoadRunnerTesting3 extends LinearOpMode
{
    private SampleMecanumDrive drive;
    private Robot robot;

    private TestRRMechanism testRRMechanism;


    private int autoVersion;
    private AutoState autoState = AutoState.MAIN;
    private ArrayList<Trajectory> trajectoriesToFollow;
    private int trajectoryIndex = 0;

    private ArrayList<Trajectory> defaultTrajectories;


    private enum AutoState {
        MAIN,
        NEXT,
        IDLE,
    }


    @Override
    public void runOpMode()
    {
        OnInit();

        while (!isStarted() && !isStopRequested()) //INIT LOOP
        {
            InitLoop();
        }
        OnStart();

        while(!isStopRequested())
        {
            Loop();
        }
        OnStop();
    }


    private void OnInit()
    {
        drive = new SampleMecanumDrive(hardwareMap);
        robot = new Robot(hardwareMap);
        testRRMechanism = new TestRRMechanism(hardwareMap.get(DcMotor.class, "testMotor"));
        autoVersion = 0;

        defaultTrajectories = CreateDefaultTrajectories();
        //Create other case trajectories here
    }


    private void InitLoop()
    {
        autoVersion = AutoVersionUpdate();
    }


    private void OnStart()
    {
        switch (autoVersion)
        {
            case 1:
                //case 1
                //Follow case trajectories here

            case 2:
                //case 2
                //Follow case trajectories here

            case 3:
                //case 3
                //Follow case trajectories here

            default:
                trajectoriesToFollow = defaultTrajectories;

            drive.followTrajectoryAsync(trajectoriesToFollow.get(trajectoryIndex));
        }
    }


    private void Loop()
    {
        switch (autoState)
        {
            case MAIN:
                drive.update();
                testRRMechanism.Update();
                //Update any other mechanisms

            case NEXT:
                trajectoryIndex++;
                drive.followTrajectoryAsync(trajectoriesToFollow.get(trajectoryIndex));

            case IDLE:
                //Don't do anything because the main auto is over
        }
    }


    private void OnStop()
    {

    }


    private ArrayList<Trajectory> CreateDefaultTrajectories()
    {
        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));
        drive.setPoseEstimate(startPose);

        Trajectory test;
        Trajectory test2;

        test = drive.trajectoryBuilder(new Pose2d())
                .splineToSplineHeading(new Pose2d(10,10),Math.toRadians(180))
                .addDisplacementMarker(() -> {
                    //Run Robot Code to Start/Stop Systems Here Mid Trajectory
                    testRRMechanism.motorState = TestRRMechanism.MotorState.ON;
                })
                .splineToSplineHeading(new Pose2d(20,20),Math.toRadians(180))
                .addDisplacementMarker(() -> {
                    testRRMechanism.motorState = TestRRMechanism.MotorState.OFF;
                    autoState = AutoState.NEXT;
                })
                .build();

        test2 = drive.trajectoryBuilder(test.end())
                .splineToSplineHeading(new Pose2d(30,30),Math.toRadians(180))
                .addDisplacementMarker(() -> {
                    //Run Robot Code to Start/Stop Systems Here Mid Trajectory
                })
                .splineToSplineHeading(new Pose2d(0,0),Math.toRadians(180))
                .addDisplacementMarker(() -> {
                    autoState = AutoState.IDLE; //End Auto
                })
                .build();


        return new ArrayList<>(Arrays.asList(test,test2));
    }


    private int AutoVersionUpdate()
    {
        //Update the Auto Version Here (April Tags, Color Detection, etc.)

        return 0;
    }
}

