package com.example.altasmontaas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class Intro extends AppCompatActivity {
    private static final int DELAY_TIME = 3000; // 3 segundos
    ImageView logo;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        logo = findViewById(R.id.Logo);

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(2000);
        logo.startAnimation(fadeIn);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), Municipios.class);
            startActivity(intent);
            finish();
        }, DELAY_TIME);

    }
}