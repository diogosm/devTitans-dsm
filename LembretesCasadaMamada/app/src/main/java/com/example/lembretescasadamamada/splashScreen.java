package com.example.lembretescasadamamada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

public class splashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ImageView videoView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        videoView = findViewById(R.id.imageView2);
        Glide.with(this)
                .load(R.raw.loading)
                .into(videoView);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(splashScreen.this,
                    MainActivity.class);
            startActivity(i);
            finish();
        },  SPLASH_TIME_OUT);
    }
}