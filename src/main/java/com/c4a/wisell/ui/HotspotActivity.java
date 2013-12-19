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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.c4a.wisell.R;
import com.c4a.wisell.utils.WifiUtils;

import java.util.List;

public class HotspotActivity extends ActionBarActivity {
    ListView wilist;
    WifiManager manager;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot);
        wilist = (ListView) findViewById(R.id.listWifi);
        manager = (WifiManager) getSystemService(WIFI_SERVICE);
        //Register wifi scan results receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(new WifiReceiver(),filter);

        if (WifiUtils.isWifiEnabled(this)){
            manager.startScan();
        }else{
            WifiUtils.setWifiEnabled(this, true);
            manager.startScan();
        }
    }



    class WifiReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<ScanResult> results = manager.getScanResults();
            final String[] items = new String[results.size()];
            for (int i=0;i<results.size();i++){
                items[i] = results.get(i).SSID;
            }
            adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,items);
            wilist.setAdapter(adapter);
        }
    }


}
