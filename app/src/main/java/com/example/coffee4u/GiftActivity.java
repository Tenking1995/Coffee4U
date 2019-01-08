package com.example.coffee4u;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class GiftActivity extends AppCompatActivity {

    private Button btnConfirm;
    private TextView lblPoint;
    private Spinner spinnerGift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);

        //initialize variables on view
        btnConfirm = findViewById(R.id.confirm_button);
        lblPoint = findViewById(R.id.label_points);
        spinnerGift = findViewById(R.id.gift_spinner);
    }
}
