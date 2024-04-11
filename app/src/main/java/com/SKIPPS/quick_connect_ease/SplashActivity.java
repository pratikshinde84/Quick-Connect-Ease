package com.SKIPPS.quick_connect_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.secureserver.R;

public class SplashActivity extends AppCompatActivity {
    TextView tv_title, tv_subtitle;
    Animation fadeInAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv_title = findViewById(R.id.tv_main_title);
        tv_subtitle = findViewById(R.id.tv_main_subtitle);

        fadeInAnim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fadein);

        tv_title.setAnimation(fadeInAnim);
        tv_subtitle.setAnimation(fadeInAnim);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, SetPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3500);
    }
}
