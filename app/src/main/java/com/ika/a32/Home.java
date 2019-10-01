package com.ika.a32;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView cameraCard=(CardView)findViewById(R.id.cameraCard);
        cameraCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,Deteksi.class);
                startActivity(i);
            }
        });

        CardView infoCard=(CardView)findViewById(R.id.infoCard);
        infoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,Info.class);
                startActivity(i);
            }
        });

        CardView profileCard=(CardView)findViewById(R.id.profileCard);
        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,Profile.class);
                startActivity(i);

            }
        });

        CardView deviceCard=(CardView)findViewById(R.id.deviceCard);
        deviceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home.this,Device.class);
                startActivity(i);

            }
        });

    }
}
