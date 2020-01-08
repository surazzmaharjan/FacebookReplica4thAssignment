package com.example.facebookreplica.Adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookreplica.Model.TimelineData;
import com.example.facebookreplica.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FbRecyclerAdapater extends RecyclerView.Adapter<FbRecyclerAdapater.MyHolder> {

    List<TimelineData> fbtimelinelist = new ArrayList<>();
    Context context;

    public FbRecyclerAdapater(List<TimelineData> fbtimelinelist, Context context) {
        this.fbtimelinelist = fbtimelinelist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fb_repeat_layout,parent,false);
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final  TimelineData timelineData= fbtimelinelist.get(position);

        holder.fullname.setText(timelineData.getFullname());
        holder.status.setText(timelineData.getStatus());
        holder.time.setText(timelineData.getTime());


            Picasso.with(context)
                    .load("http://10.0.2.2:4000/uploads/" + timelineData.getTimelineimage())
                    .into(holder.circleimageview);



    }

    @Override
    public int getItemCount() {
        return fbtimelinelist.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        CircleImageView circleimageview;
        TextView fullname, status, time;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            circleimageview = itemView.findViewById(R.id.imgView_proPic);
            fullname = itemView.findViewById(R.id.tv_name);
            status = itemView.findViewById(R.id.tv_status);
            time = itemView.findViewById(R.id.tv_time);

        }
    }
}
