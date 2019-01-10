package com.example.coffee4u;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private EditText reg_name, reg_address, reg_age, reg_contactNo;
    private Button btnRegisterDetails;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        reg_name = (EditText) findViewById(R.id.txtName);
        reg_address =(EditText) findViewById(R.id.txtAddress);
        reg_age = (EditText) findViewById(R.id.txtAge);
        reg_contactNo = (EditText) findViewById(R.id.txtNumber);
        btnRegisterDetails = (Button) findViewById(R.id.btn_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        btnRegisterDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = reg_name.getText().toString();
                String user_address = reg_address.getText().toString();
                Integer user_age = Integer.parseInt(reg_age.getText().toString());
                String user_contactNo = reg_contactNo.getText().toString();

                if(!TextUtils.isEmpty(user_name)){
                    String user_id = firebaseAuth.getCurrentUser().getUid();
                    CollectionReference users = firebaseFirestore.collection("Users");
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("name", user_name);
                    userMap.put("address", user_address);
                    userMap.put("age", user_age);
                    userMap.put("contactNo", user_contactNo);
                    userMap.put("noOfCups", 0);
                    userMap.put("points", 0);

                    firebaseFirestore.collection("Users").document(user_id).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "Details are added into database", Toast.LENGTH_LONG).show();
                                Intent menuIntent = new Intent(SignUpActivity.this, MenuActivity.class);
                                startActivity(menuIntent);
                                finish();
                            }else{
                                String error = task.getException().getMessage();
                                Toast.makeText(SignUpActivity.this, "(FIRESTORE ERROR)" + error, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
