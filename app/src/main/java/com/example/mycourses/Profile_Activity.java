package com.example.mycourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class Profile_Activity extends AppCompatActivity {

    CircularImageView image;
    TextView name, email, tv1, tv2;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        toolbar = findViewById(R.id.mainToolbar);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            Picasso.get().load(signInAccount.getPhotoUrl()).into(image);
            name.setText(signInAccount.getDisplayName());
            email.setText(signInAccount.getEmail());
            tv1.setText(signInAccount.getGivenName());
            tv2.setText(signInAccount.getFamilyName());
        }
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