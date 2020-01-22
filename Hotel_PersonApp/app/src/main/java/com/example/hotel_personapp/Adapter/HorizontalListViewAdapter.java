package com.example.hotel_personapp.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotel_personapp.R;

public class HorizontalListViewAdapter extends BaseAdapter {
    private final Context context;
    private final int screenWidth;
    private final String[] strings;
    private final Integer[] images;

    public HorizontalListViewAdapter(Context applicationContext, int screenWidth, String[] strings, Integer[] images) {
        this.context = applicationContext;
        this.screenWidth = screenWidth;
        this.strings = strings;
        this.images = images;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if (convertView == null) {
            convertView = View.inflate(context.getApplicationContext(), R.layout.horizontallistview_item, null);
            holder = new ViewHolder();
            holder.miv = convertView.findViewById(R.id.iv_me_function);
            holder.mtv = convertView.findViewById(R.id.tv_me_function);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mtv.setText(strings[position]);
        holder.miv.setBackgroundResource(images[position]);
        return convertView;
    }

    private static class ViewHolder {
        private TextView mtv ;
        private ImageView miv;
    }


}


