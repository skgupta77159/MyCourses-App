package com.example.mycourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mycourses.adapter.EnrolledAdapter;
import com.example.mycourses.model.enrolledModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Profile_Activity extends AppCompatActivity {

    CircularImageView image;
    TextView name, email;
    Toolbar toolbar;
    RecyclerView mRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dataRef, chapterRef;
    FirebaseAuth mAuth;
    String CurrentUserId;
    List<enrolledModel> percentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        toolbar = findViewById(R.id.mainToolbar);
        mRecyclerView = findViewById(R.id.enrolled_recyclerview);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        CurrentUserId = mAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        dataRef = firebaseDatabase.getReference("users").child(CurrentUserId).child("enrolledCourses");
        chapterRef = firebaseDatabase.getReference("videos");

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            Picasso.get().load(signInAccount.getPhotoUrl()).into(image);
            name.setText(signInAccount.getDisplayName());
            email.setText(signInAccount.getEmail());
        }
        createPercentage();

    }

    private void createPercentage() {
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot chpSnapshot: snapshot.getChildren()){
                        String CPercent, CName, CUrl;
                        CPercent = String.valueOf(chpSnapshot.child("cPercent").getChildrenCount());
                        CName = chpSnapshot.child("cName").getValue().toString();
                        CUrl = chpSnapshot.child("cUrl").getValue().toString();
                        String chapterKey = chpSnapshot.getKey();
                        chapterRef.child(chapterKey).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String total = String.valueOf(snapshot.getChildrenCount());
                                enrolledModel percentInfo = new enrolledModel(CName, CPercent, CUrl, total);
                                percentList.add(percentInfo);
                                EnrolledAdapter enrolledAdapter = new EnrolledAdapter(percentList);
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                enrolledAdapter.notifyDataSetChanged();
                                mRecyclerView.setAdapter(enrolledAdapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


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