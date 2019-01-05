package com.example.coffee4u;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    private LinearLayout coffeeShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Status Bar Transparent
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_menu);

        //initialize onclick for linear layout
        coffeeShop = findViewById(R.id.coffeeShopLay);
        coffeeShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DonateActivity.class);
                startActivity(intent);
            }
        });

    }

    public void OnClick(View view) {
        Intent intent = new Intent(MenuActivity.this, DonateActivity.class);
        startActivity(intent);
    }

    public void OnClick1(View view) {
        Intent intent = new Intent(MenuActivity.this, ContactActivity.class);
        startActivity(intent);
    }
}
