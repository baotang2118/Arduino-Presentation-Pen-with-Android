//This sketch is used arduino pro micro, which support Atmega32u4
//You might have to modified source code to compatible with your one

/*
        if dev equals to 0, it will be a keyboard
        if dev equals to 1, it will be a mouse
        if status equals to 1, keyboard or mouse is pressed
        if status equals to 0, keyboard or mouse is released
        if status equals to 2, keyboard sends keystroke or mouse clicks
        if status equals to 3, mouse is draged
        if dev is a mouse, code = 1 is mouse left button
                              code = 2 is mouse right button
                              code = 3 is mouse midle button
        if dev is a keyboard, code is keystroke number
        x, y, z are mouse position
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
  int val3 = csv.indexOf(',', val2 + 1);
  int val4 = csv.indexOf(',', val3 + 1);

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
void get4value(String csv) {
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
      get4value(csv);
  }
}
