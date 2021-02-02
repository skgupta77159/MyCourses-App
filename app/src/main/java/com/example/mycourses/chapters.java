package com.example.mycourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mycourses.adapter.PostAdapter;
import com.example.mycourses.model.post;
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
    TextView sName, sWel;
    ImageView imageView;
    String subName, subImgUrl;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        try {

            sName = findViewById(R.id.subName);
            sWel = findViewById(R.id.subWel);
            imageView = findViewById(R.id.subImg);
            toolbar = findViewById(R.id.mainToolbar);
            recyclerView = findViewById(R.id.recycler_view);

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            subName = getIntent().getStringExtra("subName");
            subImgUrl = getIntent().getStringExtra("subImgUrl");

            database = FirebaseDatabase.getInstance();
            dataRef = database.getReference("videos").child(subName);

            sName.setText(subName.toUpperCase());
            sWel.setText("Welcome to " + subName.toUpperCase() + " course");
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

        }catch (Exception e){
            Intent MainIntent = new Intent(chapters.this, MainActivity.class);
            startActivity(MainIntent);
            finish();
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent MainIntent = new Intent(chapters.this, videoActivity.class);
        MainIntent.putExtra("VidUrl",PostList.get(position).getVidurl());
        MainIntent.putExtra("chp",PostList.get(position).getKey());
        MainIntent.putExtra("subName", subName);
        MainIntent.putExtra("chpName", PostList.get(position).getChpname());
        startActivity(MainIntent);
    }

    @Override
    public void onLongItemClick(int position) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}