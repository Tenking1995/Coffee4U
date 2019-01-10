package com.example.coffee4u;

import android.content.Intent;
import android.service.autofill.SaveInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupEmailActivity extends AppCompatActivity {

    private EditText txtEmail, txtPassword, txtConfirmPassword;
    private Button btn_register, btnLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);

        mAuth = FirebaseAuth.getInstance();

        txtEmail = (EditText) findViewById(R.id.txt_reg_email);
        txtPassword = (EditText) findViewById(R.id.txt_reg_password);
        txtConfirmPassword = (EditText) findViewById(R.id.txt_confirm_password);
        btn_register = (Button) findViewById(R.id.btn_reg_email);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailRegister = txtEmail.getText().toString();
                String passwordRegister = txtPassword.getText().toString();
                String confirm_pass = txtConfirmPassword.getText().toString();

                if(!TextUtils.isEmpty(emailRegister) && !TextUtils.isEmpty(passwordRegister) && !TextUtils.isEmpty(confirm_pass)){
                    if(passwordRegister.equals(confirm_pass)){
                      mAuth.createUserWithEmailAndPassword(emailRegister, passwordRegister).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if(task.isSuccessful()){
                                  sendToSignUpPage();
                              }else{
                                  String errorMessage = task.getException().getMessage();
                                  Toast.makeText(SignupEmailActivity.this, "Error" + errorMessage, Toast.LENGTH_LONG).show();
                              }
                          }
                      });
                    }else{
                        Toast.makeText(SignupEmailActivity.this, "Confirm password and password field does not match.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            sendToSignUpPage();
        }
    }
    private void sendToSignUpPage() {
        Intent mainIntent = new Intent(SignupEmailActivity.this, SignUpActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
