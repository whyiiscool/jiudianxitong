package com.example.hotel_personapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hotel_personapp.R;

import java.util.List;

/*import soexample.umeng.com.fenlei.R;
import soexample.umeng.com.fenlei.bean.RightBean;*/

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyViewHolder>{
    private List list;
    private Context context;

    public RightAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RightAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate( context, R.layout.right_item,null );
        MyViewHolder viewHolder = new MyViewHolder( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RightAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText("易先生的货架");
/*      List<> listBeans = this.list.get( i ).getList();*/
        GridLayoutManager layoutManager = new GridLayoutManager( context,2 );
        myViewHolder.recyclerView.setLayoutManager( layoutManager );
        myViewHolder.recyclerView.setAdapter( new MyRightItemAdapter( context) );
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
        public MyViewHolder(@NonNull View itemView) {
            super( itemView );
            textView = itemView.findViewById( R.id.name );
            recyclerView = itemView.findViewById( R.id.recycleview);
        }
    }
}
