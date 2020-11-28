package com.example.hotelmanagement.model;

import com.example.hotelmanagement.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Hotel implements Serializable , Comparable{
    public  String hotelName;
    public  String hotelDesc;
    public  String hotelShortDesc;
    public  String hotelRating;
    public  String hotelStar;
    public  String amnities;
    public  String price;
    public  int image;




    public Hotel(String hotelName, String hotelDesc, String hotelShortDesc, String hotelRating, String hotelStar) {
        this.hotelName = hotelName;
        this.hotelDesc = hotelDesc;
        this.hotelShortDesc = hotelShortDesc;
        this.hotelRating = hotelRating;
        this.hotelStar = hotelStar;
    }

    public Hotel() {
    }

    public int getHotelImage() {
        final int min = 0;
        final int max = 8;
        final int random = new Random().nextInt((max - min) + 1) + min;

        return (int) Hotel.getHotelImages().get(random);
    }

    public static ArrayList getHotelImages() {

        ArrayList arrayList=new ArrayList();

        arrayList.add(R.drawable.images);
        arrayList.add(R.drawable.images1);
        arrayList.add(R.drawable.images2);
        arrayList.add(R.drawable.images2);
        arrayList.add(R.drawable.images4);
        arrayList.add(R.drawable.images6);
        arrayList.add(R.drawable.images7);
        arrayList.add(R.drawable.images8);
        arrayList.add(R.drawable.images9);

        return arrayList;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
