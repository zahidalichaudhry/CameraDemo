package com.deshpande.camerademo.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.deshpande.camerademo.Config;
import com.deshpande.camerademo.R;

public class Thank_You extends AppCompatActivity {
    TextView code;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_thank__you);
        Intent intent=getIntent();
       String Id= intent.getStringExtra("ID");
       code=(TextView)findViewById(R.id.code);
       code.setText(Id);
        //Getting out sharedpreferences
        SharedPreferences preferences = getSharedPreferences(Config.SHARE_PREFRANCE_NAME, Context.MODE_PRIVATE);
        //Getting editor
        SharedPreferences.Editor editor = preferences.edit();
        //Putting blank value to email
        editor.putString(Config.DIS, "");
        //Saving the sharedpreferences
        editor.clear();
        editor.apply();
    }
}
