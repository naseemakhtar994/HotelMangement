package com.example.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String User = "User";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button2=findViewById(R.id.button2);
        Button button=findViewById(R.id.button);
        Button button8=findViewById(R.id.button8);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        String userdata=  sharedpreferences.getString(User, null);
        String Admin=  sharedpreferences.getString("Admin", null);


        if(userdata!=null){
            Intent intent=new Intent(getApplicationContext(), DashBoardActivity.class);
            startActivity(intent);
            finish();
        } else

        if(Admin!=null){
            Intent intent=new Intent(getApplicationContext(), AdminDashBoard.class);
            startActivity(intent);
            finish();
        }


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), LooginActivity.class);
                startActivity(intent);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(intent);
            }
        });



    }
}