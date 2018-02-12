package com.deshpande.camerademo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.deshpande.camerademo.R;


public class Splash extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//        //Remove notification bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
         setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);

        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
//        Thread myThread = new Thread(){
//            @Override
//            public void run() {
//                try {
//                    sleep(3000);
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//                    doWork();
//                    finish();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        myThread.start();
    }

    private void doWork() {
        for (int progress=0; progress<100; progress+=20) {
            try {
                Thread.sleep(500);
                progressBar.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
                //Timber.e(e.getMessage());
            }
        }
    }
    private void startApp() {
        Intent intent = new Intent(Splash.this, MainActivity.class);
        startActivity(intent);
    }
}
