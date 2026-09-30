// This sketch runs on arduino pro micro, which supports Atmega32u4
// You might have to modify the source code to be compatible with your board

/*
  Commands arrive on Serial1 as newline-terminated CSV, in one of two shapes:

    dev,status,code    keyboard keys and mouse buttons
    dev,status,x,y,z   mouse movement and scrolling

  dev = 0, the device acts as a keyboard
    status = 0, release key code
    status = 1, press and hold key code
    status = 2, type key code
  dev = 1, the device acts as a mouse
    status = 0, release button
    status = 1, press and hold button
    status = 2, click button
      code = 1, left button
      code = 2, right button
      code = 3, middle button
    status = 3, move by x and y, scroll by z
*/

#include <Keyboard.h>
#include <Mouse.h>

String csv;

void setup()
{
  //Serial.begin(115200);
  Serial1.begin(115200);
  Mouse.begin();
}

void get3value(String csv) {
  int val1 = csv.indexOf(',');
  int val2 = csv.indexOf(',', val1 + 1);

  String dev = csv.substring(0, val1);
  String stt = csv.substring(val1 + 1, val2);
  String code = csv.substring(val2 + 1, csv.length());

  if (dev == "0") {
    if (stt == "1")
      Keyboard.press(code.toInt());
    if (stt == "0")
      Keyboard.release(code.toInt());
    if (stt == "2")
      Keyboard.write(code.toInt());
  }

  if (dev == "1") {
    if (stt == "1") {
      if (code == "1")
        Mouse.press(MOUSE_LEFT);
      if (code == "2")
        Mouse.press(MOUSE_RIGHT);
      if (code == "3")
        Mouse.press(MOUSE_MIDDLE);
    }
    if (stt == "0") {
      if (code == "1")
        Mouse.release(MOUSE_LEFT);
      if (code == "2")
        Mouse.release(MOUSE_RIGHT);
      if (code == "3")
        Mouse.release(MOUSE_MIDDLE);
    }
    if (stt == "2") {
      if (code == "1")
        Mouse.click(MOUSE_LEFT);
      if (code == "2")
        Mouse.click(MOUSE_RIGHT);
      if (code == "3")
        Mouse.click(MOUSE_MIDDLE);
    }
  }
};
void get5value(String csv) {
  int val1 = csv.indexOf(',');
  int val2 = csv.indexOf(',', val1 + 1);
  int val3 = csv.indexOf(',', val2 + 1);
  int val4 = csv.indexOf(',', val3 + 1);

  String dev = csv.substring(0, val1);
  String stt = csv.substring(val1 + 1, val2);
  String x = csv.substring(val2 + 1, val3);
  String y = csv.substring(val3 + 1, val4);
  String z = csv.substring(val4 + 1, csv.length());

  if (dev == "1") {
    if (stt == "3") {
      Mouse.move(x.toInt(), y.toInt(), z.toInt());
    }
  }
};

void loop()
{
  if (Serial1.available() > 0) {
    csv = Serial1.readStringUntil('\n');
    Serial.println(csv);

    int comma = 0;

    for (int i = 0; i < csv.length(); i++) {
      if (csv[i] == ',')
        comma++;
    }
    if (comma == 2)
      get3value(csv);
    if (comma == 4)
      get5value(csv);
  }
}
