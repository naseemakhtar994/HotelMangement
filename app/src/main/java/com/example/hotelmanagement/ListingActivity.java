package com.example.hotelmanagement;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagement.adapter.HotelAdapter;
import com.example.hotelmanagement.model.Hotel;
import com.example.hotelmanagement.model.PriceComparator;
import com.example.hotelmanagement.model.RatingComparator;
import com.example.hotelmanagement.model.StarComparator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ListingActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;


    EditText edit_query;
    ArrayList arrayList = new ArrayList();
    ArrayList arrayListtemp = new ArrayList();
    Button priceButton, starButton, aminitiesButton, ratingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        View layoutFlter = findViewById(R.id.layoutFlter);
        layoutFlter.setVisibility(View.GONE);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        edit_query = findViewById(R.id.edit_query);
        priceButton = findViewById(R.id.priceButton);
        starButton = findViewById(R.id.starButton);
        aminitiesButton = findViewById(R.id.aminitiesButton);
        ratingButton = findViewById(R.id.ratingButton);
        priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (priceButton.getText().toString().contains("☑")) {
                    priceButton.setText("Price");


                    if (starButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new RatingComparator());

                    }

                    if (priceButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new PriceComparator());

                    }

                    if (starButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new StarComparator());

                    }
                    priceButton.setBackgroundColor(Color.parseColor("#B1B1B1"));
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyView);
                    HotelAdapter adapter = new HotelAdapter(arrayList, ListingActivity.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recyclerView.setAdapter(adapter);

                } else {
                    Collections.sort(arrayListtemp, new PriceComparator());

                    priceButton.setText("☑ Price");
                    priceButton.setBackgroundColor(Color.GREEN);
                    if (starButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new RatingComparator());

                    }

                    if (priceButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new PriceComparator());

                    }

                    if (starButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new StarComparator());

                    }
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyView);
                    HotelAdapter adapter = new HotelAdapter(arrayListtemp, ListingActivity.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recyclerView.setAdapter(adapter);
                }
            }
        });


        ratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ratingButton.getText().toString().contains("☑")) {
                    ratingButton.setText("Rating");

                    if (starButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new RatingComparator());

                    }

                    if (priceButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new PriceComparator());

                    }

                    if (starButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new StarComparator());

                    }
                    ratingButton.setBackgroundColor(Color.parseColor("#B1B1B1"));
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyView);
                    HotelAdapter adapter = new HotelAdapter(arrayList, ListingActivity.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recyclerView.setAdapter(adapter);

                } else {
                    Collections.sort(arrayListtemp, new RatingComparator());
                    ratingButton.setText("☑ Rating");
                    ratingButton.setBackgroundColor(Color.GREEN);

                    if (starButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new RatingComparator());

                    }

                    if (priceButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new PriceComparator());

                    }

                    if (starButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new StarComparator());

                    }
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyView);
                    HotelAdapter adapter = new HotelAdapter(arrayListtemp, ListingActivity.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recyclerView.setAdapter(adapter);

                }
            }
        });


        starButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (starButton.getText().toString().contains("☑")) {
                    starButton.setText("Star");

                    if (starButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new RatingComparator());

                    }

                    if (priceButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new PriceComparator());

                    }

                    if (starButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new StarComparator());

                    }
                    starButton.setBackgroundColor(Color.parseColor("#B1B1B1"));
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyView);
                    HotelAdapter adapter = new HotelAdapter(arrayList, ListingActivity.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recyclerView.setAdapter(adapter);
                } else {

                    starButton.setText("☑ Star");
                    starButton.setBackgroundColor(Color.GREEN);

                    if (starButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new RatingComparator());

                    }

                    if (priceButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new PriceComparator());

                    }

                    if (starButton.getText().toString().contains("☑")) {
                        Collections.sort(arrayListtemp, new StarComparator());

                    }
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyView);
                    HotelAdapter adapter = new HotelAdapter(arrayListtemp, ListingActivity.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    recyclerView.setAdapter(adapter);

                }
            }
        });

        edit_query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchHotel(editable.toString());
            }
        });

//        Hotel hotel=new Hotel();
//        hotel.hotelName="Resort Terra Paraiso";
//        hotel.hotelDesc="Resort Terra Paraiso is an elegant and unique beach resort with white sand beaches, located at about 100 meters from the famous Calangute Beach. This seaside haven depicting Goan culture, with Portuguese-inspired architecture on one side and beautiful blue seas on the other, leaves you with a feeling of heaven-on-earth.\n" +
//                "\n" +
//                "The Yatra SMART choice property comes with a promise of 8 assured amenities, money-back guarantee and priority helpline support. You also get free cancellation and standardized prices through the year.";
//        hotel.hotelShortDesc="Resort Terra Paraiso is an elegant and unique beach resort with white sand beaches.";
//        hotel.hotelRating="3.0";
//        hotel.hotelStar="4";
//        hotel.amnities="CCTV,ExtraBed,WIFI";
//        hotel.price="99";
//
//        arrayList.add(hotel);
//
//        hotel=new Hotel();
//        hotel.hotelName="Neelams The Grand";
//        hotel.hotelDesc="Resort Terra Paraiso is an elegant and unique beach resort with white sand beaches, located at about 100 meters from the famous Calangute Beach. This seaside haven depicting Goan culture, with Portuguese-inspired architecture on one side and beautiful blue seas on the other, leaves you with a feeling of heaven-on-earth.\n" +
//                "\n" +
//                "The Yatra SMART choice property comes with a promise of 8 assured amenities, money-back guarantee and priority helpline support. You also get free cancellation and standardized prices through the year.";
//        hotel.hotelShortDesc="Resort Terra Paraiso is an elegant and unique beach resort with white sand beaches.";
//        hotel.hotelRating="3.5";
//        hotel.hotelStar="5";
//        hotel.amnities="CCTV,ExtraBed,Free Lunch,Air Conditioning";
//        hotel.price="9";
//
//
//        arrayList.add(hotel);
//
//
//
//
//        hotel=new Hotel();
//        hotel.hotelName="NekAkh Motel";
//        hotel.hotelDesc="Resort located at about 100 meters from the famous Calangute Beach. This seaside haven depicting Goan culture, with Portuguese-inspired architecture on one side and beautiful blue seas on the other, leaves you with a feeling of heaven-on-earth.\n" +
//                "\n" +
//                "The Yatra SMART choice property comes with a promise of 8 assured amenities, money-back guarantee and priority helpline support. You also get free cancellation and standardized prices through the year.";
//        hotel.hotelShortDesc="Paraiso is an elegant and unique beach resort with white sand beaches.";
//        hotel.hotelRating="2.5";
//        hotel.hotelStar="3";
//        hotel.amnities="WIFI";
//        hotel.price="16";
//
//        arrayList.add(hotel);
//
//
//
//         hotel=new Hotel();
//        hotel.hotelName="Western House Hotel";
//        hotel.hotelDesc="Hello and welcome to Western House Hotel, located the edge of Ayr Racecourse we offer four start luxury accommodation in the heart of Burns Country";
//        hotel.hotelShortDesc="These 10 elegantly designed bedrooms in the Hotel's main house offer accommodation of the highest standard, which include Executive rooms and Suites, combining the opulence of a bygone era with 21st century amenities.";
//        hotel.hotelRating="3.0";
//        hotel.hotelStar="4";
//        hotel.amnities="Rooms, Wedding halls, conference hall, Dining";
//        hotel.price="35";
//        arrayList.add(hotel);
//
//        hotel=new Hotel();
//        hotel.hotelName="London Ealing hotel";
//        hotel.hotelDesc="We know plans can change, so please make sure you book the rate with the right level of flexibility for you. ";
//        hotel.hotelShortDesc="Making your visit to Hyannis and Cape Cod memorable is our only business. ";
//        hotel.hotelRating="3.5";
//        hotel.hotelStar="5";
//        hotel.amnities="Chargeable ,Onsite parking, Air conditioning, Amazon Lockers, Family rooms Lift access, Free Wi-Fi, Accessible";
//        hotel.price="29";
//
//
//        arrayList.add(hotel);
//
//
//
//
//        hotel=new Hotel();
//        hotel.hotelName="Travelodge London Hackney";
//        hotel.hotelDesc="Travelodge London Hackney Hotel is an excellent choice for travellers visiting London, offering a family-friendly environment alongside many helpful amenities designed to enhance your stay";
//        hotel.hotelShortDesc="Travelodge is an elegant and unique beach resort with white sand beaches.";
//        hotel.hotelRating="2.5";
//        hotel.hotelStar="3";
//        hotel.amnities="WIFI";
//        hotel.price="24.99";
//
//        arrayList.add(hotel);
//
//
//        mDatabase.child("hotels").setValue(arrayList);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        final ProgressBar progressbar = findViewById(R.id.progressbar);
        mDatabase.child("hotels").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                arrayList = new ArrayList();
                arrayListtemp = new ArrayList();
//                if (data == null) {
//                    data = new ArrayList();
//                }

                for (DataSnapshot zoneSnapshot : dataSnapshot.getChildren()) {
                    Hotel hotel = new Hotel();
                    hotel.hotelName = (String) zoneSnapshot.child("hotelName").getValue();
                    hotel.hotelShortDesc = (String) zoneSnapshot.child("hotelShortDesc").getValue();
                    hotel.hotelDesc = (String) zoneSnapshot.child("hotelDesc").getValue();
                    hotel.hotelStar = (String) zoneSnapshot.child("hotelStar").getValue();
                    hotel.hotelRating = (String) zoneSnapshot.child("hotelRating").getValue();
                    hotel.price = (String) zoneSnapshot.child("price").getValue();
                    hotel.image = hotel.getHotelImage();
                    hotel.amnities = (String) zoneSnapshot.child("amnities").getValue();
                    arrayList.add(hotel);
                    arrayListtemp.add(hotel);
                    Log.e("hotelName", (String) zoneSnapshot.child("hotelName").getValue());
                }
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyView);
                HotelAdapter adapter = new HotelAdapter(arrayList, ListingActivity.this);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                recyclerView.setAdapter(adapter);
                progressbar.setVisibility(View.GONE);
                Log.e("", "");
//                Log.i(TAG, dataSnapshot.child("ZNAME").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "");
//                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });


        aminitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shownimities();
            }
        });

    }


    public void searchHotel(final String serach) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final ProgressBar progressbar = findViewById(R.id.progressbar);
        mDatabase.child("hotels").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                ArrayList data = new ArrayList();
//                if (data == null) {
//                    data = new ArrayList();
//                }

                for (DataSnapshot zoneSnapshot : dataSnapshot.getChildren()) {
                    Hotel hotel = new Hotel();
                    hotel.hotelName = (String) zoneSnapshot.child("hotelName").getValue();
                    hotel.hotelShortDesc = (String) zoneSnapshot.child("hotelShortDesc").getValue();
                    hotel.hotelDesc = (String) zoneSnapshot.child("hotelDesc").getValue();
                    hotel.hotelStar = (String) zoneSnapshot.child("hotelStar").getValue();
                    hotel.hotelRating = (String) zoneSnapshot.child("hotelRating").getValue();
                    hotel.price = (String) zoneSnapshot.child("price").getValue();
                    hotel.image = hotel.getHotelImage();
                    hotel.amnities = (String) zoneSnapshot.child("amnities").getValue();
                    if (hotel.hotelName.contains(serach)) {
                        data.add(hotel);
                    }

                    Log.e("hotelName", (String) zoneSnapshot.child("hotelName").getValue());
                }
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyView);
                HotelAdapter adapter = new HotelAdapter(data, ListingActivity.this);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                recyclerView.setAdapter(adapter);
                progressbar.setVisibility(View.GONE);
                Log.e("", "");
//                Log.i(TAG, dataSnapshot.child("ZNAME").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "");
//                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    ArrayList selectedAminities=new ArrayList();
    public  void getFilterAMinities(final ArrayList aminitis){


//        for (int i = 0; i < selectedAminities.size() ; i++) {
//            selectedAminities.get(i);
//        }
        Log.e("","");
        selectedAminities=aminitis;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final ProgressBar progressbar = findViewById(R.id.progressbar);
        mDatabase.child("hotels").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                ArrayList data = new ArrayList();
//                if (data == null) {
//                    data = new ArrayList();
//                }

                for (DataSnapshot zoneSnapshot : dataSnapshot.getChildren()) {
                    Hotel hotel = new Hotel();
                    hotel.hotelName = (String) zoneSnapshot.child("hotelName").getValue();
                    hotel.hotelShortDesc = (String) zoneSnapshot.child("hotelShortDesc").getValue();
                    hotel.hotelDesc = (String) zoneSnapshot.child("hotelDesc").getValue();
                    hotel.hotelStar = (String) zoneSnapshot.child("hotelStar").getValue();
                    hotel.hotelRating = (String) zoneSnapshot.child("hotelRating").getValue();
                    hotel.price = (String) zoneSnapshot.child("price").getValue();
                    hotel.image = hotel.getHotelImage();
                    hotel.amnities = (String) zoneSnapshot.child("amnities").getValue();


                    for (Object itemaminities:aminitis) {
                        if (hotel.amnities.toLowerCase().contains(itemaminities.toString().toLowerCase())) {
                            data.add(hotel);
                            break;
                        }
                    }


                    Log.e("hotelName", (String) zoneSnapshot.child("hotelName").getValue());
                }
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyView);
                HotelAdapter adapter = new HotelAdapter(data, ListingActivity.this);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                recyclerView.setAdapter(adapter);
                progressbar.setVisibility(View.GONE);
                Log.e("", "");
//                Log.i(TAG, dataSnapshot.child("ZNAME").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("", "");
//                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

    }

    public void shownimities() {

// add a checkbox list
        ArrayList aminities = new ArrayList();

        aminities.add("dsfdfds");

        CheckBoxMultiSelect checkBoxMultiSelect=   new CheckBoxMultiSelect();
//        checkBoxMultiSelect.showDiaog(ListingActivity.this);
    }


}

