package com.example.hotel_personapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.example.hotel_personapp.BroadcastReceiver.SMSBroadcastReceiver;
import com.example.hotel_personapp.R;
import com.example.hotel_personapp.Utils.MyUtils;
import com.mob.MobSDK;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class MainActivity extends AppCompatActivity {

    Button mbtnlogin;
    TextView mtvyzm;
    EditText metusername;
    EditText metpassword;
    SMSBroadcastReceiver mSMSBroadcastReceiver;
    IntentFilter filter;

    private int i = 30;//计时器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SDKInitializer.initialize(getApplicationContext());
        initView();//找到组件
        mSMSBroadcastReceiver.setOnReceivedMessageListener(new SMSBroadcastReceiver.MessageListener() {

            public void OnReceived(StringBuilder message) {

                Log.e("tag", "1=" + message);
                String s = new MyUtils().getDynamicPassword(message);
                metpassword.setText((s));//截取4位验证码
                Log.d("验证码",""+ s);

            }
        });

        mbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = metusername.getText().toString();
                SMSSDK.submitVerificationCode("86", phoneNum, metpassword
                        .getText().toString());
                Intent intent = null;
                intent = new Intent(MainActivity.this,IndexActivity.class);
                startActivity(intent);
            }
        });
        mtvyzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = metusername.getText().toString();
                SMSSDK.getVerificationCode("86",phoneNum);// 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
                mtvyzm.setClickable(false);
                mtvyzm.setText("重新发送(" + i + ")");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for ( ; i  > 0; i--) {
                            handler.sendEmptyMessage(-9);
                            if (i <= 0 ){
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(-8);
                    }
                }).start();
            }
        });


        //如果 targetSdkVersion小于或等于22，可以忽略这一步，如果大于或等于23，需要做权限的动态申请：
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
        MobSDK.init(this, MobSDK.getAppkey(), MobSDK.getAppSecret()); // 启动短信验证sdk
        EventHandler eh = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                // TODO 此处不可直接处理UI线程，处理后续操作需传到主线程中操作
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };

        //注册一个事件回调监听，用于处理SMSSDK接口请求的结果

        SMSSDK.registerEventHandler(eh);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == -9) {
                mtvyzm.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                mtvyzm.setText("获取验证码");
                mtvyzm.setClickable(true);
                i = 30;
            } else {
                int i = msg.arg1;
                int i1 = msg.arg2;
                Object o = msg.obj;
                /*Log.d("3","Handler");
                Log.d("this","" + i);

                Log.d("this","" + i1);
                Log.d("this","" + o);*/

                if (i1 == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，返回MainActivity,然后提示
                    if (i == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        Toast.makeText(MainActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, IndexActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("userName", metusername.getText().toString().trim());
                        intent.putExtras(bundle);
                        startActivity(intent);

                    } else if (i == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE) {
                        Toast.makeText(MainActivity.this, "正在获取验证码", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.d("错误码为：","-------"+o);
                }
            }
        }
    };


    //找到组件
    private void initView(){
        mbtnlogin = findViewById(R.id.btn_login);
        mtvyzm = findViewById(R.id.tv_yzm);
        metusername = findViewById(R.id.et_username);
        metpassword =findViewById(R.id.et_password);
        filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        //filter.setPriority(1000);
        mSMSBroadcastReceiver = new SMSBroadcastReceiver();
        //注册广播接收
        registerReceiver(mSMSBroadcastReceiver, filter);
    }

}