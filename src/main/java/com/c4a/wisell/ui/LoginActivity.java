package com.c4a.wisell.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import com.c4a.wisell.R;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoginActivity extends Activity {
    public static String TAG = "Login";
    public static String URI = "http://wisell-software.rhcloud.com/api/";
    String imei;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TelephonyManager teleManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        imei = teleManager.getDeviceId();
        addListenerOnButton();


    }

    public void addListenerOnButton() {

        button = (Button) findViewById(R.id.loginSuivant);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               TextView numero = (TextView) findViewById(R.id.phone_number);
                button.setClickable(false);
                String link = URI+"clients/register";
                new DoLoginTask().execute(link,numero.getText().toString(),imei);

            }
        });

    }

    private class DoLoginTask extends AsyncTask<String,Long,HttpResponse>{

        protected HttpResponse doInBackground(String... params) {
            String link = params[0];
            String numero = params[1];
            String imei  = params[2];
            HttpPost request = new HttpPost(link);
            try {
                HttpEntity entity = new StringEntity("{\"phone\":"+numero+",\"imei\":"+imei+"}");
                request.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            request.setHeader("Content-Type", "application/json");
            AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
            try {
               return client.execute(request);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }finally {
                client.close();
            }
        }

        @Override
        protected void onPreExecute() {
            setFirsTime();
            goToNext();
            finish();
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
        }

        protected void onPostExecute(HttpResponse result) {
            Log.d("Register","Result " + result.getStatusLine().getStatusCode());
        }
    }

    public void goToNext(){
        Intent intent = new Intent(this, HotspotActivity.class);
        startActivity(intent);
    }

    public void setFirsTime(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("PREF_FIRST_LAUNCH", false);
        editor.commit();
    }
}
