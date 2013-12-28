package com.c4a.wisell.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import com.c4a.wisell.R;
public class HomeActivity extends Activity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                if(pref.getBoolean("PREF_FIRST_LAUNCH",true)==false){
                    Log.d("TAG1", "firstRun: " + Boolean.valueOf(pref.getBoolean("PREF_FIRST_LAUNCH",true)).toString());
                    Intent i = new Intent(HomeActivity.this, HotspotActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Log.d("TAG1", "firstRun(false): " + Boolean.valueOf(pref.getBoolean("PREF_FIRST_LAUNCH",true)).toString());
                    Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        },SPLASH_TIME_OUT);
    }

}
