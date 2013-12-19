package com.c4a.wisell.utils;

import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * Created by ousmaneo on 12/19/13.
 */
public class WifiUtils {

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

}
