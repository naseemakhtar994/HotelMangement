package com.example.hotelmanagement;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagement.adapter.ReservasationAdapter;
import com.example.hotelmanagement.model.BookingModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TotalEarningActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    ArrayList arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_earning);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        final ProgressBar progressbar = findViewById(R.id.progressbar);
        final LinearLayout layoutEarning = findViewById(R.id.layoutEarning);
        final TextView textViewTotalEarning = findViewById(R.id.textViewTotalEarning);
        final  boolean isTotalEarning= getIntent().getBooleanExtra("isTotalEarning",false);

        mDatabase.child("bookings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                ArrayList data = new ArrayList();

                Double total=0.0;
                for (DataSnapshot zoneSnapshot : dataSnapshot.getChildren()) {
                    BookingModel hotel = new BookingModel();

                    hotel.earning = (String) zoneSnapshot.child("earning").getValue();

                    total=Double.valueOf(hotel.earning)+total;

                    data.add(hotel);
                    Log.e("hotelName", (String) zoneSnapshot.child("hotelName").getValue());
                }

                textViewTotalEarning.setText("£ " + total);
                layoutEarning.setVisibility(View.VISIBLE);

                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "");
            }
        });


    }
}

