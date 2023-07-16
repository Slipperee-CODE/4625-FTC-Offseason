package org.firstinspires.ftc.teamcode.customclasses;


public class PressButton
{
    private boolean isPressed = false;
    private Runnable function;


    public PressButton(Runnable passedFunction){ initialize(passedFunction); }

    private void initialize(Runnable passedFunction)
    {
        function = passedFunction;
    }


    public void Update(boolean buttonState)
    {
        if (buttonState && !isPressed)
        {
            isPressed = true;
            function.run();
            return;
        }

        if (!buttonState && isPressed)
        {
            isPressed = false;
        }
    }
}
