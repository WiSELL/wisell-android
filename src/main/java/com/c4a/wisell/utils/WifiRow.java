package com.c4a.wisell.utils;

/**
 * Created by ousmaneo on 12/19/13.
 */
public class WifiRow {
    private int wifiRange;
    private String bssid;

    public WifiRow(int wifiRange, String bssid) {
        this.wifiRange = wifiRange;
        this.bssid = bssid;
    }

    public int getWifiRange() {
        return wifiRange;
    }

    public void setWifiRange(int wifiRange) {
        this.wifiRange = wifiRange;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    @Override
    public String toString() {
        return "WifiRow{" +
                "wifiRange=" + wifiRange +
                ", bssid='" + bssid + '\'' +
                '}';
    }
}
