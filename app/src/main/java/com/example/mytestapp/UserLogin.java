package com.example.mytestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;


public class UserLogin extends AppCompatActivity {

    private Button login;
    private TextView signup;
    FirebaseAuth auth;

    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //Configure Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
       // getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Login");

        //Configure View widgets and components
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        login = findViewById(R.id.user_login);
        signup = findViewById(R.id.sign_up);

        //Setting Click Listeners
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        //Acquiring auth instance
        auth = FirebaseAuth.getInstance();
    }

    private void signUp() {
        Intent signUpPage = new Intent(this, Registration.class);
        startActivity(signUpPage);
    }

    private void goToDashboard(){
        Intent dashboard = new Intent(this, DashBoard.class);
        startActivity(dashboard);
        finish();
    }

    private void doLogin() {
        String txtEmail, txtPassword;
        txtEmail = email.getText().toString();
        txtPassword = password.getText().toString();

        if(txtEmail.isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter Email Address",Toast.LENGTH_SHORT).show();
            return;
        }

        if(txtPassword.isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(txtEmail, txtPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Sign in completed",Toast.LENGTH_SHORT).show();
                    goToDashboard();
                }else{
                    if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                        Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();
                    }else if(task.getException() instanceof FirebaseAuthInvalidUserException){
                            Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
