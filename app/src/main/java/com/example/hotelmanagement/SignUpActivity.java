package com.example.hotelmanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hotelmanagement.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    EditText editTextNumber, editTextEmail, editTextName, editTexPassword;
    Spinner blood_spinner;
    Button button3;
    ArrayList data;


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String User = "User";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        editTextNumber = findViewById(R.id.editTextNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTexPassword = findViewById(R.id.editTexPassword);
        editTextName = findViewById(R.id.editTextName);
        button3 = findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subMitData();
            }
        });

        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                data = (ArrayList<UserModel>) dataSnapshot.getValue(Object.class);

//                Log.i(TAG, dataSnapshot.child("ZNAME").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "");
            }
        });
    }

    public String getValueFromRadioGroup(RadioGroup radioGroupGender) {
        int selectedRadioButtonId = radioGroupGender.getCheckedRadioButtonId();
        String selectedRbText = "";
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            selectedRbText = selectedRadioButton.getText().toString();
        }
        return selectedRbText;
    }

    public void subMitData() {
        final String name = editTextName.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String mobile = editTextNumber.getText().toString();
        final String password = editTexPassword.getText().toString();

        if (validateForm(name, email,mobile,password)) {

            UserModel createDonerModel = new UserModel();

            createDonerModel.name = name;
            createDonerModel.email = email;
            createDonerModel.mobile = mobile;

            createDonerModel.password = password;
            if (data == null) {
                data = new ArrayList();
            }
            data.add(createDonerModel);
            mDatabase.child("users").setValue(data);

            SharedPreferences.Editor editor = sharedpreferences.edit();

            String jsonobject = new Gson().toJson(new UserModel(name, email, mobile, password));
            editor.putString(User, jsonobject);

            editor.commit();


            new Common().CreatAccount(SignUpActivity.this, "Account Crateted Sucessfully");
            //                            mDatabase.child("request").updateChildren(childUpdates);

        }

        // ...


    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private boolean validateForm(String name, String email,String  mobile,String password) {
        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Please Enter Name");
            return false;
        } else if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please Enter Email");
            return false;
        }else if (!isValidEmail(email)) {
            editTextEmail.setError("Please Enter Valid Email");
            return false;
        }
        else if (TextUtils.isEmpty(mobile)) {
            editTextNumber.setError("Please Enter Mobile Number");
            return false;
        }
        else if (mobile.length()!=10) {
            editTextNumber.setError("Please Enter Valid Mobile Number");
            return false;
        } else if (TextUtils.isEmpty(password)) {
            editTexPassword.setError("Please Enter Password");
            return false;
        } else {
            editTextName.setError(null);
            editTextEmail.setError(null);
            editTexPassword.setError(null);
            editTextNumber.setError(null);
            return true;
        }
    }
}
