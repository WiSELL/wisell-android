package com.c4a.wisell.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.c4a.wisell.R;
import com.c4a.wisell.utils.WifiRow;

import java.util.List;

/**
 * Created by ousmaneo on 12/19/13.
 */
public class CustomWifiAdapter extends ArrayAdapter<WifiRow> {
    Context context;

    public CustomWifiAdapter(Context context, int resourceId, List<WifiRow> items) {
        super(context, resourceId, items);
        this.context =context;
    }
     /*Private view holder class*/
    private class ViewHolder{
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        WifiRow  wifiRow = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView==null) {
            convertView = mInflater.inflate(R.layout.wifi_row,null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.txtBssid);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imgWifi);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder) convertView.getTag();
        holder.textView.setText(wifiRow.getBssid());
//        holder.imageView.setImageResource(wifiRow.getWifiRange());
        holder.imageView.setImageResource(R.drawable.ic_launcher);
        return convertView;
    }
}
