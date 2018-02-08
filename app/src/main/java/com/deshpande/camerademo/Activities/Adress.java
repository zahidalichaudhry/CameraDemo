package com.deshpande.camerademo.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.deshpande.camerademo.R;


public class Adress extends AppCompatActivity {
    Button proceed;
    String Id;
    ImageView johar,nazim,clift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_adress);
        proceed=(Button)findViewById(R.id.proceed);
        johar=(ImageView)findViewById(R.id.johar);
        nazim=(ImageView)findViewById(R.id.nazim);
        clift=(ImageView)findViewById(R.id.clift);

        Intent intent=getIntent();
         Id= intent.getStringExtra("ID");
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adress.this,Thank_You.class);
                intent.putExtra("ID",Id);
                startActivity(intent);
            }
        });
        johar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/place/Lahore+University+of+Management+Sciences/@31.470436,74.4085973,17z/data=!3m1!4b1!4m5!3m4!1s0x391908a610715697:0xa58f8e89d5669a0e!8m2!3d31.470436!4d74.410786");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        nazim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/place/24%C2%B056'26.2%22N+67%C2%B003'00.3%22E/@24.9421773,67.0488709,17z/data=!4m5!3m4!1s0x0:0x0!8m2!3d24.940605!4d67.050087");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        clift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/place/The+Residence+Tower/@24.8153213,67.0359774,14z/data=!4m5!3m4!1s0x0:0xbe7bad2cc28cec60!8m2!3d24.8292759!4d67.041663");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }
}
