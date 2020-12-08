package com.example.hotelmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelmanagement.model.UserModel;
import com.google.gson.Gson;

public class AdminDashBoard extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String User = "User";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Button button6=findViewById(R.id.button6);
        Button buttonTotalEraning=findViewById(R.id.buttonTotalEraning);
        Button button7=findViewById(R.id.button7);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReservationList.class);
                startActivity(intent);
            }
        });

        buttonTotalEraning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TotalEarningActivity.class);
                intent.putExtra("isTotalEarning",true);
                startActivity(intent);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("Admin", null);

                editor.commit();
                Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
