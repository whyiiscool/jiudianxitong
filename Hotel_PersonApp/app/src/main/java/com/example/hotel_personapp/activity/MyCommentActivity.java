package com.example.hotel_personapp.activity;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hotel_personapp.Adapter.TitleFragmentPagerAdapter;
import com.example.hotel_personapp.Fragment.BlankFragment;
import com.example.hotel_personapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyCommentActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener{

    TabLayout tabLayout;
    ViewPager viewpager;

    List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        init();
    }

    private void init(){
        tabLayout = findViewById(R.id.tab_comment);
        viewpager = findViewById(R.id.vp_comment);
        fragments = new ArrayList<>();
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());

        TitleFragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(getSupportFragmentManager(), fragments, new String[]{ "已点评", "点评"});
        viewpager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
