package com.example.hotelmanagement;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagement.adapter.MyBookingAdapter;
import com.example.hotelmanagement.adapter.ReservasationAdapter;
import com.example.hotelmanagement.model.BookingModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReservationList extends AppCompatActivity {
    private DatabaseReference mDatabase;

    ArrayList arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        View layoutFlter = findViewById(R.id.layoutFlter);
        View edit_query = findViewById(R.id.edit_query);
        layoutFlter.setVisibility(View.GONE);
        edit_query.setVisibility(View.GONE);
        final ProgressBar progressbar = findViewById(R.id.progressbar);

      final  boolean isTotalEarning= getIntent().getBooleanExtra("isTotalEarning",false);

        mDatabase.child("bookings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                ArrayList data = new ArrayList();

                for (DataSnapshot zoneSnapshot : dataSnapshot.getChildren()) {
                    BookingModel hotel = new BookingModel();
                    hotel.hotelName = (String) zoneSnapshot.child("hotelName").getValue();
                    hotel.room = (String) zoneSnapshot.child("room").getValue();
                    hotel.booking_date = (String) zoneSnapshot.child("booking_date").getValue();
                    hotel.earning = (String) zoneSnapshot.child("earning").getValue();
                    hotel.mobile = (String) zoneSnapshot.child("mobile").getValue();
                    hotel.dates = (String) zoneSnapshot.child("dates").getValue();
                    hotel.name = (String) zoneSnapshot.child("name").getValue();
                    data.add(hotel);
                    Log.e("hotelName", (String) zoneSnapshot.child("hotelName").getValue());
                }
                RecyclerView recyclerView = findViewById(R.id.recyView);
                if (isTotalEarning){
                    ReservasationAdapter adapter = new ReservasationAdapter(data, ReservationList.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recyclerView.setAdapter(adapter);
                }else {
                    ReservasationAdapter adapter = new ReservasationAdapter(data, ReservationList.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recyclerView.setAdapter(adapter);
                }

                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "");
            }
        });


    }
}
