package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hotelmanagement.model.Hotel;
import com.example.hotelmanagement.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MyAccount extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    private DatabaseReference mDatabase;
    ArrayList<UserModel> data;
Button button3;
    SharedPreferences sharedpreferences;
    EditText textViewName,textViewMobileNo,textViewEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String userdata = sharedpreferences.getString("User", null);

        final UserModel model = new Gson().fromJson(userdata, UserModel.class);
        textViewName=findViewById(R.id.editTextName);
        textViewMobileNo=findViewById(R.id.editTextNumber);
        textViewEmail=findViewById(R.id.editTextEmail);
        button3=findViewById(R.id.button3);

        textViewName.setText(model.name);
        textViewMobileNo.setText(model.mobile);
        textViewEmail.setText(model.email);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i <data.size() ; i++) {
                    if (data.get(i).email.equals(textViewEmail.getText().toString())){
                        data.get(i).name=textViewName.getText().toString();
                        data.get(i).mobile=textViewMobileNo.getText().toString();
                    }
                }


                SharedPreferences.Editor editor = sharedpreferences.edit();

                String jsonobject = new Gson().toJson(new UserModel(textViewName.getText().toString(), textViewEmail.getText().toString(), textViewMobileNo.getText().toString(), model.password));
                editor.putString("User", jsonobject);

                editor.commit();

                mDatabase.child("users").setValue(data);
                new Common().CreatAccount(MyAccount.this, "Profile Update Sucessfully");

            }
        });

        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                data = new ArrayList<UserModel>();

                for (DataSnapshot zoneSnapshot : dataSnapshot.getChildren()) {
                    UserModel hotel = new UserModel();
                    hotel.name = (String) zoneSnapshot.child("name").getValue();
                    hotel.email = (String) zoneSnapshot.child("email").getValue();
                    hotel.password = (String) zoneSnapshot.child("password").getValue();
                    hotel.mobile = (String) zoneSnapshot.child("mobile").getValue();

                        data.add(hotel);


                }


//                Log.i(TAG, dataSnapshot.child("ZNAME").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "");
            }
        });

    }
}