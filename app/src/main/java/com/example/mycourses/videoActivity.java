package com.example.mycourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.mycourses.model.VideoActModel;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class videoActivity extends YouTubeBaseActivity {

    String url, chpkey, subName, chpName;
    ProgressBar progressBar;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference, percentRef;
    TextView title, description;
    VideoView videoPlayer;
    ImageView fullScreen;
    FrameLayout frameLayout;
    Toolbar toolbar;

    YouTubePlayerView mYoutubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        toolbar = findViewById(R.id.mainToolbar);
        title = findViewById(R.id.videoTitle);
        description = findViewById(R.id.videoDesc);
        frameLayout = findViewById(R.id.frameLayout);
        mYoutubePlayerView = findViewById(R.id.youtubePlay);

        url = getIntent().getStringExtra("VidUrl");
        chpkey = getIntent().getStringExtra("chp");
        subName = getIntent().getStringExtra("subName");
        chpName = getIntent().getStringExtra("chpName");


        mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("videos").child(subName);
        percentRef = firebaseDatabase.getReference("users").child(userId).child("enrolledCourses").child(subName);
        percentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    percentRef.child("cPercent").child(chpkey).setValue(chpkey);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(chpkey)){
                    VideoActModel videoActModel = snapshot.child(chpkey).getValue(VideoActModel.class);
                    title.setText(videoActModel.getTitle());
                    description.setText(videoActModel.getDescription());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        onInitializedListener = new YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(url);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };



    }

    @Override
    protected void onStart() {
        super.onStart();
        mYoutubePlayerView.initialize("AIzaSyAknkGc2lZvMvQfP2tW5XpbDxtkEL6G0HU", onInitializedListener);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}