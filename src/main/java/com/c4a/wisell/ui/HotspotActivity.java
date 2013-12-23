package com.c4a.wisell.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.c4a.wisell.R;
import com.c4a.wisell.adapters.CustomWifiAdapter;
import com.c4a.wisell.utils.WifiRow;
import com.c4a.wisell.utils.WifiUtils;

import java.util.ArrayList;
import java.util.List;

public class HotspotActivity extends ActionBarActivity {
    ListView wilist;
    List<WifiRow> wifiRows;
    WifiManager manager;
    CustomWifiAdapter adapter;
    WifiReceiver wifiReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot);
        wilist = (ListView) findViewById(R.id.listWifi);
        manager = (WifiManager) getSystemService(WIFI_SERVICE);
        wifiRows = new ArrayList<WifiRow>();
        //Register wifi scan results receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        wifiReceiver = new WifiReceiver();
        registerReceiver(wifiReceiver,filter);

        if (WifiUtils.isWifiEnabled(this)){
            manager.startScan();
        }else{
            WifiUtils.setWifiEnabled(this, true);
            manager.startScan();
        }

        wilist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(wifiReceiver);
        Log.d("Wisell","Wifireceiver paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        wifiReceiver = new WifiReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wifiReceiver,filter);
        Log.d("Wisell","Wifireceiver resumed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiReceiver);
        Log.d("Wisell","Wifireceiver Stopped");
    }

    class WifiReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<ScanResult> results = manager.getScanResults();
            wifiRows.removeAll(wifiRows);
            for (int i=0;i<results.size();i++){
                if (results.get(i).SSID.startsWith("NET")){
                    WifiRow item = new WifiRow(manager.calculateSignalLevel(results.get(i).level,5),results.get(i).SSID);
                    wifiRows.add(item);
                }
            }
            adapter = new CustomWifiAdapter(context,R.layout.wifi_row,wifiRows);
            wilist.setAdapter(adapter);
        }
    }


}
