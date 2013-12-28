package wisell.hotspot;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.content.Context;
import android.view.WindowManager;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    boolean wasAPEnabled = false;
    static WifiAP wifiAp;
    private WifiManager wifi;
    static ToggleButton btnWifiToggle;
    static CheckBox checkencrypted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnWifiToggle = (ToggleButton) findViewById(R.id.btnWifiToggle);
        checkencrypted = (CheckBox) findViewById(R.id.checkBox);

        wifiAp = new WifiAP();
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        btnWifiToggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                wifiAp.toggleWiFiAP(wifi, MainActivity.this);

            }
        });

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasAPEnabled) {
            if (wifiAp.getWifiAPState()!=wifiAp.WIFI_AP_STATE_ENABLED && wifiAp.getWifiAPState()!=wifiAp.WIFI_AP_STATE_ENABLING){
                wifiAp.toggleWiFiAP(wifi, MainActivity.this);
            }
        }
        updateStatusDisplay();
    }

    @Override
    public void onPause() {
        super.onPause();
        boolean wifiApIsOn = wifiAp.getWifiAPState()==wifiAp.WIFI_AP_STATE_ENABLED || wifiAp.getWifiAPState()==wifiAp.WIFI_AP_STATE_ENABLING;
        if (wifiApIsOn) {
            wasAPEnabled = true;
            wifiAp.toggleWiFiAP(wifi, this);
        } else {
            wasAPEnabled = false;
        }
        updateStatusDisplay();
    }

    public static void updateStatusDisplay() {
        if (wifiAp.getWifiAPState()==wifiAp.WIFI_AP_STATE_ENABLED || wifiAp.getWifiAPState()==wifiAp.WIFI_AP_STATE_ENABLING) {
            //btnWifiToggle.setImageResource(R.drawable.off_button);
            btnWifiToggle.setButtonDrawable(R.drawable.off_button);
            //findViewById(R.id.bg).setBackgroundResource(R.drawable.bg_wifi_on);
        } else {
            //btnWifiToggle.setImageResource(R.drawable.on_button);
            btnWifiToggle.setButtonDrawable(R.drawable.on_button);
            //findViewById(R.id.bg).setBackgroundResource(R.drawable.bg_wifi_off);
        }
    }
}
