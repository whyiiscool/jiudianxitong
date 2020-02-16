package com.example.hotel_personapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hotel_personapp.R;

public class PersonMessageActivity extends AppCompatActivity {

    private ImageView mivmyname,mivmyid,mivmyphone,mivmyemail;
    private Button mbtnchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_message);
        init();
    }


    private void init(){
//        mivmyemail = findViewById(R.id.rl_myemail);
//        mivmyname = findViewById(R.id.rl_myname);
//        mivmyid = findViewById(R.id.rl_myid);
//        mivmyphone = findViewById(R.id.rl_myphone);
        mbtnchange = findViewById(R.id.btn_changemymes);
        setListeners();
    }

    private void setListeners() {
        OnClick onClick = new OnClick();
//        mivmyemail.setOnClickListener(onClick);
//        mivmyname.setOnClickListener(onClick);
//        mivmyid.setOnClickListener(onClick);
//        mivmyphone.setOnClickListener(onClick);
        mbtnchange.setOnClickListener(onClick);
    }

    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
//                case R.id.rl_myname:break;
//                case R.id.rl_myid:break;
//                case R.id.rl_myphone:break;
//                case R.id.rl_myemail:break;
                case R.id.btn_changemymes:
                    Intent intent = new Intent(PersonMessageActivity.this,Change_personActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
