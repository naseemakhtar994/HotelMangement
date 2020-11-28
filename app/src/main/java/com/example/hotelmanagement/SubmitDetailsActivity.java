package com.example.hotelmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aminography.primecalendar.PrimeCalendar;
import com.aminography.primecalendar.civil.CivilCalendar;
import com.aminography.primedatepicker.picker.PrimeDatePicker;
import com.aminography.primedatepicker.picker.callback.RangeDaysPickCallback;
import com.example.hotelmanagement.model.BookingModel;
import com.example.hotelmanagement.model.Hotel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class SubmitDetailsActivity extends AppCompatActivity {

    TextView textViewSelectedDates;
    Button  buttonSelectDate, buttonSubmitBookingData;
    ArrayList comparedata = new ArrayList();
    ArrayList bookingList = new ArrayList();


    EditText editTextName, editTextContact, editTextAddress, editTextEmail;
    Spinner spinnerRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_submit_layout);
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


        textViewSelectedDates = (TextView) findViewById(R.id.textViewSelectedDates);
        editTextName = findViewById(R.id.editTextName);
        editTextContact = findViewById(R.id.editTextContact);
        editTextAddress = findViewById(R.id.editTextAddress);
        buttonSubmitBookingData = findViewById(R.id.buttonSubmitBookingData);
        editTextEmail = findViewById(R.id.editTextEmail);

        final Hotel hotel = (Hotel) getIntent().getSerializableExtra("hotel");


        buttonSubmitBookingData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner    spinnerRooms = findViewById(R.id.spinnerRooms);

                String name = editTextName.getText().toString();
                String mobile = editTextContact.getText().toString();
                String address = editTextAddress.getText().toString();
                String email = editTextEmail.getText().toString();
                if (validateForm(name, mobile, address, email)) {
                    BookingModel bookingModel=new BookingModel();
                    bookingModel.hotelName=name;
                    bookingModel.mobile=mobile;
                    bookingModel.address=address;
                    bookingModel.email=email;
                    bookingModel.hotelName=hotel.hotelName;
                    bookingModel.room=spinnerRooms.getSelectedItem().toString();
                    bookingList.add(bookingModel);

                    mDatabase.child("bookings").setValue(bookingList);

                    new Common().showDialog(SubmitDetailsActivity.this,"Booking Confirmed Sucessfully");
                }
            }
        });



        mDatabase.child("bookings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot zoneSnapshot : dataSnapshot.getChildren()) {


                    BookingModel hotel = new BookingModel();
                    hotel.hotelName = (String) zoneSnapshot.child("hotelName").getValue();
                    hotel.room = (String) zoneSnapshot.child("room").getValue();
                    hotel.email = (String) zoneSnapshot.child("email").getValue();
                    hotel.address = (String) zoneSnapshot.child("address").getValue();
                    hotel.mobile = (String) zoneSnapshot.child("mobile").getValue();
                    hotel.dates = (String) zoneSnapshot.child("dates").getValue();
                    hotel.name = (String) zoneSnapshot.child("name").getValue();
                    bookingList.add(hotel);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "");
            }
        });





        final RangeDaysPickCallback singleDayPickCallback = new RangeDaysPickCallback() {


            @Override
            public void onRangeDaysPicked(PrimeCalendar startDay, PrimeCalendar endDay) {
                Log.e("", "");
                textViewSelectedDates.setText("Date: From " + startDay.getShortDateString() + " To " + endDay.getShortDateString());
            }
        };

//        BaseThemeFactory themeFactory = new LightThemeFactory();


        buttonSelectDate = findViewById(R.id.buttonSelectDate);
        buttonSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrimeCalendar today = new CivilCalendar();  // Causes a Japanese date picker, also today as the starting date

                final PrimeDatePicker datePicker = PrimeDatePicker.Companion.dialogWith(today) // or bottomSheetWith(today)
                        .pickRangeDays(singleDayPickCallback)  // Passing callback is optional, can be set later using setDayPickCallback()

                        .minPossibleDate(today)      // Optional
//                .maxPossibleDate(maxDateCalendar)      // Optional
//                .disabledDays(disabledDaysList)        // Optional
                        .firstDayOfWeek(Calendar.MONDAY)
                        // Optional
//                .applyTheme(themeFactory)              // Optional
                        .build();
                datePicker.show(getSupportFragmentManager(), "SOME_TAG");

            }
        });
    }


    private boolean validateForm(String name, String mobile, String address, String email) {
        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Please Enter Name");
            return false;
        } else if (TextUtils.isEmpty(mobile)) {
            editTextContact.setError("Please Enter Mobile Number");
            return false;
        } else if (TextUtils.isEmpty(address)) {
            editTextAddress.setError("Please Enter Address");
            return false;
        } else if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please Enter Email");
            return false;
        } else if (textViewSelectedDates.getText().toString().equals("Date")) {
            Toast.makeText(getApplicationContext(), "Please Select Date", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            editTextName.setError(null);
            editTextAddress.setError(null);
            editTextContact.setError(null);
            return true;
        }
    }




}
