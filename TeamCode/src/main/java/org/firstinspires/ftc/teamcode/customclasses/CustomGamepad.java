package org.firstinspires.ftc.teamcode.customclasses;

import com.qualcomm.robotcore.hardware.Gamepad;

public class CustomGamepad
{
    private Gamepad gamepad;


    public boolean aHold;
    public boolean aPress;

    public boolean bHold;
    public boolean bPress;

    public boolean xHold;
    public boolean xPress;

    public boolean yHold;
    public boolean yPress;

    public boolean upHold;
    public boolean upPress;

    public boolean downHold;
    public boolean downPress;

    public boolean backHold;
    public boolean backPress;

    public boolean guideHold;
    public boolean guidePress;

    public boolean startHold;
    public boolean startPress;

    public boolean rightBumperHold;
    public boolean rightBumperPress;

    public boolean leftBumperHold;
    public boolean leftBumperPress;

    public boolean rightStickHold;
    public boolean rightStickPress;

    public boolean leftStickHold;
    public boolean leftStickPress;


    private HoldButton ButtonObject_aHold = new HoldButton();
    private PressButton ButtonObject_aPress = new PressButton();

    private HoldButton ButtonObject_bHold = new HoldButton();
    private PressButton ButtonObject_bPress = new PressButton();

    private HoldButton ButtonObject_xHold = new HoldButton();
    private PressButton ButtonObject_xPress = new PressButton();

    private HoldButton ButtonObject_yHold = new HoldButton();
    private PressButton ButtonObject_yPress = new PressButton();

    private HoldButton ButtonObject_upHold = new HoldButton();
    private PressButton ButtonObject_upPress = new PressButton();

    private HoldButton ButtonObject_downHold = new HoldButton();
    private PressButton ButtonObject_downPress = new PressButton();

    private HoldButton ButtonObject_backHold = new HoldButton();
    private PressButton ButtonObject_backPress = new PressButton();

    private HoldButton ButtonObject_guideHold = new HoldButton();
    private PressButton ButtonObject_guidePress = new PressButton();

    private HoldButton ButtonObject_startHold = new HoldButton();
    private PressButton ButtonObject_startPress = new PressButton();

    private HoldButton ButtonObject_rightBumperHold = new HoldButton();
    private PressButton ButtonObject_rightBumperPress = new PressButton();

    private HoldButton ButtonObject_leftBumperHold = new HoldButton();
    private PressButton ButtonObject_leftBumperPress = new PressButton();

    private HoldButton ButtonObject_rightStickHold = new HoldButton();
    private PressButton ButtonObject_rightStickPress = new PressButton();

    private HoldButton ButtonObject_leftStickHold = new HoldButton();
    private PressButton ButtonObject_leftStickPress = new PressButton();


    public CustomGamepad(Gamepad passedGamepad){ initialize(passedGamepad); }

    private void initialize(Gamepad passedGamepad)
    {
        gamepad = passedGamepad;
    }


    public void Update()
    {
        aHold = ButtonObject_aHold.Update(gamepad.a);
        aPress = ButtonObject_aPress.Update(gamepad.a);

        bHold = ButtonObject_bHold.Update(gamepad.b);
        bPress = ButtonObject_bPress.Update(gamepad.b);

        xHold = ButtonObject_xHold.Update(gamepad.x);
        xPress = ButtonObject_xPress.Update(gamepad.x);

        yHold = ButtonObject_yHold.Update(gamepad.y);
        yPress = ButtonObject_yPress.Update(gamepad.y);

        upHold = ButtonObject_upHold.Update(gamepad.dpad_up);
        upPress = ButtonObject_upPress.Update(gamepad.dpad_up);

        downHold = ButtonObject_downHold.Update(gamepad.dpad_down);
        downPress = ButtonObject_downPress.Update(gamepad.dpad_down);

        backHold = ButtonObject_backHold.Update(gamepad.back);
        backPress = ButtonObject_backPress.Update(gamepad.back);

        guideHold = ButtonObject_guideHold.Update(gamepad.guide);
        guidePress = ButtonObject_guidePress.Update(gamepad.guide);

        startHold = ButtonObject_startHold.Update(gamepad.start);
        startPress = ButtonObject_startPress.Update(gamepad.start);

        rightBumperHold = ButtonObject_rightBumperHold.Update(gamepad.right_bumper);
        rightBumperPress = ButtonObject_rightBumperPress.Update(gamepad.right_bumper);

        leftBumperHold = ButtonObject_leftBumperHold.Update(gamepad.left_bumper);
        leftBumperPress = ButtonObject_leftBumperPress.Update(gamepad.left_bumper);

        rightStickHold = ButtonObject_rightStickHold.Update(gamepad.right_stick_button);
        rightStickPress = ButtonObject_rightStickPress.Update(gamepad.right_stick_button);

        leftStickHold = ButtonObject_leftStickHold.Update(gamepad.left_stick_button);
        leftStickPress = ButtonObject_leftStickPress.Update(gamepad.left_stick_button);
    }
}
