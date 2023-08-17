package org.firstinspires.ftc.teamcode.customclasses;

import com.qualcomm.robotcore.hardware.Gamepad;

public class CustomGamepad
{
    private Gamepad gamepad;


    public CustomGamepad(Gamepad passedGamepad){ initialize(passedGamepad); }

    private void initialize(Gamepad passedGamepad)
    {
        gamepad = passedGamepad;
    }


    public void Update()
    {

    }
}
