package com.ika.a32;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class Beranda extends AppCompatActivity {
String id_user,nama_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Beranda.this);
        Boolean Registered = sharedPref.getBoolean("Registered", false);
        if (!Registered) {
            finish();
        } else {
            id_user = sharedPref.getString("id_user", "");
            nama_user = sharedPref.getString("nama_user", "");
        }

        TextView txtnama=(TextView)findViewById(R.id.txtnama);
        txtnama.setText(nama_user);


        CardView cameraCard=(CardView)findViewById(R.id.cameraCard);
        cameraCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Beranda.this,Deteksi.class);
                startActivity(i);
            }
        });

        CardView infoCard=(CardView)findViewById(R.id.infoCard);
        infoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Beranda.this,infotoko.class);
                startActivity(i);
            }
        });

        CardView profileCard=(CardView)findViewById(R.id.profileCard);
        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Beranda.this,Profil.class);
                i.putExtra("pk",id_user);
                startActivity(i);

            }
        });

        CardView deviceCard=(CardView)findViewById(R.id.deviceCard);
        deviceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Beranda.this,Device.class);
                startActivity(i);
            }
        });

    }
}
