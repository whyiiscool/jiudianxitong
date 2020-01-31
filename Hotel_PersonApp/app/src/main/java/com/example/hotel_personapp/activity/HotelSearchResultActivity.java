package com.example.hotel_personapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.example.hotel_personapp.Adapter.HotelResultAdapter;
import com.example.hotel_personapp.R;

import java.util.ArrayList;

public class HotelSearchResultActivity extends AppCompatActivity {

    private ListView mlvresult;
    private TextView mtvhcity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search_result);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        ArrayList<PoiInfo> list = intent.getParcelableArrayListExtra("Result");
        String city = intent.getStringExtra("city");
        mlvresult = findViewById(R.id.lv_hotelres);
        mtvhcity = findViewById(R.id.tv_hcity);
        HotelResultAdapter adapter = new HotelResultAdapter(HotelSearchResultActivity.this,list);
        mlvresult.setAdapter(adapter);
        mtvhcity.setText(city);
    }
}
