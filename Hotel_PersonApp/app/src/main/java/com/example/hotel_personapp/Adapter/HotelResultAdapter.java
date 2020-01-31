package com.example.hotel_personapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.example.hotel_personapp.R;

import java.util.ArrayList;

public class HotelResultAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<PoiInfo> list;

    public HotelResultAdapter(Context applicationContext, ArrayList<PoiInfo> list){
        this.context = applicationContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.hotel_result_listview_item, null);
            holder = new ViewHolder();
            holder.mtvhname = convertView.findViewById(R.id.tv_hname);
            holder.mtvhaddress = convertView.findViewById(R.id.tv_haddress);
            holder.mtvhphone = convertView.findViewById(R.id.tv_hpone);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mtvhname.setText(list.get(position).name);
        holder.mtvhaddress.setText(list.get(position).address);
        holder.mtvhphone.setText(list.get(position).phoneNum);
        return convertView;
    }

    private static class ViewHolder {
        private TextView mtvhname ;
        private TextView mtvhaddress ;
        private TextView mtvhphone ;

    }
}
