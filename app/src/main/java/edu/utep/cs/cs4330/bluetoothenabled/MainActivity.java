package edu.utep.cs.cs4330.bluetoothenabled;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button BTButton = (Button) findViewById(R.id.BTButton);
        final Button deviceButton = (Button) findViewById(R.id.Devices);
        final Button serverButton = (Button) findViewById(R.id.serverButton);
        final Button clientButton = (Button) findViewById(R.id.clientButton);

        BTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBluetoothEnabled()) {
                    BTButton.setText("BlueTooth is OFF ");
                    BTButton.setBackgroundColor(Color.GRAY);
                    BluetoothAdapter bt = BluetoothAdapter.getDefaultAdapter();
                    bt.disable();
                }else {
                    BTButton.setText("BlueTooth is ON");
                    BTButton.setTextColor(Color.WHITE);
                    BTButton.setBackgroundColor(Color.BLUE);
                    BluetoothAdapter bt = BluetoothAdapter.getDefaultAdapter();
                    bt.enable();

                }

            }
        });

        deviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDevices();

            }
        });

    }

    private boolean isBluetoothEnabled(){
        BluetoothAdapter bt = BluetoothAdapter.getDefaultAdapter();
        return bt != null && bt.isEnabled();
    }
    private void enableBluetooth(){
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivity(intent);
    }
    private void displayDevices(){
        if(isBluetoothEnabled()){
            BluetoothAdapter bt = BluetoothAdapter.getDefaultAdapter();
            Set<BluetoothDevice>devices = bt.getBondedDevices();
            for(BluetoothDevice device:devices){
                Log.d("Bluetooth", "paired with" + device.getName());
            }
        }else{
            toast("Please enable bluetooth");
        }
    }
    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
