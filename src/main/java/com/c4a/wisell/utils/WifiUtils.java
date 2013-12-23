package com.c4a.wisell.utils;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import com.c4a.wisell.R;

/**
 * Created by ousmaneo on 12/19/13.
 */
public class WifiUtils {

    private static final String TAG ="Wifiutils" ;

    /**
     * Return true if wifi is enabled
     * @param context
     * @return
     */
    public static boolean isWifiEnabled(final Context context)  {
        final WifiManager wifiManager =
                (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    /**
     * Enable or disable wifi enabled (true or false)
     * @param context
     * @param enabled
     * @return
     */
    public static boolean setWifiEnabled(final  Context context,boolean enabled){
        final WifiManager wifiManager =
                (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.setWifiEnabled(enabled);
    }


    public static void installConferenceWiFi(final Context context,String wifi_ssid) {
        // Create config
        WifiConfiguration config = new WifiConfiguration();
        // Must be in double quotes to tell system this is an ASCII SSID and passphrase.
        config.SSID = String.format("\"%s\"", wifi_ssid);
        //config.preSharedKey = String.format("\"%s\"", Config.WIFI_PASSPHRASE);  get selected ssid

        // Store config
        final WifiManager wifiManager =
                (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int netId = wifiManager.addNetwork(config);
        if (netId != -1) {
            wifiManager.enableNetwork(netId, false);
            boolean result = wifiManager.saveConfiguration();
            if (!result) {
                Log.e(TAG, "Unknown error while calling WiFiManager.saveConfiguration()");
                Toast.makeText(context,
                        context.getResources().getString(R.string.wifi_install_error_message),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e(TAG, "Unknown error while calling WiFiManager.addNetwork()");
            Toast.makeText(context,
                    context.getResources().getString(R.string.wifi_install_error_message),
                    Toast.LENGTH_SHORT).show();
        }
    }


}
