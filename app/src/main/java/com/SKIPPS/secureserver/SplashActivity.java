package com.SKIPPS.secureserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.secureserver.R;

public class SplashActivity extends AppCompatActivity {
    TextView tv_title,tv_subtitle;
    Animation fadeInAnim;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferences= PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
        editor=preferences.edit();
        tv_title=findViewById(R.id.tv_main_title);
        tv_subtitle=findViewById(R.id.tv_main_subtitle);

        fadeInAnim= AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fadein);

        tv_title.setAnimation(fadeInAnim);
        tv_subtitle.setAnimation(fadeInAnim);

        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        },3500);

    }
}