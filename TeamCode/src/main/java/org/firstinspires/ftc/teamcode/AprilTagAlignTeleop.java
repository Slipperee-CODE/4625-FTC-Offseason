/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.customclasses.AprilTagWebcam;
import org.firstinspires.ftc.teamcode.customclasses.HoldButton;
import org.firstinspires.ftc.teamcode.customclasses.PressButton;
import org.firstinspires.ftc.teamcode.customclasses.Robot;
import org.firstinspires.ftc.teamcode.customclasses.Webcam;

import java.util.function.Function;


@TeleOp(name="AprilTagAlignTeleop", group="Iterative Opmode")


//@Disabled


public class AprilTagAlignTeleop extends OpMode
{
    private Robot robot = null;
    private Webcam webcam = null;
    private AprilTagWebcam aprilTagWebcam = null;

    private HoldButton a1 = null;
    private PressButton b1 = null;


    private double frontRightPower;
    private double backRightPower;
    private double frontLeftPower;
    private double backLeftPower;


    @Override
    public void init()
    {
        robot = new Robot(hardwareMap);
        robot.SetSpeedConstant( 0.5);

        webcam = new Webcam(hardwareMap);
        aprilTagWebcam = new AprilTagWebcam(webcam.camera, telemetry);


        //Button Setup
        Runnable AprilTagAlignLambda = this::AprilTagAlign;
        a1 = new HoldButton(AprilTagAlignLambda, gamepad1);

        Runnable TelemetryTestLambda = this::TelemetryTest;
        b1 = new PressButton(TelemetryTestLambda);
    }


    @Override
    public void init_loop()
    {

    }


    @Override
    public void start() { robot.runtime.reset(); }


    @Override
    public void loop()
    {
        //Movement
        DrivetrainMovement();

        //Button Presses
        a1.Update(gamepad1.a);
        b1.Update(gamepad1.b);

        //April Tags
        aprilTagWebcam.DetectTags();

        //Powering Drivetrain Motors to Account for Overriding
        PowerDrivetrainMotors();
    }


    @Override
    public void stop()
    {

    }


    private void DrivetrainMovement()
    {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = -gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        frontRightPower = (y - x + rx) / denominator;
        backRightPower = (y + x + rx) / denominator;
        frontLeftPower = (y + x - rx) / denominator;
        backLeftPower = (y - x - rx) / denominator;
    }


    private void AprilTagAlign()
    {
        telemetry.addLine("Hi! The A button is being held on gamepad1");
        telemetry.addData("Detected Tag Id", aprilTagWebcam.detectedTag.id);
        telemetry.update();


        //GOALS:
            //GET BEARING TO 0
            //GET RANGE TO A SET NUMBER
            //USE 2 PID CONTROLLERS?
            //PLAN PATHING


        frontRightPower = .1;
        backRightPower = .1;
        frontLeftPower = .1;
        backLeftPower = .1;
    }


    private void TelemetryTest()
    {
        telemetry.addLine("Hi! The B button was pressed on gamepad1");
        telemetry.update();
    }


    private void PowerDrivetrainMotors()
    {
        robot.rF.SetAdjustedPower(frontRightPower);
        robot.rB.SetAdjustedPower(backRightPower);
        robot.lF.SetAdjustedPower(frontLeftPower);
        robot.lB.SetAdjustedPower(backLeftPower);
    }
}
