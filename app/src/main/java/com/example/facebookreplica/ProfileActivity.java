package com.example.facebookreplica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    TextView textviewName,textviewGender,textviewdob,textviewemail;
    ImageView userImage;
    Button btnback,buttonlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        userImage = findViewById(R.id.ivUserImg);


        textviewName= findViewById(R.id.displayname);
        textviewGender= findViewById(R.id.displaygender);
        textviewdob= findViewById(R.id.displaydob);
        textviewemail= findViewById(R.id.displayemail);
        btnback = findViewById(R.id.btnback);
//        buttonlogout = findViewById(R.id.btnlogout);

        Intent intent = getIntent();
        String name= intent.getStringExtra("name");
        String gender= intent.getStringExtra("gender");
        String dob = intent.getStringExtra("date");
        String email= intent.getStringExtra("email");
        String ipath= intent.getStringExtra("path");
        String itoken= intent.getStringExtra("token");

        textviewName.setText(name);
        textviewGender.setText(gender);
        textviewdob.setText(dob);
        textviewemail.setText(email);

        Picasso.with(ProfileActivity.this)
                .load("http://10.0.2.2:4000/"+ipath)
                .into(userImage);



        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
