package com.example.mycourses.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycourses.R;
import com.example.mycourses.RecyclerViewClickInterface;
import com.example.mycourses.model.post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewholder> {

    List<post> postList = new ArrayList<>();
    RecyclerViewClickInterface recyclerViewClickInterface;
    public PostAdapter() {
    }

    public PostAdapter(List<post> postList, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.postList = postList;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public PostViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.video_view,parent,false);
        return new PostViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewholder holder, int position) {
        String thumb = postList.get(position).getThumbnail();
        String time = postList.get(position).getTime();
        String title = postList.get(position).getTitle();
        String chpName = postList.get(position).getChpname();
        holder.setdata(title,time,thumb,chpName);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class PostViewholder extends RecyclerView.ViewHolder {

        TextView text, title, chapterNo;
        ImageView image;

        public PostViewholder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.timing);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.thumbnail);
            chapterNo = itemView.findViewById(R.id.chapterNo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }

        private void setdata(String Title, String Time, String Image, String chpname){
            text.setText(Time);
            title.setText(Title);
            chapterNo.setText(chpname);
            Picasso.get().load(Image).into(image);

        }
    }
}
