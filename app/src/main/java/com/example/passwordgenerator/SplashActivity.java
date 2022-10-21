package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private LinearLayout title_layout;
    private LinearLayout developer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        title_layout = findViewById(R.id.title_layout);
        developer_layout = findViewById(R.id.developer_layout);

        playAnimations();

        final Intent intent = new Intent(this, MainActivity.class);

        new android.os.Handler().postDelayed(
            new Runnable() {
                public void run() {
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                    finish();
                }
            },
        3000);
    }

    public void playAnimations() {
        title_layout.setAlpha(0f);
        title_layout.setTranslationY(-100);
        title_layout.setScaleX(2f);
        title_layout.setScaleY(2f);
        title_layout.animate().alpha(1f).setDuration(700).setStartDelay(200).start();
        title_layout.animate().translationY(0).setDuration(800).setStartDelay(200).start();
        title_layout.animate().scaleX(1f).setDuration(500).setStartDelay(100).start();
        title_layout.animate().scaleY(1f).setDuration(500).setStartDelay(100).start();

        developer_layout.setAlpha(0f);
        developer_layout.setScaleX(0f);
        developer_layout.setScaleY(0f);
        developer_layout.animate().alpha(1f).setDuration(500).setStartDelay(400).start();
        developer_layout.animate().scaleX(1f).setDuration(500).setStartDelay(400).start();
        developer_layout.animate().scaleY(1f).setDuration(500).setStartDelay(400).start();
    }
}
