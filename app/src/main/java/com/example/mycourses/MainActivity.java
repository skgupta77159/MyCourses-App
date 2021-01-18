package com.example.mycourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface {

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference dataRef;
    List<sublist> SubList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.subRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        dataRef = database.getReference("courses");

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot chpSnapshot: snapshot.getChildren()){
                        sublist subInfo = chpSnapshot.getValue(sublist.class);
                        SubList.add(subInfo);
                        SubAdapter subAdapter = new SubAdapter(SubList, MainActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        subAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(subAdapter);
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
        Intent MainIntent = new Intent(MainActivity.this, chapters.class);
        MainIntent.putExtra("subName",SubList.get(position).getSubname());
        MainIntent.putExtra("subImgUrl",SubList.get(position).getSuburl());
        startActivity(MainIntent);
    }

    @Override
    public void onLongItemClick(int position) {

    }
}