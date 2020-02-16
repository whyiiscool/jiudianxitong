package com.example.hotel_personapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hotel_personapp.Adapter.MyLeftAdapter;
import com.example.hotel_personapp.Adapter.RightAdapter;
import com.example.hotel_personapp.R;

import java.util.ArrayList;
import java.util.List;

public class MarketActivity extends AppCompatActivity {

    private RecyclerView recy_left,recy_right;
    private List leftbean = new ArrayList();
    private MyLeftAdapter myLeftAdapter;
    private RightAdapter myRightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        init();
    }

    private void init() {
        recy_left = findViewById(R.id.recy_left);
        recy_right = findViewById(R.id.recy_right);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recy_left.setLayoutManager(linearLayoutManager1);
        myLeftAdapter = new MyLeftAdapter(this,leftbean);
        recy_left.setAdapter(myLeftAdapter);
        myLeftAdapter.setItemClick(new MyLeftAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                int id = 1;
//              doHttpRight(id);
            }
        });

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recy_right.setLayoutManager(linearLayoutManager2);

        myRightAdapter = new RightAdapter(leftbean,this);
        recy_right.setAdapter(myRightAdapter);
    }

    /*    private void doHttpLeft() {
        new OkHttpHelper().get( "http://www.zhaoapi.cn/product/getCatagory" ).result( new OkHttpHelper.OkHttpHelperListener() {
            @Override
            public void Success(String data) {
                ShopLeftBean shopLeftBean = new Gson().fromJson( data, ShopLeftBean.class );
                leftbean = shopLeftBean.getData();
                myLeftAdapter.setList( leftbean );
            }

            @Override
            public void Error() {

            }
        } );
        doHttpRight( cid );
    }

    private void doHttpRight(int cid) {
        new OkHttpHelper().get( "http://www.zhaoapi.cn/product/getProductCatagory?cid=" + cid ).result( new OkHttpHelper.OkHttpHelperListener() {
            @Override
            public void Success(String data) {
                ShopRightBean shopRightBean = new Gson().fromJson( data, ShopRightBean.class );
                List<ShopRightBean.DataBean> rightbean = shopRightBean.getData();
                MyRightAdapter rightAdapter = new MyRightAdapter( MainActivity.this, rightbean );
                recy_right.setAdapter( rightAdapter );
            }

            @Override
            public void Error() {

            }
        } );
    }*/
}
