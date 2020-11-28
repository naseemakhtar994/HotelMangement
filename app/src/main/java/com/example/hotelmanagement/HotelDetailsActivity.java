package com.example.hotelmanagement;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.net.ParseException;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aminography.primecalendar.PrimeCalendar;
import com.aminography.primecalendar.civil.CivilCalendar;
import com.aminography.primedatepicker.picker.PrimeDatePicker;
import com.aminography.primedatepicker.picker.callback.RangeDaysPickCallback;
import com.example.hotelmanagement.adapter.HotelAdapter;
import com.example.hotelmanagement.model.BookingModel;
import com.example.hotelmanagement.model.Hotel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HotelDetailsActivity extends AppCompatActivity {

    TextView textViewAminities, textviewHotelDescription, textviewHotelRAting, textviewHotelStar, textviewHotelName, textViewSelectedDates,pernightPrice;
    ImageView imageViewHotel;
    Button compare, buttonBooknow, buttonSelectDate, buttonSubmitBookingData;
    ArrayList comparedata = new ArrayList();
    ArrayList bookingList = new ArrayList();
    View booknow;
    long bookingDays=0;

    EditText editTextName, editTextContact, editTextAddress, editTextEmail;
    Spinner spinnerRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_details);
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        booknow = findViewById(R.id.booknow);
        imageViewHotel = (ImageView) findViewById(R.id.imageViewHotel);
        textviewHotelDescription = (TextView) findViewById(R.id.textViewDesction);
        textviewHotelName = (TextView) findViewById(R.id.textviewHotelName);
        textviewHotelStar = (TextView) findViewById(R.id.textviewHotelStar);
        textviewHotelRAting = (TextView) findViewById(R.id.textviewHotelRAting);
        textViewSelectedDates = (TextView) findViewById(R.id.textViewSelectedDates);
        textViewAminities = (TextView) findViewById(R.id.textViewAminities);
        editTextName = findViewById(R.id.editTextName);
        editTextContact = findViewById(R.id.editTextContact);
        editTextAddress = findViewById(R.id.editTextAddress);
        pernightPrice = findViewById(R.id.pernightPrice);
        buttonSubmitBookingData = findViewById(R.id.buttonSubmitBookingData);
        spinnerRooms = findViewById(R.id.spinnerRooms);
        editTextEmail = findViewById(R.id.editTextEmail);

        final Hotel hotel = (Hotel) getIntent().getSerializableExtra("hotel");

        pernightPrice.setText("£"+hotel.price);

        buttonSubmitBookingData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String mobile = editTextContact.getText().toString();
                String address = editTextAddress.getText().toString();
                String email = editTextEmail.getText().toString();

                DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a");
                Date date = new Date();
                System.out.println(dateFormat.format(date));

                if (validateForm(name, mobile, address, email)) {
                    BookingModel bookingModel=new BookingModel();
                    bookingModel.name=name;
                    bookingModel.mobile=mobile;
                    bookingModel.address=address;
                    bookingModel.email=email;
                    bookingModel.hotelName=hotel.hotelName;
                    bookingModel.booking_date=dateFormat.format(date);
                    bookingModel.earning=String.valueOf(Double.valueOf(bookingDays*Double.valueOf(hotel.price)));
                    bookingModel.dates=textViewSelectedDates.getText().toString();
                    bookingModel.room=spinnerRooms.getSelectedItem().toString();

                    bookingList.add(bookingModel);

                    mDatabase.child("bookings").setValue(bookingList);

                 new Common().showDialog(HotelDetailsActivity.this,"Booking Confirmed Sucessfully");
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


        mDatabase.child("compare").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


//                if (data == null) {
//                    data = new ArrayList();
//                }

                for (DataSnapshot zoneSnapshot : dataSnapshot.getChildren()) {
                    compare.setText("Go Compare");

                    Hotel hotel = new Hotel();
                    hotel.hotelName = (String) zoneSnapshot.child("hotelName").getValue();
                    hotel.hotelShortDesc = (String) zoneSnapshot.child("hotelShortDesc").getValue();
                    hotel.hotelDesc = (String) zoneSnapshot.child("hotelDesc").getValue();
                    hotel.hotelStar = (String) zoneSnapshot.child("hotelStar").getValue();
                    hotel.hotelRating = (String) zoneSnapshot.child("hotelRating").getValue();
                    hotel.image = hotel.getHotelImage();
                    hotel.amnities = (String) zoneSnapshot.child("amnities").getValue();
                    comparedata.add(hotel);
                    Log.e("hotelName", (String) zoneSnapshot.child("hotelName").getValue());
                }

//                Log.i(TAG, dataSnapshot.child("ZNAME").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "");
//                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

        compare = findViewById(R.id.compare);
        buttonBooknow = findViewById(R.id.buttonBooknow);
        buttonBooknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonBooknow.setVisibility(View.GONE);
                booknow.setVisibility(View.VISIBLE);
            }
        });
        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comparedata.size() == 0) {

                    comparedata.add(hotel);
                    mDatabase.child("compare").setValue(comparedata);
                    finish();
                } else {
                    if (comparedata.size() == 2) {
                        comparedata.remove(comparedata.size() - 1);
                    }
                    comparedata.add(hotel);
                    mDatabase.child("compare").setValue(comparedata);
                    Intent intent = new Intent(getApplicationContext(), CompareActivity.class);
                    startActivity(intent);
                }
            }
        });


        textviewHotelName.setText(hotel.hotelName);
        textviewHotelDescription.setText(hotel.hotelDesc);
        textviewHotelStar.setText(hotel.hotelStar + " Star");
        textviewHotelRAting.setText(hotel.hotelRating + " Rating");
        textViewAminities.setText(hotel.amnities);
        imageViewHotel.setImageResource(hotel.image);


        final RangeDaysPickCallback singleDayPickCallback = new RangeDaysPickCallback() {


            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onRangeDaysPicked(PrimeCalendar startDay, PrimeCalendar endDay) {

//                LocalDate dateBefore = LocalDate.of(startDay.getYear(), startDay.getMonth()+1, startDay.getDayOfMonth());
//                LocalDate dateAfter = LocalDate.of(endDay.getYear(), endDay.getMonth()+1, endDay.getDayOfMonth());
//


                SimpleDateFormat myFormat = new SimpleDateFormat("yyyy MM dd");
                String inputString1 = startDay.getYear()+" "+(startDay.getMonth()+1)+" "+startDay.getDayOfMonth();
                String inputString2 = endDay.getYear()+" "+(endDay.getMonth()+1)+" "+endDay.getDayOfMonth();

                try {
                    Date date1 = myFormat.parse(inputString1);
                    Date date2 = myFormat.parse(inputString2);
                    long diff = date2.getTime() - date1.getTime();
                    System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                    bookingDays= TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                } catch (ParseException | java.text.ParseException e) {
                    e.printStackTrace();
                }
                //29-July-2017, change this to your desired End Date
//                bookingDays = ChronoUnit.DAYS.between(dateBefore, dateAfter);

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

