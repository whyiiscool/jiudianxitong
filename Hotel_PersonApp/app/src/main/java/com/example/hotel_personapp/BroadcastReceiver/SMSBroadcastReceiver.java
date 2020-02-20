package com.example.hotel_personapp.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSBroadcastReceiver extends BroadcastReceiver {


    private static MessageListener mMessageListener;

    public SMSBroadcastReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder content = new StringBuilder();//用于存储短信内容
        String sender = null;//存储短信发送方手机号
        Bundle bundle = intent.getExtras();//通过getExtras()方法获取短信内容
        String format = intent.getStringExtra("format");
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");//根据pdus关键字获取短信字节数组，数组内的每个元素都是一条短信
            for (Object object : pdus) {
                SmsMessage message=SmsMessage.createFromPdu((byte[])object,format);//将字节数组转化为Message对象
                sender = message.getOriginatingAddress();//获取短信手机号
                content.append(message.getMessageBody());//获取短信内容
                mMessageListener.OnReceived(content);
            }
        }
    }
    public interface  MessageListener{
        void OnReceived(StringBuilder message);
    }
    public void setOnReceivedMessageListener(MessageListener messageListener) {
        this.mMessageListener = messageListener;
    }

}
