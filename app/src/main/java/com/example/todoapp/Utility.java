package com.example.todoapp;


import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    static void showToastShort(Context context , String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    static boolean emailValidation (String email){

        return !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }
    static boolean passwordValidation(String password){

            Pattern pattern;
            Matcher matcher;
            final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(password);
            return matcher.matches();

    }
}
