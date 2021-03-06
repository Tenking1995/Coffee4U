package com.example.coffee4u;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private LinearLayout coffeeShop;
    private LinearLayout donate;
    private LinearLayout redeemGift;
    private LinearLayout contactUs;
    private LinearLayout account;
    private LinearLayout setting;
    private LinearLayout logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Status Bar Transparent
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_menu);

        //get Instance of current user
        mAuth = FirebaseAuth.getInstance();
        //initialize onclick for linear layout
        coffeeShop = findViewById(R.id.shop_layout);
        donate = findViewById(R.id.donate_layout);
        redeemGift = findViewById(R.id.redeem_layout);
        contactUs = findViewById(R.id.contact_layout);
        account = findViewById(R.id.account_layout);
        setting = findViewById(R.id.setting_layout);
        logout = findViewById(R.id.logout_layout);

        //set intent for each linear layout
        coffeeShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DonateActivity.class);
                startActivity(intent);
            }
        });
        redeemGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, GiftActivity.class);
                startActivity(intent);
            }
        });
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOutUser();
            }
        });
    }

    @Override
    protected void onStart() { //if current user doesn't exit, timeout etc, auto send to login page
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null){
            sendToLogin();
        }
    }

    private void logOutUser() {
        mAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {
        Intent loginIntent = new Intent (MenuActivity.this, SignInActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
