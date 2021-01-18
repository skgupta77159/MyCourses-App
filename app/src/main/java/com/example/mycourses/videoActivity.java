package com.example.mycourses;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;


public class videoActivity extends AppCompatActivity {

    String url;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        url = getIntent().getStringExtra("VidUrl");
        progressBar = findViewById(R.id.progressBar);
       // TextView title = findViewById(R.id.videoTitle);
       // TextView description = findViewById(R.id.videoDesc);
        VideoView videoPlayer = findViewById(R.id.videoView);



        Uri videoUrl = Uri.parse(url);
        videoPlayer.setVideoURI(videoUrl);
        MediaController mc = new MediaController(this);
        videoPlayer.setMediaController(mc);

        videoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoPlayer.start();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}