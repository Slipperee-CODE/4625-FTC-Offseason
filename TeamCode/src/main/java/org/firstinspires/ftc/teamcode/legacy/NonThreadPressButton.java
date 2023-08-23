package org.firstinspires.ftc.teamcode.customclasses;


public class PressButton
{
    private boolean isClicked = false;


    public PressButton(){ initialize(); }

    private void initialize() { }


    public boolean Update(boolean buttonState)
    {
        if (buttonState && !isClicked)
        {
            isClicked = true;
        }
        else
        {
            isClicked = false;
        }

        return isClicked;
    }
}
