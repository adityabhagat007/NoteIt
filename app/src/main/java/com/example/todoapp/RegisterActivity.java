package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText emailText, phoneText, passwordText;
    private Button registerBtn ,loginBtn;
    private FirebaseAuth mAuth;



    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        emailText = findViewById(R.id.txtEmail);
        phoneText = findViewById(R.id.txtPhone);
        passwordText = findViewById(R.id.txtPassword);
        registerBtn = findViewById(R.id.signUp);
        loginBtn = findViewById(R.id.login);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, phone;
                email = String.valueOf(emailText.getText()).trim();
                password = String.valueOf(passwordText.getText()).trim();
                phone = String.valueOf(phoneText.getText()).trim();
                if (email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty() || password.length() <= 3) {
                    Toast.makeText(getApplicationContext(), "Please Enter a Strong Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.length() != 10) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                registerUser(email, password);

            }
        });
        loginBtn.setOnClickListener(v->changeToLoginScreen());
    }



    void registerUser(String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        Intent loginIntent = new Intent(this, Login.class);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                            mAuth.getCurrentUser().sendEmailVerification();
                            mAuth.signOut();
                            startActivity(loginIntent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    void changeToLoginScreen(){
        Intent loginIntent = new Intent(this, Login.class);
        startActivity(loginIntent);
    }
}