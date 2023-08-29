package org.firstinspires.ftc.teamcode.customclasses;

import android.sax.StartElementListener;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestPIDMechanism
{
    public PIDMotor motor = null;

    public MotorState motorState = MotorState.IDLE;
    public enum MotorState {
        EXTENDED,
        RETRACTED,
        IDLE
    }


    public TestPIDMechanism(HardwareMap hardwareMap)
    {
        initialize(hardwareMap);
    }


    private void initialize(HardwareMap hardwareMap)
    {
        motor = new PIDMotor(hardwareMap.get(DcMotor.class,"testMotor"),1,0,0);
    }


    public void Update(Telemetry telemetry)
    {
        switch(motorState)
        {
            case EXTENDED:
                motor.setTarget(1000);
                motorState = MotorState.IDLE;
                break;

            case RETRACTED:
                motor.setTarget(-1000);
                motorState = MotorState.IDLE;
                break;

            case IDLE:
                //Do nothing just so the program isn't constantly setting the target, we can talk about whether or not this is necessary - Cai
                break;

            default:
                motorState = MotorState.IDLE;
        }

        motor.Update(telemetry);
    }
}
