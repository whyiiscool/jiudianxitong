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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel_personapp.Adapter.HorizontalListViewAdapter;
import com.example.hotel_personapp.R;
import com.example.hotel_personapp.View.HorizontalListView;
import com.example.hotel_personapp.activity.ChangePassActivity;
import com.example.hotel_personapp.activity.Hotel_Message_Activity;
import com.example.hotel_personapp.activity.MyCommentActivity;
import com.example.hotel_personapp.activity.MyCouponActivity;
import com.example.hotel_personapp.activity.PersonMessageActivity;
import com.example.hotel_personapp.activity.SuggestionActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView mtvchange,mtvsuggestion,mtvcall,mtvhotel,mtvperson,mtvoutlogin;

    private OnFragmentInteractionListener mListener;

    public MeFragment() {
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
    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
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
        init(view);
        initData(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false);
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

    private void init(View view){
        mtvchange = view.findViewById(R.id.tv_changepass);
        mtvcall = view.findViewById(R.id.tv_call);
        mtvsuggestion = view.findViewById(R.id.tv_suggestion);
        mtvhotel = view.findViewById(R.id.tv_hotelmessage);
        mtvperson = view.findViewById(R.id.tv_personelmessage);
        mtvoutlogin = view.findViewById(R.id.tv_outlogin);

        mtvchange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getView().getContext(),ChangePassActivity.class);
                startActivity(intent);
            }
        });

        mtvsuggestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getView().getContext(),SuggestionActivity.class);
                startActivity(intent);
            }
        });

        mtvcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+"88888888888"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mtvhotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getView().getContext(),Hotel_Message_Activity.class);
                startActivity(intent);
            }
        });

        mtvperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getView().getContext(),PersonMessageActivity.class);
                startActivity(intent);
            }
        });

        mtvoutlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("")//标题
                        .setMessage("确定要退出当前登录状态？")
                        .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
    }

    private void initData(View view) {
        final String[] strings = new String[]{"我的优惠券", "我的点评", "常住人", "我的奖品", "我的特权", "会员权益"};
        final Integer[] images = new Integer[]{R.mipmap.youhui, R.mipmap.dianping,
                R.mipmap.zhuren, R.mipmap.jiangping, R.mipmap.tequan, R.mipmap.huiyuan};
        int screenWidth = view.getWidth();
        final HorizontalListViewAdapter hListViewAdapter = new HorizontalListViewAdapter(view.getContext().getApplicationContext(),screenWidth,strings,images);
        HorizontalListView hlv = view.findViewById(R.id.hlv_me);
        hlv.setAdapter(hListViewAdapter);
        hlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext().getApplicationContext(),"点击了"+strings[position].toString(),Toast.LENGTH_LONG).show();
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent(view.getContext(),MyCouponActivity.class);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(),MyCommentActivity.class);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }startActivity(intent);
            }
        });
    }

}
