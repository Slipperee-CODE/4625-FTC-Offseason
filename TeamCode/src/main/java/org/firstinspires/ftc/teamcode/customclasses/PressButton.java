package org.firstinspires.ftc.teamcode.customclasses;


public class PressButton
{
    private boolean isPressed = false;


    public PressButton(){ initialize(); }

    private void initialize() { }


    public boolean Update(boolean buttonState)
    {
        if (buttonState && !isPressed)
        {
            isPressed = true;
        }
        else
        {
            isPressed = false;
        }

        return isPressed;
    }
}
