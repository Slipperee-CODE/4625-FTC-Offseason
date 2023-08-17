package org.firstinspires.ftc.teamcode.customclasses;

import com.qualcomm.robotcore.hardware.Gamepad;

public class CustomGamepad {
    public Gamepad gamepad; // has to be public because the Gamepad class is too complicated to fully wrap.
    public boolean x,y,a,b,up,down,left,right,xDown,yDown,aDown,bDown,upDown,downDown,leftDown,rightDown;
    private boolean px,py,pa,pb,pup,pdown,pleft,pright;

    public void Update() {
        //Rebinds the gamepads variables to our own use
        x     = gamepad.x;
        y     = gamepad.y;
        a     = gamepad.a;
        b     = gamepad.b;
        up    = gamepad.dpad_up;
        down  = gamepad.dpad_down;
        left  = gamepad.dpad_left;
        right = gamepad.dpad_right;

        xDown = x && !px;
        yDown = y && !py;
        aDown = a && !pa;
        bDown = b && !pb;
        upDown   = gamepad.dpad_up && !pup;
        downDown  = gamepad.dpad_down && !pdown;
        leftDown  = gamepad.dpad_left && !pleft;
        rightDown = gamepad.dpad_right && !pright;

        px = x;
        py = y;
        pa = a;
        pb = b;
        pup =up;
        pdown =down;
        pleft =left;
        pright =right;
    }


    public CustomGamepad(Gamepad passedGamepad){
        this.gamepad = passedGamepad;
    }


}
