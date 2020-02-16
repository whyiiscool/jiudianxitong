package com.example.hotel_personapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hotel_personapp.Fragment.MeFragment;
import com.example.hotel_personapp.R;

public class ChangePassActivity extends AppCompatActivity {

    private Button mbtnchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        init();
    }

    private void init(){
        mbtnchange = findViewById(R.id.btn_change);
        mbtnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(ChangePassActivity.this)
                        .setTitle("  修改成功")//标题
                        .setMessage("")
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Intent intent = new Intent(ChangePassActivity.this,IndexActivity.class);
                                intent.putExtra("waitPayFlag","4");
                                startActivity(intent);
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
    }
}
