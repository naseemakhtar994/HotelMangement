package com.example.hotelmanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.hotelmanagement.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class LooginActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    ArrayList data;
    EditText editTextEmail, editTextPhone;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String User = "User";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loogin);
        editTextEmail = findViewById(R.id.editTextTextPersonName3);
        editTextPhone = findViewById(R.id.editTextTextPersonName4);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);




        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                data = (ArrayList<UserModel>) dataSnapshot.getValue(Object.class);
                if (data == null) {
                    data = new ArrayList();
                }


                Log.e("", "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

    public void login(View view) {
        boolean islogined=false;
        if (validateForm(editTextEmail.getText().toString(),editTextPhone.getText().toString())) {


            for (HashMap<String, String> myListData : (ArrayList<HashMap<String, String>>) data) {
                String email = myListData.get("email");
                String phone = myListData.get("mobile");
                String name = myListData.get("name");
                String password = myListData.get("password");
                String bloodgroup = myListData.get("bloodgroup");



                if (editTextEmail.getText().toString().equals(email) && editTextPhone.getText().toString().equals(password)) {

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    String jsonobject = new Gson().toJson(new UserModel(name, email, phone, password));
                    editor.putString("User", jsonobject);

                    editor.commit();
                    islogined = true;
                }


            }

            if (islogined) {
                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                startActivity(intent);
                finish();
            } else {
                new Common().showMessage(LooginActivity.this, "User name and password is invalied");
            }

        }
    }

    public void signup(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
        finish();
    }


    private boolean validateForm(String email,String password) {
         if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please Enter Email");
            return false;
        } else if (TextUtils.isEmpty(password)) {
            editTextPhone.setError("Please Enter Password");
            return false;
        } else {
             editTextPhone.setError(null);
            editTextEmail.setError(null);
            return true;
        }
    }
}
