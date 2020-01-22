package com.example.hotel_personapp.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hotel_personapp.Adapter.BottomViewAdapter;
import com.example.hotel_personapp.Adapter.TitleFragmentPagerAdapter;
import com.example.hotel_personapp.Fragment.ActivityFragment;
import com.example.hotel_personapp.Fragment.BlankFragment;
import com.example.hotel_personapp.Fragment.HotelFragment;
import com.example.hotel_personapp.Fragment.MeFragment;
import com.example.hotel_personapp.Fragment.RecordFragment;
import com.example.hotel_personapp.R;
import com.example.hotel_personapp.loader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends AppCompatActivity implements HotelFragment.OnFragmentInteractionListener
        ,RecordFragment.OnFragmentInteractionListener ,ActivityFragment.OnFragmentInteractionListener
        ,MeFragment.OnFragmentInteractionListener,BlankFragment.OnFragmentInteractionListener{

    BottomNavigationView navigation;
    ViewPager viewPager;

    MenuItem menuItem;
    List<Fragment> fragments = new ArrayList<>();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_hotel:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_record:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_activity:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_me:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        viewPager = findViewById(R.id.viewpager);
        setNavigation();
        viewPager.setCurrentItem(0);
    }

    private void setNavigation(){
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        fragments.add(HotelFragment.newInstance("",""));
        fragments.add(RecordFragment.newInstance("",""));
        fragments.add(ActivityFragment.newInstance("",""));
        fragments.add(MeFragment.newInstance("",""));
        viewPager = findViewById(R.id.viewpager);
        BottomViewAdapter adapter = new BottomViewAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
