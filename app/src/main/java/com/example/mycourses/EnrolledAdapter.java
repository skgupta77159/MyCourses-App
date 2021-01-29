package com.example.mycourses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycourses.adapter.PostAdapter;
import com.example.mycourses.model.post;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EnrolledAdapter extends RecyclerView.Adapter<EnrolledAdapter.EnrolledViewholder> {

    List<enrolledModel> percentList = new ArrayList<>();
    public EnrolledAdapter(List<enrolledModel> percentList) {
        this.percentList = percentList;
    }
    public EnrolledAdapter() {
    }

    @NonNull
    @Override
    public EnrolledViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.enrolled_cardview,parent,false);
        return new EnrolledAdapter.EnrolledViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnrolledViewholder holder, int position) {
        String cName = percentList.get(position).getcName();
        String cPercent = percentList.get(position).getcPercent();
        String cUrl = percentList.get(position).getcUrl();
        holder.setdata(cName, cPercent, cUrl);
    }

    @Override
    public int getItemCount() {
        return percentList.size();
    }

    class EnrolledViewholder extends RecyclerView.ViewHolder{
        TextView chpName, chpPercent;
        ImageView imageView;

        public EnrolledViewholder(@NonNull View itemView) {
            super(itemView);
            chpName = itemView.findViewById(R.id.courseName);
            chpPercent = itemView.findViewById(R.id.coursePercent);
            imageView = itemView.findViewById(R.id.courseImg);
        }
        private void setdata(String name, String percent, String url){
            chpName.setText(name);
            chpPercent.setText(percent);
            Picasso.get().load(url).into(imageView);
        }
    }

}
