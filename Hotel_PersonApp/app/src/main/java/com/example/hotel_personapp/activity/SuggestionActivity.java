package com.example.hotel_personapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hotel_personapp.R;

public class SuggestionActivity extends AppCompatActivity {

    private Button mbtngetsuggtions;
    private TextView mtvtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        init();
    }

    private void init(){
        mtvtype = findViewById(R.id.tv_suggestion_type);
        mbtngetsuggtions = findViewById(R.id.btn_getsuggestions);

        mtvtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{"APP使用","入住体验", "预定流程", "优惠促销","其他问题"};
                AlertDialog alertDialog = new AlertDialog.Builder(SuggestionActivity.this)
                        .setTitle("请选择反馈原因")
                        .setIcon(null)
                        .setItems(items, new DialogInterface.OnClickListener() {//添加列表
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mtvtype.setText(items[i]);
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
        mbtngetsuggtions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(SuggestionActivity.this)
                        .setTitle("提交成功")//标题
                        .setMessage("")
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Intent intent = new Intent(SuggestionActivity.this,IndexActivity.class);
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
