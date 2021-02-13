package com.example.mycourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mycourses.adapter.MessageAdapter;
import com.example.mycourses.adapter.SubAdapter;
import com.example.mycourses.model.messageModel;
import com.example.mycourses.model.sublist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Messages extends AppCompatActivity {
    Toolbar toolbar;
    FirebaseDatabase database;
    DatabaseReference messageRef;
    List<messageModel> MsgList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        toolbar = findViewById(R.id.mainToolbar);
        recyclerView = findViewById(R.id.msgRecyclerView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        messageRef = database.getReference("Messages");

        messageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot msgSnapshot: snapshot.getChildren()){
                        messageModel MsgInfo = msgSnapshot.getValue(messageModel.class);
                        MsgList.add(MsgInfo);
                        MessageAdapter messageAdapter = new MessageAdapter(MsgList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Messages.this));
                        messageAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(messageAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}