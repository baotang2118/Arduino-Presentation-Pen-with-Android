# Arduino-Presentation-Pen-with-Android

Presentation pens sold online are expensive. This project turns a phone into one using an Arduino. The receiver appears to the host PC as a normal USB keyboard and mouse, so no Wi-Fi and no software installation on the PC are needed.

Project write-up:
- https://baotdvi.wordpress.com/2019/01/23/turn-your-phone-into-presentation-pen-with-arduino/
- https://baotdvi.wordpress.com/2019/01/30/turn-your-phone-into-presentation-pen-with-arduino-v2/

## How it works

The Android app connects to a Bluetooth serial module over classic Bluetooth SPP and sends short CSV commands. The Arduino reads those lines from `Serial1` and replays them through the USB HID `Keyboard` and `Mouse` libraries.

```
Phone (Android app)  --Bluetooth SPP-->  HC-05  --UART 115200-->  Arduino Pro Micro  --USB HID-->  PC
```

An ATmega32U4 board such as the Pro Micro is required, because only that chip can act as a USB keyboard and mouse.

## Hardware

- Arduino Pro Micro (ATmega32U4) or another ATmega32U4 board
- HC-05 Bluetooth serial module, paired with the phone beforehand

The module talks to `Serial1` at 115200 baud, so the serial lines cross over:

| Pro Micro | HC-05 |
| --- | --- |
| `VCC` | `5+` |
| `GND` | `GND` |
| `TX0` | `RX` |
| `RX1` | `TX` |

<img src="screenshot-result/circuit-wiring.png" height="200" width="300"> <img src="screenshot-result/presentation-pen-board.jpg" height="260" width="190">

## Serial protocol

Every message is one newline-terminated CSV line, in one of two shapes:

```
device,status,code        # keys and mouse buttons
device,status,x,y,z       # pointer movement and scrolling
```

| Field | Meaning |
| --- | --- |
| `device` | `0` = keyboard, `1` = mouse |
| `status` | `0` release, `1` press/hold, `2` click or keystroke, `3` move/scroll |
| `code` | key code, or mouse button `1` left / `2` right / `3` middle |
| `x`, `y`, `z` | pointer delta and scroll delta |

Examples: `0,2,97` types `a`, `1,1,1` holds the left mouse button, and `1,3,0,0,-2` scrolls.

The Arduino counts the commas to decide which parser to run, so the two message shapes can be mixed freely on the same link.

## Repository layout

| Path | Contents |
| --- | --- |
| [BluetoothPresentationTool](BluetoothPresentationTool) | Android app, package `com.example.bluetoothpresentationtool` |
| [Sketchbook_Arduino/sketch_jan24a](Sketchbook_Arduino/sketch_jan24a) | Arduino receiver sketch |
| [screenshot-result](screenshot-result) | Screenshots and photos |

Key Android classes: `PairedDevice` (launcher, device list), `ConnectThread` (RFCOMM socket), `IODataStream` (stream I/O), `Control` (all input modes), `keymodifiers` (key codes), `TrackFinger` (touch feedback).

## Build

1. Open `BluetoothPresentationTool` in Android Studio, then build and install on the phone. The app targets Android 7.0 (API 24) and newer.
2. Open `Sketchbook_Arduino/sketch_jan24a/sketch_jan24a.ino` in the Arduino IDE, select the ATmega32U4 board, and upload.
3. Pair the phone with the Bluetooth module, then pick it from the device list in the app.

## Screens

Pick a paired module, and grant the Bluetooth request when prompted.

<img src="screenshot-result/Screenshot_20180807-073110.jpg" height="300" width="200"> <img src="screenshot-result/Screenshot_20180807-074118.jpg" height="300" width="200">

The four icons along the top switch between the modes: presentation, letter keyboard, function keyboard, and mouse.

Presentation mode carries slideshow shortcuts (start, exit, black, white, arrow, laser pointer, pen, eraser), previous and next slide bars, and a trackpad with a scroll strip. The letter keyboard adds modifier keys such as Ctrl, Alt, Win, and Shift, while the second keyboard holds navigation keys and F1 to F12. Mouse mode pairs a large trackpad with left and right buttons, a middle scroll strip, and the same slide arrows.

<img src="screenshot-result/Screenshot_20180807-074154.jpg" height="300" width="200"> <img src="screenshot-result/Screenshot_20180807-074204.jpg" height="300" width="200"> <img src="screenshot-result/Screenshot_20180807-074216.jpg" height="300" width="200"> <img src="screenshot-result/Screenshot_20180807-074225.jpg" height="300" width="200">
