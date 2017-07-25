package com.example.satan.room_controller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_ENABLE_BT = 0;
    Button pair;
    BluetoothAdapter mBluetoothAdapter;
    ArrayList<BluetoothDevice> pairedDeviceArrayList;
    ListView listViewPairedDevice;
    ArrayAdapter<BluetoothDevice> pairedDeviceAdapter;
    ArrayList<String>  listing;
    ArrayAdapter<String> lister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(MainActivity.this, "device not supported", Toast.LENGTH_LONG).show();
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        listViewPairedDevice = (ListView)findViewById(R.id.list);
        pair = (Button) findViewById(R.id.pair);
        pair.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.pair) {
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                pairedDeviceArrayList = new ArrayList<BluetoothDevice>();
                listing =new ArrayList<String>();

                for (BluetoothDevice device : pairedDevices) {
                    pairedDeviceArrayList.add(device);
               //     listing.add(device+"\n"+device.getName());
                }

               pairedDeviceAdapter = new ArrayAdapter<BluetoothDevice>(this,
                        android.R.layout.simple_list_item_1, pairedDeviceArrayList);
                listViewPairedDevice.setAdapter(pairedDeviceAdapter);
                //lister =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listing);
                //listViewPairedDevice.setAdapter(lister);
                listViewPairedDevice.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        BluetoothDevice device =
                                (BluetoothDevice) parent.getItemAtPosition(position);
                        Toast.makeText(MainActivity.this,
                                "Name: " + device.getName() + "\n"
                                        + "Address: " + device.getAddress() + "\n"
                                        + "BondState: " + device.getBondState() + "\n"
                                        + "BluetoothClass: " + device.getBluetoothClass() + "\n"
                                        + "Class: " + device.getClass(),
                                Toast.LENGTH_LONG).show();
                        Intent i=new Intent(MainActivity.this,sender.class);
                        startActivity(i);
                    }
                });

            }
        }
    }
}
