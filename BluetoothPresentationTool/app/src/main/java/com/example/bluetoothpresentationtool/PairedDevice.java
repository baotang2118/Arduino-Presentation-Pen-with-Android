package com.example.bluetoothpresentationtool;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class PairedDevice extends AppCompatActivity {

    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    private BluetoothSocket Blsocket;
    ListView listView;
    Button btnPairDevice;

    public static final int MESSAGE_READ = 0;
    public static final int MESSAGE_WRITE = 1;
    public static final int MESSAGE_TOAST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paired_devices);

        BA = BluetoothAdapter.getDefaultAdapter();

        listView = (ListView) findViewById(R.id.devices_list);
        btnPairDevice = (Button) findViewById(R.id.btnPairDevice);

        btnPairDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BA == null) return;
                if (!BA.isEnabled()) {
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnOn, 0);
                    Toast.makeText(getApplicationContext(), "Turning on", Toast.LENGTH_LONG).show();
                } else {
                    pairedDevices = BA.getBondedDevices();

                    ArrayList list = new ArrayList();

                    for (BluetoothDevice bt : pairedDevices)
                        list.add(bt.getName() + "\n" + bt.getAddress());
                    Toast.makeText(getApplicationContext(), "Showing Paired Devices", Toast.LENGTH_SHORT).show();

                    final ArrayAdapter adapter = new ArrayAdapter(PairedDevice.this, android.R.layout.simple_list_item_1, list);

                    listView.setAdapter(adapter);
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int tmp = 0;
                String temp = "";
                for (BluetoothDevice bt : pairedDevices) {
                    if (position == tmp) {
                        temp = bt.getName();
                        ConnectThread connectThread = new ConnectThread(bt);
                        connectThread.run(BA);
                        Blsocket = connectThread.getBlsocket();
                        DataHolder.setData(Blsocket);
                        startActivity(new Intent(PairedDevice.this,Control.class));
                        break;
                    }
                    tmp++;
                }
                Toast.makeText(getApplicationContext(), "Connected " + temp, Toast.LENGTH_LONG).show();
            }
        });
    }
}
