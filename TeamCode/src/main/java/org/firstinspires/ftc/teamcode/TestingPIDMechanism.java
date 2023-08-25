package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.customclasses.CustomGamepad;
import org.firstinspires.ftc.teamcode.customclasses.TestPIDMechanism;

@TeleOp(name="TestingPIDMechanism", group="Iterative Opmode")

public class TestingPIDMechanism extends OpMode
{
    private TestPIDMechanism testPIDMechanism = null;

    private CustomGamepad customGamepad1;

    @Override
    public void init()
    {
        testPIDMechanism = new TestPIDMechanism(hardwareMap);
        customGamepad1 = new CustomGamepad(this, 1);
    }

    @Override
    public void loop()
    {
        if (customGamepad1.upDown)
        {
            testPIDMechanism.motorState = TestPIDMechanism.MotorState.EXTENDED;
        }

        if (customGamepad1.downDown)
        {
            testPIDMechanism.motorState = TestPIDMechanism.MotorState.RETRACTED;
        }

        testPIDMechanism.Update(telemetry);
    }
}
