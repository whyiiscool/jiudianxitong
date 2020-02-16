package com.example.hotel_personapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hotel_personapp.R;

public class ExerciseActivity extends AppCompatActivity {

    private TextView mtvmachine;
    private TextView mtvplan;
    private TextView mtvfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        init();
    }

    private void init() {
        mtvmachine = findViewById(R.id.tv_exemachine);
        mtvplan = findViewById(R.id.tv_exeplan);
        mtvfood = findViewById(R.id.tv_exefood);
        setCilck();
    }

    private void setCilck() {
        OnClick onClick = new OnClick();
        mtvmachine.setOnClickListener(onClick);
        mtvplan.setOnClickListener(onClick);
        mtvfood.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.tv_exemachine:
                    intent = new Intent(ExerciseActivity.this,ExeMachineActivity.class);
                    break;
                case R.id.tv_exeplan:
                    intent = new Intent(ExerciseActivity.this,ExePlanActivity.class);
                    break;
                case R.id.tv_exefood:
                    intent = new Intent(ExerciseActivity.this,ExeFoodActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
