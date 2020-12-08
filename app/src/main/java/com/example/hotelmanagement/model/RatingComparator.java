package com.example.hotelmanagement.model;

import java.util.Comparator;

public class RatingComparator implements Comparator<Hotel> {

    @Override
    public int compare(Hotel sp1, Hotel sp2) {
        return (Float.valueOf(sp1.hotelRating) < Float.valueOf(sp2.hotelRating) ) ? -1: (Float.valueOf(sp1.hotelRating) >  Float.valueOf(sp2.hotelRating)) ? 1:0 ;
    }


}


