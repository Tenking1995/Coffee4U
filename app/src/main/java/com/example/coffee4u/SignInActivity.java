package com.example.coffee4u;

import android.content.Intent;
import android.content.pm.LauncherApps;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;

public class SignInActivity extends AppCompatActivity {

    private CallbackManager mCallbackManager;

    private static final String TAG = "FACELOG";

    private FirebaseAuth mAuth;
    //initialize button to navigate to sign up activity

    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Status Bar Transparent
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);

        setContentView(R.layout.activity_sign_in);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //initializ signUp button
        signUpButton = findViewById(R.id.btn_signup_account);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpEmailActivity();
            }
        });

    };

    //signup Activity method
    public void openSignUpEmailActivity() {
        Intent intent = new Intent(this,  SignupEmailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            updateUI();
        }
    }

    private void updateUI() {

        Toast.makeText(SignInActivity.this,"You're  logged in", Toast.LENGTH_LONG).show();

        Intent accountIntent = new Intent(SignInActivity.this, MenuActivity.class);
        startActivity(accountIntent);
        finish();               
    }


    public void OnClick(View view) {
        Intent intent = new Intent(SignInActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
