package com.example.mycourses.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycourses.R;
import com.example.mycourses.RecyclerViewClickInterface;
import com.example.mycourses.model.sublist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.SubViewholder>{

    List<sublist> subList = new ArrayList<>();
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public SubAdapter(){
    }
    public SubAdapter(List<sublist> subList, RecyclerViewClickInterface recyclerViewClickInterface){
        this.subList = subList;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public SubViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.cardview,parent,false);
        return new SubAdapter.SubViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewholder holder, int position) {
        String subName = subList.get(position).getSubname().toUpperCase();
        String imgUrl = subList.get(position).getSuburl();
        holder.setdata(subName,imgUrl);

    }

    @Override
    public int getItemCount() {
        return subList.size();
    }

    class SubViewholder extends RecyclerView.ViewHolder{

        TextView subject;
        ImageView subjectImage;
        Button enroll_button;
        public SubViewholder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.courseName);
            subjectImage = itemView.findViewById(R.id.courseImg);
            enroll_button = itemView.findViewById(R.id.enroll_button);

            enroll_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
        private void setdata(String Name, String Image){
            subject.setText(Name);
            Picasso.get().load(Image).into(subjectImage);
        }
    }
}
