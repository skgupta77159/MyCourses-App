package com.example.mycourses;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycourses.model.post;

import java.util.List;

public class EnrolledAdapter extends RecyclerView.Adapter<EnrolledAdapter.EnrolledViewholder> {

    public EnrolledAdapter(){
    }
    public EnrolledAdapter(List<post> postList){

    }

    @NonNull
    @Override
    public EnrolledViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EnrolledViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class EnrolledViewholder extends RecyclerView.ViewHolder{

        public EnrolledViewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
