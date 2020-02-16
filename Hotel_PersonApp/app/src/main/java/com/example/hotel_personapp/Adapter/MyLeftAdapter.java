package com.example.hotel_personapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hotel_personapp.R;
import com.example.hotel_personapp.activity.MarketActivity;

import java.util.ArrayList;
import java.util.List;

public class MyLeftAdapter extends RecyclerView.Adapter<MyLeftAdapter.MyViewHolder> {

   private Context context;
   private List leftbean = new ArrayList<Object>();
   public MyLeftAdapter(MarketActivity mainActivity, List leftbean){
       context = mainActivity;
       this.leftbean = leftbean;
   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.left_item,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.shopName = view.findViewById(R.id.left_name);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
       myViewHolder.shopName.setText("易先生");
       myViewHolder.shopName.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listener.onItemClickListener(i);
           }
       });
    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        TextView shopName;
    }

    private ItemClickListener listener;
    public void setItemClick(ItemClickListener listener){
        this.listener = listener;
    }
    public interface ItemClickListener{
        void onItemClickListener(int position);
    }
}
