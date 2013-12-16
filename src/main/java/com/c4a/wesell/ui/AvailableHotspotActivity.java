package com.c4a.wesell.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.c4a.wesell.util.WifiUtils;
import com.c4a.wesell.R;

import java.util.List;


public class AvailableHotspotActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ListView myList=(ListView)findViewById(R.id.listWifi);

        final WifiManager   manager = (WifiManager) getSystemService(WIFI_SERVICE);
        //Register wifi scan results receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Receiving wifi scan result", Toast.LENGTH_SHORT).show();

                List<ScanResult> results = manager.getScanResults();
                final String[] items = new String[results.size()];
                for (int i=0;i<results.size();i++){
                    items[i] = results.get(i).SSID;
                    Log.e("Wessel","wifi"+results.get(i).SSID);
                    //Log is showing wifi list 
                }

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,items);
                Log.e("Wisseeellll","Adapter");
                /*
                Todo look at listAdapter
                myList.setAdapter(adapter);
                */
            }
        },filter);

        if (WifiUtils.isWifiEnabled(this)){
            manager.startScan();
        }else{
            WifiUtils.setWifiEnabled(this,true);
            manager.startScan();
        }

    }

}
