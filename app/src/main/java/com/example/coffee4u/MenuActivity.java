package com.example.coffee4u;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Status Bar Transparent
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_menu);
    }

    public void OnClick(View view) {
        Intent intent = new Intent(MenuActivity.this, DonateActivity.class);
        startActivity(intent);
        finish();
    }

    public void OnClick1(View view) {
        Intent intent = new Intent(MenuActivity.this, Contactus.class);
        startActivity(intent);
        finish();
    }
}
