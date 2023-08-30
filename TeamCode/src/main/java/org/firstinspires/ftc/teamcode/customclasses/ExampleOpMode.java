package org.firstinspires.ftc.teamcode.customclasses;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.RoadRunnerTesting3;

import java.util.ArrayList;
import java.util.Arrays;

public class ExampleOpMode extends CustomOpMode{
    private TestRRMechanism testRRMechanism;
    private int trajectoryIndex;
    private ArrayList<Trajectory> trajectoriesToFollow;
    public void init() {

        super.init();
        testRRMechanism = new TestRRMechanism(hardwareMap);
        trajectoriesToFollow = CreateDefaultTrajectories();

    }
    protected boolean handleState(AutoState state) {
        return true;
    }
    protected void onMainLoop() {
        drive.update();
        testRRMechanism.Update();
        telemetry.addLine("MainRunning");
        telemetry.addData("Mechanism isBusy -> ", testRRMechanism.customMotor.isBusy());
        telemetry.addData("Mechanism isOn -> ", testRRMechanism.motorState);
        telemetry.update();
        //Update any other mechanisms
    }
    protected void onNextLoop() {
        trajectoryIndex++;
        drive.followTrajectoryAsync(trajectoriesToFollow.get(trajectoryIndex));
        drive.update();
        autonomousState = AutoState.MAIN;
    }
    protected void onStopLoop() {
        super.onStopLoop();
        autonomousState = AutoState.IDLE;
    }
    protected void onIdleLoop() {

    }
    private ArrayList<Trajectory> CreateDefaultTrajectories()
    {
        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));
        drive.setPoseEstimate(startPose);

        Trajectory test;
        Trajectory test2;

        test = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(10,10),Math.toRadians(0))
                .addDisplacementMarker(() -> {
                    //Run Robot Code to Start/Stop Systems Here Mid Trajectory
                    testRRMechanism.motorState = TestRRMechanism.MotorState.ON;
                })
                .splineTo(new Vector2d(20,20),Math.toRadians(0))
                .addDisplacementMarker(() -> {
                    testRRMechanism.motorState = TestRRMechanism.MotorState.OFF;
                    autonomousState = AutoState.NEXT;
                })
                .build();

        test2 = drive.trajectoryBuilder(test.end())
                .splineTo(new Vector2d(30,30),Math.toRadians(0))
                .addDisplacementMarker(() -> {
                    //Run Robot Code to Start/Stop Systems Here Mid Trajectory
                })
                .splineTo(new Vector2d(0,0),Math.toRadians(0))
                .addDisplacementMarker(() -> {
                    drive.setMotorPowers(0,0,0,0); //IS THIS NECESARRY IF IT APPEARS IN THE "onStopLoop" function
                    autonomousState = AutoState.STOP; //End Auto
                })
                .build();


        return new ArrayList<>(Arrays.asList(test,test2));
    }

}
