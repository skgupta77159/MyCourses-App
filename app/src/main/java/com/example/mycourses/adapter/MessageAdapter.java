package com.example.mycourses.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycourses.R;
import com.example.mycourses.model.enrolledModel;
import com.example.mycourses.model.messageModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewholder> {
    List<messageModel> msgList = new ArrayList<>();

    public MessageAdapter(List<messageModel> msgList) {
        this.msgList = msgList;
    }

    public MessageAdapter() {
    }

    @NonNull
    @Override
    public MessageViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.messageview,parent,false);
        return new MessageAdapter.MessageViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewholder holder, int position) {
        String Uri = msgList.get(position).getIconUrl();
        String title = msgList.get(position).getMsgTitle();
        String message = msgList.get(position).getMessage();
        holder.setdata(Uri, title, message);
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    class MessageViewholder extends RecyclerView.ViewHolder{
        TextView Title, Message;
        ImageView imageview;

        public MessageViewholder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.MsgTitle);
            Message = itemView.findViewById(R.id.Message);
            imageview = itemView.findViewById(R.id.MsgIcon);
        }
        private void setdata(String uri, String title, String message){
            Title.setText(title);
            Message.setText(message);
            Picasso.get().load(uri).into(imageview);
        }
    }
}
