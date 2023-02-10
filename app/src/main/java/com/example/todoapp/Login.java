package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private TextInputEditText emailText, passwordText;
    private Button loginBtn, registerBtn;
    private FirebaseAuth mAuth;


    void changeToRegister() {
        Intent registerPageIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerPageIntent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerBtn = findViewById(R.id.signupBtn);
        loginBtn = findViewById(R.id.loginBtn);

        registerBtn.setOnClickListener(v -> changeToRegister());
        loginBtn.setOnClickListener(v->loginUser());

    }

    void loginUser(){
        emailText = findViewById(R.id.txtLoginEmail);
        passwordText =findViewById(R.id.txtLoginPassword);

        String loginEmail  = String.valueOf(emailText.getText()).trim();
        String loginPassword = String.valueOf(passwordText.getText()).trim();

        if(!Utility.emailValidation(loginEmail) ){
            Utility.showToastShort(Login.this,"Enter a valid Email");
            return;
        }
        if(loginPassword.length()<8 || !Utility.passwordValidation(loginPassword)){
            Utility.showToastShort(Login.this,"Enter a Strong a password");
            return;
        }


        loginUserFirebase(loginEmail,loginPassword);
    }
    void loginUserFirebase(String email, String password) {

        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(mAuth.getCurrentUser().isEmailVerified()){
                        startActivity(new Intent(Login.this,MainActivity.class));
                      Utility.showToastShort(Login.this,"Welcome...");
                      finish();
                    }else{
                        Utility.showToastShort(Login.this,"Verify email first");
                    }
                }else{
                    Utility.showToastShort(Login.this, task.getException().getLocalizedMessage());
                }
            }
        });

    }
}