package com.example.avengerstictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    Animation logoanimation;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        logo=findViewById(R.id.gameLogo);
        logoanimation=AnimationUtils.loadAnimation(this, R.anim.logo_animation);

        logo.setAnimation(logoanimation);

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2500);
                } catch (Exception ex) {
                    Toast.makeText(SplashScreen.this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();

                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();


    }
}