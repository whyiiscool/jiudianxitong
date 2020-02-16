package com.example.hotel_personapp.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.atuan.datepickerlibrary.CalendarUtil;
import com.atuan.datepickerlibrary.DatePopupWindow;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.hotel_personapp.Adapter.HorizontalListViewAdapter;
import com.example.hotel_personapp.R;
import com.example.hotel_personapp.View.HorizontalListView;
import com.example.hotel_personapp.activity.ExerciseActivity;
import com.example.hotel_personapp.activity.HotelSearchResultActivity;
import com.example.hotel_personapp.activity.MarketActivity;
import com.example.hotel_personapp.activity.SignActivity;
import com.example.hotel_personapp.loader.GlideImageLoader;
import com.lljjcoder.style.citylist.CityListSelectActivity;
import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HotelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private PoiSearch mPoiSearch = null;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
//BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口
//原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明

    private int startGroup = -1;
    private int endGroup = -1;
    private int startChild = -1;
    private int endChild = -1;

    //预订界面UI
    List<Integer> images = new ArrayList<Integer>();
    List<String> titles = new ArrayList<String>();
    List<Integer> images2 = new ArrayList<Integer>();
    List<String> titles2 = new ArrayList<String>();
    TextView mtvlivecity;
    TextView mtvlivetime;
    TextView mtvouttime;
    TextView mtvtimeres;
    TextView mtvmoney;
    TextView mtvdingwei;
    Button mbtnsea;
    
    private OnFragmentInteractionListener mListener;

    public HotelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HotelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HotelFragment newInstance(String param1, String param2) {
        HotelFragment fragment = new HotelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CityListLoader.getInstance().loadCityData(view.getContext());
        //注册监听函数
        initlist();
        inithotelview(view);
        initlist2();
        inithotelview2(view);
        initData(view);
        init(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hotel, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CityListSelectActivity.CITY_SELECT_RESULT_FRAG) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                Bundle bundle = data.getExtras();

                CityInfoBean cityInfoBean = (CityInfoBean) bundle.getParcelable("cityinfo");

                if (null == cityInfoBean) {
                    return;
                }

               mtvlivecity.setText(cityInfoBean.getName());
            }
        }
    }


    protected void initlist(){
        images.add(R.mipmap.activity1);
        images.add(R.mipmap.activity2);
        images.add(R.mipmap.activity3);
        images.add(R.mipmap.activity4);
        images.add(R.mipmap.activity5);
        titles.add("圣诞来临，酒店将举办圣诞大礼");
        titles.add("酒店将举办亲子活动");
        titles.add("酒店金秋特惠，欢迎大家入住");
        titles.add("学雷锋爱心助学活动");
        titles.add("迎鼠年，庆新春");
    }

    protected void inithotelview(View view){
        Banner banner = view.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    protected void initlist2(){
        images2.add(R.mipmap.guilin);
        images2.add(R.mipmap.taiyuan);
        images2.add(R.mipmap.changsha);
        titles2.add("桂林|美丽桂林 秀领天下");
        titles2.add("太原|龙城宝地 最美太原");
        titles2.add("长沙|鱼米之乡 闪耀星城");
    }


    protected void inithotelview2(View view){
        Banner banner2 = view.findViewById(R.id.banner2);
        //设置banner样式
        banner2.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner2.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner2.setImages(images2);
        //设置banner动画效果
        banner2.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner2.setBannerTitles(titles2);
        //设置自动轮播，默认为true
        banner2.isAutoPlay(true);
        //设置轮播时间
        banner2.setDelayTime(4500);
        //设置指示器位置（当banner模式中有指示器时）
        banner2.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner2.start();
    }

    private void initData(View view) {
        final String[] strings = new String[]{"每日签到", "优选商城", "酒店用餐", "健身活动"};
        final Integer[] images = new Integer[]{R.mipmap.qiandao, R.mipmap.shangcheng,
                R.mipmap.yongcan, R.mipmap.jiansheng};
        int screenWidth = view.getWidth();
        final HorizontalListViewAdapter hListViewAdapter = new HorizontalListViewAdapter(view.getContext().getApplicationContext(),screenWidth,strings,images);
        HorizontalListView hlv = view.findViewById(R.id.hlv_hotel);
        hlv.setAdapter(hListViewAdapter);
        hlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext().getApplicationContext(),"点击了"+strings[position].toString(),Toast.LENGTH_LONG).show();
                Intent intent = null;
                switch(position){
                    case 0: intent = new Intent(getView().getContext(),SignActivity.class);
                        break;
                    case 1: intent = new Intent(getView().getContext(),MarketActivity.class);
                        break;
                    case 2: intent = new Intent(getView().getContext(),MarketActivity.class);
                        break;
                    case 3: intent = new Intent(getView().getContext(),ExerciseActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }

    private void init(View view){
        mtvlivecity = view.findViewById(R.id.tv_livecity);
        mtvlivetime = view.findViewById(R.id.ruzhutime);
        mtvouttime = view.findViewById(R.id.lidiantime);
        mtvtimeres = view.findViewById(R.id.tv_timeresult);
        mtvmoney = view.findViewById(R.id.tv_moneyarea);
        mtvdingwei = view.findViewById(R.id.tv_dingwei);
        mbtnsea = view.findViewById(R.id.btn_searech);

        mLocationClient = new LocationClient(view.getContext().getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true
        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明

        mPoiSearch = PoiSearch.newInstance();

        setListeners();
    }

    private void setListeners() {
        mtvlivecity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(getView().getContext(),CityListSelectActivity.class);
                startActivityForResult(intent, CityListSelectActivity.CITY_SELECT_RESULT_FRAG);
            }
        });
        mtvlivetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCustomDatePicker(v, mtvtimeres);
            }
        });
        mtvouttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCustomDatePicker(v, mtvtimeres);
            }
        });
        mtvmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCostPicker(v);
            }
        });
        mtvdingwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationClient.start();
                //mLocationClient为第二步初始化过的LocationClient对象
                //调用LocationClient的start()方法，便可发起定位请求

            }
        });
        mbtnsea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = mtvlivecity.getText().toString().substring(0,mtvlivecity.getText().length()-1);
                Log.d("搜索城市为：",city);
                mPoiSearch.searchInCity(new PoiCitySearchOption()
                        .city(city) //必填
                        .keyword("酒店") //必填
                        .pageNum(10));

/*                mPoiSearch.destroy();*/
            }
        });
        mPoiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult == null
                        || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {// 没有找到检索结果
                    Toast.makeText(getContext(), "未找到结果",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR){// 检索结果正常返回
                    Log.d("状态码：",""+poiResult.error);
                    ArrayList<PoiInfo> list = new ArrayList<PoiInfo>();
                    for(int i=0;i<poiResult.getAllPoi().size();i++){
                        list.add(poiResult.getAllPoi().get(i));
                    }
                    if(list!=null){
                        Toast.makeText(getContext(), list.get(0).name, Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getActivity(), HotelSearchResultActivity.class);
                        intent.putParcelableArrayListExtra("Result",list);
                        intent.putExtra("city",mtvlivecity.getText().toString().substring(0,mtvlivecity.getText().length()-1));
                        startActivity(intent);
                    }else Toast.makeText(getContext(), "为空", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        });
    }


    private void createCustomDatePicker(View view, final TextView result) {
        new DatePopupWindow
                .Builder(getActivity(), Calendar.getInstance().getTime(), view)
                .setInitSelect(startGroup, startChild, endGroup, endChild)
                .setInitDay(false)
                .setDateOnClickListener(new DatePopupWindow.DateOnClickListener() {
                    @Override
                    public void getDate(String startDate, String endDate, int startGroupPosition, int startChildPosition, int endGroupPosition, int endChildPosition) {
                        startGroup = startGroupPosition;
                        startChild = startChildPosition;
                        endGroup = endGroupPosition;
                        endChild = endChildPosition;
                        String mStartTime = CalendarUtil.FormatDateYMD(startDate);
                        String mEndTime = CalendarUtil.FormatDateYMD(endDate);
                        mStartTime = mStartTime.substring(5);
                        mEndTime = mEndTime.substring(5);
                        int daysOffset = Integer.parseInt(CalendarUtil.getTwoDay(endDate, startDate));
                        if (daysOffset >= 0) {
                            result.setText("共" + daysOffset + "晚");
                        }
                        mtvlivetime.setText(mStartTime);
                        mtvouttime.setText(mEndTime);
                    }
                }).builder();
    }

    private void createCostPicker(View view){
        final String[] items = new String[]{"不限","¥0—500", "¥500—1000", "¥1000—2000"};
        AlertDialog alertDialog = new AlertDialog.Builder(view.getContext())
                .setTitle("请选择心仪的价格")
                .setIcon(null)
                .setItems(items, new DialogInterface.OnClickListener() {//添加列表
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mtvmoney.setText(items[i]);
                    }
                })
                .create();
        alertDialog.show();
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

/*            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份*/
            String city = location.getCity();    //获取城市
/*            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            int errorCode = location.getLocType();*/
            Log.d(location.getAddrStr(),"................"+location.getLatitude());
//          Log.d(location.getAddrStr(),"................"+errorCode);
            mtvlivecity.setText(city);
            mLocationClient.stop();
        }
    }

}


