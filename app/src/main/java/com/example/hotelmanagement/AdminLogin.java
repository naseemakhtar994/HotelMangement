package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hotelmanagement.model.UserModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminLogin extends AppCompatActivity {
    EditText editTextEmail, editTextPhone;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String User = "User";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        editTextEmail = findViewById(R.id.editTextTextPersonName3);
        editTextPhone = findViewById(R.id.editTextTextPersonName4);

        String Admin=  sharedpreferences.getString("Admin", null);


        if(Admin!=null){
            Intent intent=new Intent(getApplicationContext(), AdminDashBoard.class);
            startActivity(intent);
            finish();
        }
    }


    public void login(View view) {
        if (editTextEmail.getText().toString().equals("admin@gmail.com") && editTextPhone.getText().toString().equals("123456")) {

            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("Admin", "Admin");

            editor.commit();
            Intent intent = new Intent(getApplicationContext(), AdminDashBoard.class);
            startActivity(intent);
            finish();
        }else {
            new Common().showMessage(AdminLogin.this,"User name and password is invalied");
        }
    }

}