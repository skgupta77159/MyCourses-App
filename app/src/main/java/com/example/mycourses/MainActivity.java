package com.example.mycourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycourses.adapter.SubAdapter;
import com.example.mycourses.model.sublist;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
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

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface {

    RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference dataRef, userRef, versionRef;
    private Toolbar toolbar;
    List<sublist> SubList = new ArrayList<>();
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CircularImageView header_image, header_image_toolbar;
    private TextView header_username, naming_status, header_email;
    ImageView imgView;
    String CurrentUserId;
    String oVersion = "1.0";
    String newVersion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigation_view);
        recyclerView = findViewById(R.id.subRecyclerView);
        toolbar = findViewById(R.id.mainToolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        header_image = navView.findViewById(R.id.header_image);
        header_username = navView.findViewById(R.id.header_username);
        naming_status = findViewById(R.id.naming_status);
        header_image_toolbar = findViewById(R.id.header_image_toolbar);
        header_email = navView.findViewById(R.id.header_email);
        imgView = findViewById(R.id.toolbarMsg);

        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2,GridLayoutManager.VERTICAL,false);

        mAuth = FirebaseAuth.getInstance();
        CurrentUserId = mAuth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        dataRef = database.getReference("courses");
        userRef = database.getReference("users").child(CurrentUserId);
        versionRef = database.getReference("Version");

        versionRef.child("version").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    newVersion = snapshot.getValue().toString();
                    if(!oVersion.equals(newVersion)){
                        showDownloadDialogBox();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Picasso.get().load(snapshot.child("profileUrl").getValue().toString()).into(header_image);
                    Picasso.get().load(snapshot.child("profileUrl").getValue().toString()).into(header_image_toolbar);
                    header_username.setText(snapshot.child("fullName").getValue().toString());
                    header_email.setText(snapshot.child("email").getValue().toString());
                    naming_status.setText("Hey " + snapshot.child("firstName").getValue().toString() +  " What do you want to learn today!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       /* GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            Picasso.get().load(signInAccount.getPhotoUrl()).into(header_image);
            Picasso.get().load(signInAccount.getPhotoUrl()).into(header_image_toolbar);
            header_username.setText(signInAccount.getDisplayName());
            header_email.setText(signInAccount.getEmail());
            naming_status.setText("Hey " + signInAccount.getGivenName() + " What do you want to learn today!");
        }*/

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot chpSnapshot: snapshot.getChildren()){
                        sublist subInfo = chpSnapshot.getValue(sublist.class);
                        SubList.add(subInfo);
                        SubAdapter subAdapter = new SubAdapter(SubList, MainActivity.this);
                        recyclerView.setLayoutManager(gridLayoutManager);
                        subAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(subAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        header_image_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profile_Activity.class);
                startActivity(intent);
            }
        });

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, Messages.class);
                startActivity(intent3);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                UserMenuSelectorItem(menuItem);
                return false;
            }

            private void UserMenuSelectorItem(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_profile:
                        Intent intent = new Intent(MainActivity.this, Profile_Activity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "You are in home ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_messages:
                        Intent intent3 = new Intent(MainActivity.this, Messages.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_settings:
                        Intent intent2 = new Intent(MainActivity.this, Setting.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_logout:
                        GoogleSignInOptions gso = new GoogleSignInOptions.
                                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                                build();
                        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(MainActivity.this,gso);
                        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    FirebaseAuth.getInstance().signOut();
                                    Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK); // clear previous task (optional)
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                        break;
                }
            }
        });


    }

    @Override
    public void onItemClick(int position) {

        userRef.child("enrolledCourses").child(SubList.get(position).getSubname()).child("cName").setValue(SubList.get(position).getSubname());
        userRef.child("enrolledCourses").child(SubList.get(position).getSubname()).child("cUrl").setValue(SubList.get(position).getSuburl()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent MainIntent = new Intent(MainActivity.this, chapters.class);
                    MainIntent.putExtra("subName", SubList.get(position).getSubname());
                    MainIntent.putExtra("subImgUrl", SubList.get(position).getSuburl());
                    startActivity(MainIntent);
                }
            }
        });
    }


    private void showDownloadDialogBox() {

        final AlertDialog dialog=new AlertDialog.Builder(MainActivity.this).create();
        View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.dialogpopup,null);
        Button cancelBtn;
        cancelBtn=view.findViewById(R.id.canclebtn);
        dialog.setCancelable(false);
        dialog.setView(view);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialog.show();

    }

    @Override
    public void onLongItemClick(int position) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}