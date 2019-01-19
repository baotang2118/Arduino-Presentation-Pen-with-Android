package com.example.bluetoothpresentationtool;

import android.bluetooth.BluetoothSocket;

public class DataHolder {
    private static BluetoothSocket data = null;
    public static BluetoothSocket getData() {return data;}
    public static void setData(BluetoothSocket data) {DataHolder.data = data;}
}
