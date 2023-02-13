package com.example.todoapp;


import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
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

    static String timestampToString(Timestamp timestamp){
        return new SimpleDateFormat("MM/dd/yyyy").format(timestamp.toDate());
    }
//    static boolean checkInternetConnection(){
//
//    }
}
