package com.example.bluetoothpresentationtool;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothSocket;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;

public class Control extends AppCompatActivity {

    static float xInit = 0, yInit = 0, yScrollInit = 0, xScrollInit = 0;
    RelativeLayout presentetionlayout, mouselayout;
    LinearLayout keyboardlayout, keyboardlayout1;
    ImageView touchpad, scrollpad, bigtouchpad, smallscrollpad;
    Button btstart, btexit, btblack, btwhite, btarrow, btlaserp, btpen, bteraser, bttleft, bttright, btleftmouse, btrightmouse, btnext, btprivious;
    ImageButton btkeyboard, btkeyboard1, btmouse, btpresentetion;
    BluetoothSocket Blsocket = DataHolder.getData();
    IODataStream ioDataStream = new IODataStream(Blsocket);
    long downtime, uptime, Sdowntime;
    boolean LeftmousePress = false, LeftmouseClick = false, leftmouseIsPress = false, isTwoPointer = false;
    boolean capslockisOn = false;

    //define keyboard buttons

    Button  btPageUp, btHome, btEsc, btRshift, btRCtrl,
            btLWin, btPageDown, btEnd, btRAlt,
            btF1, btF2, btF3, btF4, btF5, btF6, btF7, btF8, btF9, btF10, btF11, btF12, btInsert, btTab,
            btGraveAccent, btHyphen, btEquals, btLSquareBracket, btRSquareBracket, btBackslash, btSemicolon, btApostrophe, btSlash,
            bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0,
            btQ, btW, btE, btR, btT, btY, btU, btI, btO, btP,
            btA, btS, btD, btF, btG, btH, btJ, btK, btL,
            btClock, btZ, btX, btC, btV, btB, btN, btM, btBSpace,
            btLShift, btComma, btSpace, btDot, btEnter;

    private View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View v) {
            final int id = v.getId();
            switch (id) {
                case R.id.btLWindows:
                    ioDataStream.write(csv('0', '1', keymodifiers.KEY_LEFT_GUI).getBytes());
                    ioDataStream.write(csv('0', '0', keymodifiers.KEY_LEFT_GUI).getBytes());
                    break;
                case R.id.btPageUp:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_PAGE_UP).getBytes());
                    break;
                case R.id.btHome:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_HOME).getBytes());
                    break;
                case R.id.btEsc:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_ESC).getBytes());
                    break;
                case R.id.btPageDown:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_PAGE_DOWN).getBytes());
                    break;
                case R.id.btEnd:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_END).getBytes());
                    break;
                case R.id.btF8:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_F8).getBytes());
                    break;
                case R.id.btF9:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_F9).getBytes());
                    break;
                case R.id.btF10:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_F10).getBytes());
                    break;
                case R.id.btF11:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_F11).getBytes());
                    break;
                case R.id.btF12:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_F12).getBytes());
                    break;
                case R.id.btInsert:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_INSERT).getBytes());
                    break;
                case R.id.btTab:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_TAB).getBytes());
                    break;
                case R.id.btF1:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_F1).getBytes());
                    break;
                case R.id.btF2:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_F2).getBytes());
                    break;
                case R.id.btF3:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_F3).getBytes());
                    break;
                case R.id.btF4:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_F4).getBytes());
                    break;
                case R.id.btF5:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_F5).getBytes());
                    break;
                case R.id.btF6:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_F6).getBytes());
                    break;
                case R.id.btF7:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_F7).getBytes());
                    break;
                case R.id.btGraveAccent:
                    ioDataStream.write(csv('0', '2', '`').getBytes());
                    break;
                case R.id.btHyphen:
                    ioDataStream.write(csv('0', '2', '-').getBytes());
                    break;
                case R.id.btEquals:
                    ioDataStream.write(csv('0', '2', '=').getBytes());
                    break;
                case R.id.btLSquareBracket:
                    ioDataStream.write(csv('0', '2', '[').getBytes());
                    break;
                case R.id.btRSquareBracket:
                    ioDataStream.write(csv('0', '2', ']').getBytes());
                    break;
                case R.id.btBackslash:
                    ioDataStream.write(csv('0', '2', '\\').getBytes());
                    break;
                case R.id.btSemicolon:
                    ioDataStream.write(csv('0', '2', ';').getBytes());
                    break;
                case R.id.btApostrophe:
                    ioDataStream.write(csv('0', '2', '\'').getBytes());
                    break;
                case R.id.btSlash:
                    ioDataStream.write(csv('0', '2', '/').getBytes());
                    break;
                case R.id.bt1:
                    ioDataStream.write(csv('0', '2', '1').getBytes());
                    break;
                case R.id.bt2:
                    ioDataStream.write(csv('0', '2', '2').getBytes());
                    break;
                case R.id.bt3:
                    ioDataStream.write(csv('0', '2', '3').getBytes());
                    break;
                case R.id.bt4:
                    ioDataStream.write(csv('0', '2', '4').getBytes());
                    break;
                case R.id.bt5:
                    ioDataStream.write(csv('0', '2', '5').getBytes());
                    break;
                case R.id.bt6:
                    ioDataStream.write(csv('0', '2', '6').getBytes());
                    break;
                case R.id.bt7:
                    ioDataStream.write(csv('0', '2', '7').getBytes());
                    break;
                case R.id.bt8:
                    ioDataStream.write(csv('0', '2', '8').getBytes());
                    break;
                case R.id.bt9:
                    ioDataStream.write(csv('0', '2', '9').getBytes());
                    break;
                case R.id.bt0:
                    ioDataStream.write(csv('0', '2', '0').getBytes());
                    break;
                case R.id.btQ:
                    ioDataStream.write(csv('0', '2', 'q').getBytes());
                    break;
                case R.id.btW:
                    ioDataStream.write(csv('0', '2', 'w').getBytes());
                    break;
                case R.id.btE:
                    ioDataStream.write(csv('0', '2', 'e').getBytes());
                    break;
                case R.id.btR:
                    ioDataStream.write(csv('0', '2', 'r').getBytes());
                    break;
                case R.id.btT:
                    ioDataStream.write(csv('0', '2', 't').getBytes());
                    break;
                case R.id.btY:
                    ioDataStream.write(csv('0', '2', 'y').getBytes());
                    break;
                case R.id.btU:
                    ioDataStream.write(csv('0', '2', 'u').getBytes());
                    break;
                case R.id.btI:
                    ioDataStream.write(csv('0', '2', 'i').getBytes());
                    break;
                case R.id.btO:
                    ioDataStream.write(csv('0', '2', 'o').getBytes());
                    break;
                case R.id.btP:
                    ioDataStream.write(csv('0', '2', 'p').getBytes());
                    break;
                case R.id.btA:
                    ioDataStream.write(csv('0', '2', 'a').getBytes());
                    break;
                case R.id.btS:
                    ioDataStream.write(csv('0', '2', 's').getBytes());
                    break;
                case R.id.btD:
                    ioDataStream.write(csv('0', '2', 'd').getBytes());
                    break;
                case R.id.btF:
                    ioDataStream.write(csv('0', '2', 'f').getBytes());
                    break;
                case R.id.btG:
                    ioDataStream.write(csv('0', '2', 'g').getBytes());
                    break;
                case R.id.btH:
                    ioDataStream.write(csv('0', '2', 'h').getBytes());
                    break;
                case R.id.btJ:
                    ioDataStream.write(csv('0', '2', 'j').getBytes());
                    break;
                case R.id.btK:
                    ioDataStream.write(csv('0', '2', 'k').getBytes());
                    break;
                case R.id.btL:
                    ioDataStream.write(csv('0', '2', 'l').getBytes());
                    break;
                case R.id.btZ:
                    ioDataStream.write(csv('0', '2', 'z').getBytes());
                    break;
                case R.id.btX:
                    ioDataStream.write(csv('0', '2', 'x').getBytes());
                    break;
                case R.id.btC:
                    ioDataStream.write(csv('0', '2', 'c').getBytes());
                    break;
                case R.id.btV:
                    ioDataStream.write(csv('0', '2', 'v').getBytes());
                    break;
                case R.id.btB:
                    ioDataStream.write(csv('0', '2', 'b').getBytes());
                    break;
                case R.id.btN:
                    ioDataStream.write(csv('0', '2', 'n').getBytes());
                    break;
                case R.id.btM:
                    ioDataStream.write(csv('0', '2', 'm').getBytes());
                    break;
                case R.id.btCAPSLOCK:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_CAPS_LOCK).getBytes());
                    if (!capslockisOn) {
                        btClock.setBackgroundColor(0xff00ff00);
                        capslockisOn = !capslockisOn;
                    } else {
                        btClock.setBackgroundResource(android.R.drawable.btn_default);
                        capslockisOn = !capslockisOn;
                    }
                    break;
                case R.id.btBACKSPACE:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_BACKSPACE).getBytes());
                    break;
                case R.id.btCOMMA:
                    ioDataStream.write(csv('0', '2', ',').getBytes());
                    break;
                case R.id.btSPACE:
                    ioDataStream.write(csv('0', '2', ' ').getBytes());
                    break;
                case R.id.btdot:
                    ioDataStream.write(csv('0', '2', '.').getBytes());
                    break;
                case R.id.btENTER:
                    ioDataStream.write(csv('0', '2', keymodifiers.KEY_RETURN).getBytes());
                    break;
            }
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        presentetionlayout = findViewById(R.id.presentationlayout);
        keyboardlayout = findViewById(R.id.keyboardlayout);
        keyboardlayout1 = findViewById(R.id.keyboardlayout1);
        mouselayout = findViewById(R.id.mouselayout);

        btkeyboard = findViewById(R.id.keyboard);
        btkeyboard1 = findViewById(R.id.keyboard1);
        btmouse = findViewById(R.id.mouse);
        btpresentetion = findViewById(R.id.presentation);

        btstart = findViewById(R.id.start);
        btexit = findViewById(R.id.esc);
        btblack = findViewById(R.id.black);
        btwhite = findViewById(R.id.white);
        btarrow = findViewById(R.id.arrow);
        btlaserp = findViewById(R.id.laser);
        btpen = findViewById(R.id.pen);
        bteraser = findViewById(R.id.eraser);
        bttleft = findViewById(R.id.left);
        bttright = findViewById(R.id.right);
        btleftmouse = findViewById(R.id.bigleftmouse);
        btrightmouse = findViewById(R.id.bigrightmouse);
        btnext = findViewById(R.id.next);
        btprivious = findViewById(R.id.previous);

        touchpad = findViewById(R.id.touchpad);
        scrollpad = findViewById(R.id.scrollpad);
        bigtouchpad = findViewById(R.id.bigtouchpad);
        smallscrollpad = findViewById(R.id.smallscrollpad);

        btkeyboard.setBackgroundResource(android.R.drawable.btn_default);
        btkeyboard1.setBackgroundResource(android.R.drawable.btn_default);
        btmouse.setBackgroundResource(android.R.drawable.btn_default);
        btpresentetion.setBackgroundResource(android.R.drawable.btn_default);

        btstart.setBackgroundResource(android.R.drawable.btn_default);
        btexit.setBackgroundResource(android.R.drawable.btn_default);
        btblack.setBackgroundResource(android.R.drawable.btn_default);
        btwhite.setBackgroundResource(android.R.drawable.btn_default);
        btarrow.setBackgroundResource(android.R.drawable.btn_default);
        btlaserp.setBackgroundResource(android.R.drawable.btn_default);
        btpen.setBackgroundResource(android.R.drawable.btn_default);
        bteraser.setBackgroundResource(android.R.drawable.btn_default);
        bttleft.setBackgroundResource(android.R.drawable.btn_default);
        bttright.setBackgroundResource(android.R.drawable.btn_default);
        btleftmouse.setBackgroundResource(android.R.drawable.btn_default);
        btrightmouse.setBackgroundResource(android.R.drawable.btn_default);
        btnext.setBackgroundResource(android.R.drawable.btn_default);
        btprivious.setBackgroundResource(android.R.drawable.btn_default);

        touchpad.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
        scrollpad.setBackgroundResource(android.R.drawable.dialog_holo_dark_frame);
        bigtouchpad.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
        smallscrollpad.setBackgroundResource(android.R.drawable.dialog_holo_dark_frame);

        //Define keyboard buttons
        btPageUp = findViewById(R.id.btPageUp);
        btHome = findViewById(R.id.btHome);
        btEsc = findViewById(R.id.btEsc);
        btRshift = findViewById(R.id.btRshift);
        btRCtrl = findViewById(R.id.btRCtrl);
        btLWin = findViewById(R.id.btLWindows);
        btPageDown = findViewById(R.id.btPageDown);
        btEnd = findViewById(R.id.btEnd);
        btRAlt = findViewById(R.id.btRAlt);

        btF1 = findViewById(R.id.btF1);
        btF2 = findViewById(R.id.btF2);
        btF3 = findViewById(R.id.btF3);
        btF4 = findViewById(R.id.btF4);
        btF5 = findViewById(R.id.btF5);
        btF6 = findViewById(R.id.btF6);
        btF7 = findViewById(R.id.btF7);
        btF8 = findViewById(R.id.btF8);
        btF9 = findViewById(R.id.btF9);
        btF10 = findViewById(R.id.btF10);
        btF11 = findViewById(R.id.btF11);
        btF12 = findViewById(R.id.btF12);
        btInsert = findViewById(R.id.btInsert);
        btTab = findViewById(R.id.btTab);

        btGraveAccent = findViewById(R.id.btGraveAccent);
        btHyphen = findViewById(R.id.btHyphen);
        btEquals = findViewById(R.id.btEquals);
        btLSquareBracket = findViewById(R.id.btLSquareBracket);
        btRSquareBracket = findViewById(R.id.btRSquareBracket);
        btBackslash = findViewById(R.id.btBackslash);
        btSemicolon = findViewById(R.id.btSemicolon);
        btApostrophe = findViewById(R.id.btApostrophe);
        btSlash = findViewById(R.id.btSlash);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);
        bt8 = findViewById(R.id.bt8);
        bt9 = findViewById(R.id.bt9);
        bt0 = findViewById(R.id.bt0);

        btQ = findViewById(R.id.btQ);
        btW = findViewById(R.id.btW);
        btE = findViewById(R.id.btE);
        btR = findViewById(R.id.btR);
        btT = findViewById(R.id.btT);
        btY = findViewById(R.id.btY);
        btU = findViewById(R.id.btU);
        btI = findViewById(R.id.btI);
        btO = findViewById(R.id.btO);
        btP = findViewById(R.id.btP);

        btA = findViewById(R.id.btA);
        btS = findViewById(R.id.btS);
        btD = findViewById(R.id.btD);
        btF = findViewById(R.id.btF);
        btG = findViewById(R.id.btG);
        btH = findViewById(R.id.btH);
        btJ = findViewById(R.id.btJ);
        btK = findViewById(R.id.btK);
        btL = findViewById(R.id.btL);

        btClock = findViewById(R.id.btCAPSLOCK);
        btZ = findViewById(R.id.btZ);
        btX = findViewById(R.id.btX);
        btC = findViewById(R.id.btC);
        btV = findViewById(R.id.btV);
        btB = findViewById(R.id.btB);
        btN = findViewById(R.id.btN);
        btM = findViewById(R.id.btM);
        btBSpace = findViewById(R.id.btBACKSPACE);

        btLShift = findViewById(R.id.btLshift);
        btComma = findViewById(R.id.btCOMMA);
        btSpace = findViewById(R.id.btSPACE);
        btDot = findViewById(R.id.btdot);
        btEnter = findViewById(R.id.btENTER);

        btPageUp.setBackgroundResource(android.R.drawable.btn_default);
        btHome.setBackgroundResource(android.R.drawable.btn_default);
        btEsc.setBackgroundResource(android.R.drawable.btn_default);
        btRshift.setBackgroundResource(android.R.drawable.btn_default);
        btRCtrl.setBackgroundResource(android.R.drawable.btn_default);
        btPageDown.setBackgroundResource(android.R.drawable.btn_default);
        btEnd.setBackgroundResource(android.R.drawable.btn_default);
        btRAlt.setBackgroundResource(android.R.drawable.btn_default);
        btLWin.setBackgroundResource(android.R.drawable.btn_default);

        btF1.setBackgroundResource(android.R.drawable.btn_default);
        btF2.setBackgroundResource(android.R.drawable.btn_default);
        btF3.setBackgroundResource(android.R.drawable.btn_default);
        btF4.setBackgroundResource(android.R.drawable.btn_default);
        btF5.setBackgroundResource(android.R.drawable.btn_default);
        btF6.setBackgroundResource(android.R.drawable.btn_default);
        btF7.setBackgroundResource(android.R.drawable.btn_default);
        btF8.setBackgroundResource(android.R.drawable.btn_default);
        btF9.setBackgroundResource(android.R.drawable.btn_default);
        btF10.setBackgroundResource(android.R.drawable.btn_default);
        btF11.setBackgroundResource(android.R.drawable.btn_default);
        btF12.setBackgroundResource(android.R.drawable.btn_default);
        btInsert.setBackgroundResource(android.R.drawable.btn_default);
        btTab.setBackgroundResource(android.R.drawable.btn_default);

        btGraveAccent.setBackgroundResource(android.R.drawable.btn_default);
        btHyphen.setBackgroundResource(android.R.drawable.btn_default);
        btEquals.setBackgroundResource(android.R.drawable.btn_default);
        btLSquareBracket.setBackgroundResource(android.R.drawable.btn_default);
        btRSquareBracket.setBackgroundResource(android.R.drawable.btn_default);
        btBackslash.setBackgroundResource(android.R.drawable.btn_default);
        btSemicolon.setBackgroundResource(android.R.drawable.btn_default);
        btApostrophe.setBackgroundResource(android.R.drawable.btn_default);
        btSlash.setBackgroundResource(android.R.drawable.btn_default);

        bt1.setBackgroundResource(android.R.drawable.btn_default);
        bt2.setBackgroundResource(android.R.drawable.btn_default);
        bt3.setBackgroundResource(android.R.drawable.btn_default);
        bt4.setBackgroundResource(android.R.drawable.btn_default);
        bt5.setBackgroundResource(android.R.drawable.btn_default);
        bt6.setBackgroundResource(android.R.drawable.btn_default);
        bt7.setBackgroundResource(android.R.drawable.btn_default);
        bt8.setBackgroundResource(android.R.drawable.btn_default);
        bt9.setBackgroundResource(android.R.drawable.btn_default);
        bt0.setBackgroundResource(android.R.drawable.btn_default);

        btQ.setBackgroundResource(android.R.drawable.btn_default);
        btW.setBackgroundResource(android.R.drawable.btn_default);
        btE.setBackgroundResource(android.R.drawable.btn_default);
        btR.setBackgroundResource(android.R.drawable.btn_default);
        btT.setBackgroundResource(android.R.drawable.btn_default);
        btY.setBackgroundResource(android.R.drawable.btn_default);
        btU.setBackgroundResource(android.R.drawable.btn_default);
        btI.setBackgroundResource(android.R.drawable.btn_default);
        btO.setBackgroundResource(android.R.drawable.btn_default);
        btP.setBackgroundResource(android.R.drawable.btn_default);

        btA.setBackgroundResource(android.R.drawable.btn_default);
        btS.setBackgroundResource(android.R.drawable.btn_default);
        btD.setBackgroundResource(android.R.drawable.btn_default);
        btF.setBackgroundResource(android.R.drawable.btn_default);
        btG.setBackgroundResource(android.R.drawable.btn_default);
        btH.setBackgroundResource(android.R.drawable.btn_default);
        btJ.setBackgroundResource(android.R.drawable.btn_default);
        btK.setBackgroundResource(android.R.drawable.btn_default);
        btL.setBackgroundResource(android.R.drawable.btn_default);

        btClock.setBackgroundResource(android.R.drawable.btn_default);
        btZ.setBackgroundResource(android.R.drawable.btn_default);
        btX.setBackgroundResource(android.R.drawable.btn_default);
        btC.setBackgroundResource(android.R.drawable.btn_default);
        btV.setBackgroundResource(android.R.drawable.btn_default);
        btB.setBackgroundResource(android.R.drawable.btn_default);
        btN.setBackgroundResource(android.R.drawable.btn_default);
        btM.setBackgroundResource(android.R.drawable.btn_default);
        btBSpace.setBackgroundResource(android.R.drawable.btn_default);

        btLShift.setBackgroundResource(android.R.drawable.btn_default);
        btComma.setBackgroundResource(android.R.drawable.btn_default);
        btSpace.setBackgroundResource(android.R.drawable.btn_default);
        btDot.setBackgroundResource(android.R.drawable.btn_default);
        btEnter.setBackgroundResource(android.R.drawable.btn_default);

        /* Define 3 button to change ui layout: presentation, keyboard, mouse */

        btpresentetion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mouselayout.getVisibility() == View.VISIBLE) {
                    mouselayout.setVisibility(View.GONE);
                }
                if (keyboardlayout.getVisibility() == View.VISIBLE) {
                    keyboardlayout.setVisibility(View.GONE);
                }
                if (keyboardlayout1.getVisibility() == View.VISIBLE) {
                    keyboardlayout1.setVisibility(View.GONE);
                }
                if (presentetionlayout.getVisibility() != View.VISIBLE) {
                    presentetionlayout.setVisibility(View.VISIBLE);
                }
                xInit = 0;
                yInit = 0;
                yScrollInit = 0;
            }
        });

        btkeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mouselayout.getVisibility() == View.VISIBLE) {
                    mouselayout.setVisibility(View.GONE);
                }
                if (presentetionlayout.getVisibility() == View.VISIBLE) {
                    presentetionlayout.setVisibility(View.GONE);
                }
                if (keyboardlayout1.getVisibility() == View.VISIBLE) {
                    keyboardlayout1.setVisibility(View.GONE);
                }
                if (keyboardlayout.getVisibility() != View.VISIBLE) {
                    keyboardlayout.setVisibility(View.VISIBLE);
                }
            }
        });

        btkeyboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mouselayout.getVisibility() == View.VISIBLE) {
                    mouselayout.setVisibility(View.GONE);
                }
                if (presentetionlayout.getVisibility() == View.VISIBLE) {
                    presentetionlayout.setVisibility(View.GONE);
                }
                if (keyboardlayout.getVisibility() == View.VISIBLE) {
                    keyboardlayout.setVisibility(View.GONE);
                }
                if (keyboardlayout1.getVisibility() != View.VISIBLE) {
                    keyboardlayout1.setVisibility(View.VISIBLE);
                }
            }
        });

        btmouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (keyboardlayout.getVisibility() == View.VISIBLE) {
                    keyboardlayout.setVisibility(View.GONE);
                }
                if (keyboardlayout1.getVisibility() == View.VISIBLE) {
                    keyboardlayout1.setVisibility(View.GONE);
                }
                if (presentetionlayout.getVisibility() == View.VISIBLE) {
                    presentetionlayout.setVisibility(View.GONE);
                }
                if (mouselayout.getVisibility() != View.VISIBLE) {
                    mouselayout.setVisibility(View.VISIBLE);
                }
                xInit = 0;
                yInit = 0;
                yScrollInit = 0;
            }
        });

        /* Define presentation tools likes starting process, end slide, next slide, privious slide,
                                        black - white screen, pen, arrow,  mouse moving */

        touchpad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                //define track fingers
                TrackFinger trackFinger = new TrackFinger(touchpad.getWidth(), touchpad.getHeight());
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Control.xInit = x;
                    Control.yInit = y;
                    if (event.getDownTime() - downtime < 500)
                        LeftmousePress = true;
                    downtime = event.getDownTime();
                    trackFinger.Appear();
                    trackFinger.DrawTrack(touchpad, x, y);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    uptime = event.getEventTime();
                    long diff = uptime - downtime;
                    if (diff < 200) {
                        LeftmouseClick = true;
                    }
                    if (leftmouseIsPress) {
                        ioDataStream.write(csv('1', '0', keymodifiers.MOUSE_LEFT).getBytes());
                        leftmouseIsPress = false;
                    }
                    trackFinger.Gone();
                    trackFinger.DrawTrack(touchpad, x, y);
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    int diffX = (int) (x - xInit);
                    int diffY = (int) (y - yInit);
                    if (diffX != 0 || diffY != 0) {
                        ioDataStream.write(csv('1', '3', diffX, diffY, 0).getBytes());
                    }
                    trackFinger.DrawTrack(touchpad, x, y);
                }

                if (LeftmousePress && LeftmouseClick) {
                    ioDataStream.write(csv('1', '1', keymodifiers.MOUSE_LEFT).getBytes());
                    LeftmousePress = false;
                    LeftmouseClick = false;
                    leftmouseIsPress = true;
                }
                if (!LeftmousePress && LeftmouseClick) {
                    ioDataStream.write(csv('1', '2', keymodifiers.MOUSE_LEFT).getBytes());
                    LeftmouseClick = false;
                }
                Control.xInit = x;
                Control.yInit = y;
                return true;
            }
        });

        scrollpad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                TrackFinger trackFinger = new TrackFinger(scrollpad.getWidth(), scrollpad.getHeight());
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Control.yScrollInit = y;
                    trackFinger.Appear();
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    int diffY = (int) (y - yScrollInit);
                    if (diffY != 0) {
                        ioDataStream.write(csv('1', '3', 0, 0, diffY).getBytes());
                    }
                    trackFinger.Appear();
                }
                if (event.getAction() == MotionEvent.ACTION_UP){
                    trackFinger.Gone();
                }
                trackFinger.DrawTrack1(scrollpad, x, y);
                Control.yScrollInit = y;
                return true;
            }
        });

        btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ioDataStream.write(csv('0', '2', keymodifiers.KEY_F5).getBytes());
            }
        });

        btstart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ioDataStream.write(csv('0', '1', keymodifiers.KEY_LEFT_SHIFT).getBytes());
                ioDataStream.write(csv('0', '2', keymodifiers.KEY_F5).getBytes());
                ioDataStream.write(csv('0', '0', keymodifiers.KEY_LEFT_SHIFT).getBytes());
                return true;
            }
        });

        btexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ioDataStream.write(csv('0', '2', keymodifiers.KEY_ESC).getBytes());
            }
        });

        btblack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ioDataStream.write(csv('0', '2', 'b').getBytes());
            }
        });

        btwhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ioDataStream.write(csv('0', '2', 'w').getBytes());
            }
        });

        btarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ioDataStream.write(csv('0', '1', keymodifiers.KEY_LEFT_CTRL).getBytes());
                ioDataStream.write(csv('0', '2', 'a').getBytes());
                ioDataStream.write(csv('0', '0', keymodifiers.KEY_LEFT_CTRL).getBytes());
            }
        });

        btlaserp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ioDataStream.write(csv('0', '1', keymodifiers.KEY_LEFT_CTRL).getBytes());
                ioDataStream.write(csv('0', '2', 'l').getBytes());
                ioDataStream.write(csv('0', '0', keymodifiers.KEY_LEFT_CTRL).getBytes());
            }
        });

        btpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ioDataStream.write(csv('0', '1', keymodifiers.KEY_LEFT_CTRL).getBytes());
                ioDataStream.write(csv('0', '2', 'p').getBytes());
                ioDataStream.write(csv('0', '0', keymodifiers.KEY_LEFT_CTRL).getBytes());
            }
        });

        bteraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ioDataStream.write(csv('0', '1', keymodifiers.KEY_LEFT_CTRL).getBytes());
                ioDataStream.write(csv('0', '2', 'e').getBytes());
                ioDataStream.write(csv('0', '0', keymodifiers.KEY_LEFT_CTRL).getBytes());
            }
        });

        bttleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ioDataStream.write(csv('0', '2', keymodifiers.KEY_LEFT_ARROW).getBytes());
            }
        });

        bttright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ioDataStream.write(csv('0', '2', keymodifiers.KEY_RIGHT_ARROW).getBytes());
            }
        });

        /* Define remote keyboard */
        btPageUp.setOnClickListener(mListener);
        btHome.setOnClickListener(mListener);
        btEsc.setOnClickListener(mListener);
        btPageDown.setOnClickListener(mListener);
        btEnd.setOnClickListener(mListener);

        btF1.setOnClickListener(mListener);
        btF2.setOnClickListener(mListener);
        btF3.setOnClickListener(mListener);
        btF4.setOnClickListener(mListener);
        btF5.setOnClickListener(mListener);
        btF6.setOnClickListener(mListener);
        btF7.setOnClickListener(mListener);
        btF8.setOnClickListener(mListener);
        btF9.setOnClickListener(mListener);
        btF10.setOnClickListener(mListener);
        btF11.setOnClickListener(mListener);
        btF12.setOnClickListener(mListener);
        btInsert.setOnClickListener(mListener);
        btTab.setOnClickListener(mListener);

        btGraveAccent.setOnClickListener(mListener);
        btHyphen.setOnClickListener(mListener);
        btEquals.setOnClickListener(mListener);
        btLSquareBracket.setOnClickListener(mListener);
        btRSquareBracket.setOnClickListener(mListener);
        btBackslash.setOnClickListener(mListener);
        btSemicolon.setOnClickListener(mListener);
        btApostrophe.setOnClickListener(mListener);
        btSlash.setOnClickListener(mListener);
        //btDelete.setOnClickListener(mListener);

        bt1.setOnClickListener(mListener);
        bt2.setOnClickListener(mListener);
        bt3.setOnClickListener(mListener);
        bt4.setOnClickListener(mListener);
        bt5.setOnClickListener(mListener);
        bt6.setOnClickListener(mListener);
        bt7.setOnClickListener(mListener);
        bt8.setOnClickListener(mListener);
        bt9.setOnClickListener(mListener);
        bt0.setOnClickListener(mListener);

        btQ.setOnClickListener(mListener);
        btW.setOnClickListener(mListener);
        btE.setOnClickListener(mListener);
        btR.setOnClickListener(mListener);
        btT.setOnClickListener(mListener);
        btY.setOnClickListener(mListener);
        btU.setOnClickListener(mListener);
        btI.setOnClickListener(mListener);
        btO.setOnClickListener(mListener);
        btP.setOnClickListener(mListener);

        btA.setOnClickListener(mListener);
        btS.setOnClickListener(mListener);
        btD.setOnClickListener(mListener);
        btF.setOnClickListener(mListener);
        btG.setOnClickListener(mListener);
        btH.setOnClickListener(mListener);
        btJ.setOnClickListener(mListener);
        btK.setOnClickListener(mListener);
        btL.setOnClickListener(mListener);

        btClock.setOnClickListener(mListener);
        btZ.setOnClickListener(mListener);
        btX.setOnClickListener(mListener);
        btC.setOnClickListener(mListener);
        btV.setOnClickListener(mListener);
        btB.setOnClickListener(mListener);
        btN.setOnClickListener(mListener);
        btM.setOnClickListener(mListener);
        btBSpace.setOnClickListener(mListener);

        btLShift.setOnClickListener(mListener);
        btComma.setOnClickListener(mListener);
        btSpace.setOnClickListener(mListener);
        btDot.setOnClickListener(mListener);
        btEnter.setOnClickListener(mListener);

        btLWin.setOnClickListener(mListener);

        btLShift.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ioDataStream.write(csv('0', '1', keymodifiers.KEY_LEFT_SHIFT).getBytes());
                    btLShift.setBackgroundColor(0xff00ff00);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    ioDataStream.write(csv('0', '0', keymodifiers.KEY_LEFT_SHIFT).getBytes());
                    btLShift.setBackgroundResource(android.R.drawable.btn_default);
                }

                return true;
            }
        });

        btRshift.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ioDataStream.write(csv('0', '1', keymodifiers.KEY_RIGHT_SHIFT).getBytes());
                    btRshift.setBackgroundColor(0xff00ff00);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    ioDataStream.write(csv('0', '0', keymodifiers.KEY_RIGHT_SHIFT).getBytes());
                    btRshift.setBackgroundResource(android.R.drawable.btn_default);
                }

                return true;
            }
        });

        btRCtrl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ioDataStream.write(csv('0', '1', keymodifiers.KEY_RIGHT_CTRL).getBytes());
                    btRCtrl.setBackgroundColor(0xff00ff00);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    ioDataStream.write(csv('0', '0', keymodifiers.KEY_RIGHT_CTRL).getBytes());
                    btRCtrl.setBackgroundResource(android.R.drawable.btn_default);
                }

                return true;
            }
        });

        btRAlt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ioDataStream.write(csv('0', '1', keymodifiers.KEY_RIGHT_ALT).getBytes());
                    btRAlt.setBackgroundColor(0xff00ff00);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    ioDataStream.write(csv('0', '0', keymodifiers.KEY_RIGHT_ALT).getBytes());
                    btRAlt.setBackgroundResource(android.R.drawable.btn_default);
                }

                return true;
            }
        });

        /* Define remote mouse have some function such as touchpad, left - right mouse, middle mouse, next - last page (browser)  */

        bigtouchpad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int pointercount = event.getPointerCount();

                //define track fingers
                TrackFinger trackFinger = new TrackFinger(bigtouchpad.getWidth(), bigtouchpad.getHeight());

                if (pointercount ==2) {
                    float x = 0;
                    float y = 0;

                    for (int i = 0; i < pointercount; i++) {
                        x += event.getX(i);
                        y += event.getY(i);
                        trackFinger.DrawTrack(bigtouchpad, event.getX(i), event.getY(i));
                    }
                    x /= pointercount;
                    y /= pointercount;

                    if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                        Control.xScrollInit = x;
                        Control.yScrollInit = y;
                        Sdowntime = event.getDownTime();
                    }
                    if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                        isTwoPointer = true;
                    }
                    if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
                        isTwoPointer = true;
                        int diffY = (int) (y - yScrollInit);
                        int diffX = (int) (x - xScrollInit);
                        if (diffY != 0 && Math.abs(diffX) < 3) {
                            ioDataStream.write(csv('1', '3', 0, 0, diffY).getBytes());
                        }
                    }
                    Control.xScrollInit = x;
                    Control.yScrollInit = y;
                }

                if (pointercount == 1) {
                    float x = event.getX();
                    float y = event.getY();

                    if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                        Control.xInit = x;
                        Control.yInit = y;
                        if (event.getDownTime() - downtime < 500)
                            LeftmousePress = true;
                        downtime = event.getDownTime();
                        trackFinger.Appear();
                        trackFinger.DrawTrack(bigtouchpad, x, y);
                    }
                    if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                        uptime = event.getEventTime();
                        long diff = uptime - downtime;
                        if (diff < 200) {
                            LeftmouseClick = true;
                        }
                        if (leftmouseIsPress) {
                            ioDataStream.write(csv('1', '0', keymodifiers.MOUSE_LEFT).getBytes());
                            leftmouseIsPress = false;
                        }
                        trackFinger.Gone();
                        trackFinger.DrawTrack(bigtouchpad, x, y);
                    }
                    if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
                        if (isTwoPointer) {
                            isTwoPointer = false;
                        } else {
                            int diffX = (int) (x - xInit);
                            int diffY = (int) (y - yInit);
                            if (diffX != 0 || diffY != 0) {
                                ioDataStream.write(csv('1', '3', diffX, diffY, 0).getBytes());
                            }
                        }
                        trackFinger.Appear();
                        trackFinger.DrawTrack(bigtouchpad, x, y);
                    }

                    if (LeftmousePress && LeftmouseClick) {
                        ioDataStream.write(csv('1', '1', keymodifiers.MOUSE_LEFT).getBytes());
                        LeftmousePress = false;
                        LeftmouseClick = false;
                        leftmouseIsPress = true;
                    }
                    if (!LeftmousePress && LeftmouseClick) {
                        ioDataStream.write(csv('1', '2', keymodifiers.MOUSE_LEFT).getBytes());
                        LeftmouseClick = false;
                    }
                    Control.xInit = x;
                    Control.yInit = y;
                }
                return true;
            }
        });

        smallscrollpad.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                TrackFinger trackFinger = new TrackFinger(scrollpad.getWidth(), scrollpad.getHeight());
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Control.yScrollInit = y;
                    Sdowntime = event.getDownTime();
                    trackFinger.Appear();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    long diff = event.getEventTime() - Sdowntime;
                    if (diff < 200) {
                        ioDataStream.write(csv('1', '2', keymodifiers.MOUSE_MIDDLE).getBytes());
                    }
                    trackFinger.Gone();
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    int diffY = (int) (y - yScrollInit);
                    if (diffY != 0) {
                        ioDataStream.write(csv('1', '3', 0, 0, diffY).getBytes());
                    }
                    trackFinger.Appear();
                }
                trackFinger.DrawTrack1(smallscrollpad,x,y);
                Control.yScrollInit = y;
                return true;
            }
        });

        btleftmouse.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    if (!leftmouseIsPress)
                        ioDataStream.write(csv('1', '1', keymodifiers.MOUSE_LEFT).getBytes());
                if (event.getAction() == MotionEvent.ACTION_UP)
                    ioDataStream.write(csv('1', '0', keymodifiers.MOUSE_LEFT).getBytes());
                return true;
            }
        });
        btrightmouse.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    ioDataStream.write(csv('1', '1', keymodifiers.MOUSE_RIGHT).getBytes());
                if (event.getAction() == MotionEvent.ACTION_UP)
                    ioDataStream.write(csv('1', '0', keymodifiers.MOUSE_RIGHT).getBytes());
                return true;
            }
        });

        btprivious.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                ioDataStream.write(csv('0', '1', keymodifiers.KEY_RIGHT_ALT).getBytes());
                ioDataStream.write(csv('0', '2', keymodifiers.KEY_RIGHT_ARROW).getBytes());
                ioDataStream.write(csv('0', '0', keymodifiers.KEY_RIGHT_ALT).getBytes());
            }
        });
        btnext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                ioDataStream.write(csv('0', '1', keymodifiers.KEY_RIGHT_ALT).getBytes());
                ioDataStream.write(csv('0', '2', keymodifiers.KEY_LEFT_ARROW).getBytes());
                ioDataStream.write(csv('0', '0', keymodifiers.KEY_RIGHT_ALT).getBytes());

            }
        });
    }

    private String csv(char device, char status, char code) {
        //0,0,113\n
        if (device == '0') {
            return (device + "," + status + "," + (int) code + "\n");
        }
        if (device == '1') {
            return (device + "," + status + "," + code + "\n");
        }
        return "";
    }

    private String csv(char device, char status, int x, int y, int z) {
        if (device == '1') {
            return (device + "," + status + "," + x + "," + y + "," + z + "\n");
        }
        return "";
    }

    private String csv(char device, char status, String code) {
        if (device == '0'){
        return(device + "," + status + "," + code + "\n");
        }
        return "";
    }
}
