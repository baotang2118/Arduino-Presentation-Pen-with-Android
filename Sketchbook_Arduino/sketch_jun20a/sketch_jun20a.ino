//This sketch is used arduino pro micro, which support Atmega32u4
//You might have to modified source code to compatible with your one

#include <Keyboard.h>
#include <Mouse.h>
#include <ArduinoJson.h>

String json;

void setup()
{
  //Serial.begin(115200);
  Serial1.begin(115200);
  Mouse.begin();
}

void loop()
{
  if (Serial1.available() > 0) {
    json = Serial1.readStringUntil('\n');
    //Serial.println(json);
    //using json format
    StaticJsonBuffer<200> jsonBuffer;
    JsonObject &object = jsonBuffer.parseObject(json);
    //if dev equals to 0, it will be a keyboard
    //if dev equals to 1, it will be a mouse
    int dev = object["dev"];
    //if status equals to 1, keyboard or mouse is pressed
    //if status equals to 0, keyboard or mouse is released
    //if status equals to 2, keyboard sends keystroke or mouse clicks
    //if status equals to 3, mouse is draged
    int stt = object["stt"];
    /*if dev is a mouse, code = 1 is mouse left button
                          code = 2 is mouse right button
                          code = 3 is mouse midle button*/ 
    //if dev is a keyboard, code is keystroke number              
    int code = object["code"];
    //x, y, z are mouse position
    int x = object["x"];
    int y = object["y"];
    int z = object["z"];
    
    if (dev == 0) {
      if (stt == 1)
        Keyboard.press(code);
      if (stt == 0)
        Keyboard.release(code);
      if (stt == 2)
        Keyboard.write(code);
    }
    
    if (dev == 1) {
      if (stt == 1) {
        if (code == 1)
          Mouse.press(MOUSE_LEFT);
        if (code == 2)
          Mouse.press(MOUSE_RIGHT);
        if (code == 3)
          Mouse.press(MOUSE_MIDDLE);
      }
      if (stt == 0) {
        if (code == 1)
          Mouse.release(MOUSE_LEFT);
        if (code == 2)
          Mouse.release(MOUSE_RIGHT);
        if (code == 3)
          Mouse.release(MOUSE_MIDDLE);
      }
      if (stt == 2) {
        if (code == 1)
          Mouse.click(MOUSE_LEFT);
        if (code == 2)
          Mouse.click(MOUSE_RIGHT);
        if (code == 3)
          Mouse.click(MOUSE_MIDDLE);
      }
      if (stt == 3) {
        Mouse.move(x, y, z);
      }
    }
  }
}
