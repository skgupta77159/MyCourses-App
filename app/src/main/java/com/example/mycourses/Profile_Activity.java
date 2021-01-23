package com.example.mycourses;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

public class Profile_Activity extends AppCompatActivity {

    ImageView image;
    TextView name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            Picasso.get().load(signInAccount.getPhotoUrl()).into(image);
            name.setText(signInAccount.getDisplayName());
            email.setText(signInAccount.getEmail());
        }
    }
}