package com.robin.robin_wanandroid.util.flowView;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FlowAdapter extends RecyclerView.Adapter<FlowAdapter.FlowViewHolder> {

    Context context;
    List<String> data;
    RecyclerView recyclerView;

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public FlowAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public FlowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.flow_item,parent,false);

        return new FlowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowViewHolder holder, int position) {
        Logger.i("bindhodler "+position);
       holder.showText.setText(data.get(position));
       holder.showText.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
               linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
               Toast.makeText(context,"click "+position,Toast.LENGTH_LONG).show();
               if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
                   recyclerView.setLayoutManager(linearLayoutManager);
                   recyclerView.scrollToPosition(position);

               }
           }
       });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FlowViewHolder extends RecyclerView.ViewHolder{
        TextView showText;
        public FlowViewHolder(View itemView) {
            super(itemView);
            showText=itemView.findViewById(R.id.show_text);
        }
    }
}
