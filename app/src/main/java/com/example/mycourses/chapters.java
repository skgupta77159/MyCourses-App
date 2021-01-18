package com.example.mycourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class chapters extends AppCompatActivity implements RecyclerViewClickInterface{

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference dataRef;
    List<post> PostList = new ArrayList<>();
    TextView subname, subwel;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        String subName = getIntent().getStringExtra("subName");
        String subImgUrl = getIntent().getStringExtra("subImgUrl");

        subname = findViewById(R.id.subName);
        subwel = findViewById(R.id.subWel);
        imageView = findViewById(R.id.subImg);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        dataRef = database.getReference("videos").child(subName);

        subname.setText(subName.toUpperCase());
        subwel.setText("Welcome to " + subName.toUpperCase() + " course");
        Picasso.get().load(subImgUrl).into(imageView);

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot chpSnapshot: snapshot.getChildren()){
                        post Post = chpSnapshot.getValue(post.class);
                        PostList.add(Post);
                        PostAdapter postsAdapter = new PostAdapter(PostList, chapters.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(chapters.this));
                        postsAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(postsAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent MainIntent = new Intent(chapters.this, videoActivity.class);
        MainIntent.putExtra("VidUrl",PostList.get(position).getVidurl());
        startActivity(MainIntent);
    }

    @Override
    public void onLongItemClick(int position) {

    }
}