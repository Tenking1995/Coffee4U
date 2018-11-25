package com.example.coffee4u;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3500;

    Animation smalltobig,nothingtocome,btnanim;
    ImageView imageView;
    TextView textView1;
    Typeface tf1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smalltobig = AnimationUtils.loadAnimation(this,R.anim.smalltobig);
        nothingtocome = AnimationUtils.loadAnimation(this,R.anim.nothingtocome);
        btnanim = AnimationUtils.loadAnimation(this,R.anim.btnanim);

        imageView = (ImageView) findViewById(R.id.imgicon1);
        textView1 = (TextView) findViewById(R.id.appTitle);

        imageView.startAnimation(smalltobig);
        textView1.startAnimation(nothingtocome);

        tf1 = Typeface.createFromAsset(getAssets(),"Lobster-Regular.ttf");
        textView1.setTypeface(tf1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent signInIntent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(signInIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
        setStatusBarTransparent();
    }

    private void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
