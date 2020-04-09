package com.example.mytestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    private Button signup;
    private EditText txtEmail, txtPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Sign Up");

        signup = findViewById(R.id.signup);
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);

        //Get firebase authentication instance
        auth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegistration();
            }
        });

        txtPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(),"Password needs at least 5 Characters",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public void goToLogin(){

        Intent loginPage = new Intent(this, UserLogin.class);
        startActivity(loginPage);
    }


    private void startRegistration() {
        String email, password;
        Pattern pattern = Patterns.EMAIL_ADDRESS;

        email = txtEmail.getText().toString().trim();
        password = txtPassword.getText().toString().trim();

        //Validation for Email and Password
        if(email.isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter Email Address",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            if(!pattern.matcher(email).matches()){
                Toast.makeText(getApplicationContext(),"Invalid Email Address",Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if(password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()<6){
            Toast.makeText(getApplicationContext(),"Password is too short",Toast.LENGTH_SHORT).show();
            return;
        }
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new  OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    goToLogin();
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "Email already in Use", Toast.LENGTH_SHORT).show();
                        }
                    }
                 }
             });
    }


}
