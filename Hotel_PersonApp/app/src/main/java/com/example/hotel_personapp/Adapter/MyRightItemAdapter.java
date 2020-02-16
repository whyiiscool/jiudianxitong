package com.example.hotel_personapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotel_personapp.R;

import java.util.ArrayList;
import java.util.List;

//import com.bumptech.glide.Glide;

/*import soexample.umeng.com.fenlei01.R;
import soexample.umeng.com.fenlei01.bean.ShopRightBean;*/

public class MyRightItemAdapter extends RecyclerView.Adapter<MyRightItemAdapter.MyViewHolder> {
    private Context context;
    private List rightbean = new ArrayList<>();

    public MyRightItemAdapter(Context context) {
        this.context = context;
//        this.rightbean = rightbean;
    }

    @NonNull
    @Override
    public MyRightItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate( context, R.layout.right_item_child, null );
        MyViewHolder myViewHolder = new MyViewHolder( view );
        myViewHolder.imageView = view.findViewById( R.id.image );
        myViewHolder.textView = view.findViewById( R.id.titley);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRightItemAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText( "易先生" );
        myViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_background));
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
        }

        ImageView imageView;
        TextView textView;
    }
}


