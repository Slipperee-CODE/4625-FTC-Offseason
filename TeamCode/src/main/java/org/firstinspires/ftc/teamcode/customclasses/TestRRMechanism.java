package org.firstinspires.ftc.teamcode.customclasses;

import com.qualcomm.robotcore.hardware.DcMotor;

public class TestRRMechanism
{
    private DcMotor customMotor = null;

    public MotorState motorState = MotorState.OFF;
    public enum MotorState {
        ON,
        OFF,
    }


    public TestRRMechanism(DcMotor motor){ initialize(motor); }


    private void initialize(DcMotor passedMotor)
    {
        customMotor = passedMotor;

        customMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        customMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        customMotor.setPower(0);
    }


    public void Update()
    {
        switch(motorState)
        {
            case ON:
                customMotor.setPower(0.5);
                break;

            case OFF:
                customMotor.setPower(0);
                break;

            default:
                customMotor.setPower(0);
        }
    }
}
