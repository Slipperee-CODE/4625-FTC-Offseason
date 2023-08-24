package org.firstinspires.ftc.teamcode.customclasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.zip.ZipInputStream;

public class PIDMotor {
    private final DcMotor motor;
    private final double p, i, d;
    private int errorSum;
    private double lastError = Double.NaN;
    private int target;

    public PIDMotor(DcMotor motor,double p, double i, double d)
    {
        this.motor = motor;
        motor.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
        this.p = p; this.i = i; this.d = d;

    }
    public void setTarget(int target) { this.target = target; }
    public int getTarget() {return target;}
    public int getPos() {return motor.getCurrentPosition();}
    public void ResetPID()
    {
        // Will reset
        target = 0;
        errorSum = 0;

        motor.setMode(RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(RunMode.RUN_WITHOUT_ENCODER);
    }

    public double[] getPID() {
        return new double[]{p,i,d};
    }

    public int getError()
    {
        final int error= target - motor.getCurrentPosition();
        if (Double.isNaN(lastError)) {
            lastError = error;
        }
        return error;
    }
    public void Update() {
        Update(null);
    }

    public void Update(Telemetry telemetry)
    {
        final double pOutput;
        final double iOutput;
        final double dOutput;

        int error = getError();

        pOutput = p * error;

        //Must be negative to "slow" down the effects of a large spike
        dOutput = -d * (error - lastError);// Theoretically we multiply by the deltaTime but its not necessary
        lastError = error;

        errorSum += error; // Theoretically we multiply by the deltaTime but its not necessary

        iOutput = i * errorSum;

        final double output = pOutput + iOutput + dOutput;

        motor.setPower(output);
        if (telemetry != null) {
            telemetry.addLine("P: " + pOutput + "  I: " + iOutput + " D: " + dOutput);
        }
    }

}
