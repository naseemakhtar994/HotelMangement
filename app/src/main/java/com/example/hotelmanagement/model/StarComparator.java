package com.example.hotelmanagement.model;

import java.util.Comparator;

public class StarComparator implements Comparator<Hotel> {

    @Override
    public int compare(Hotel sp1, Hotel sp2) {
        return (Float.valueOf(sp1.hotelStar) < Float.valueOf(sp2.hotelStar) ) ? -1: (Float.valueOf(sp1.hotelStar) >  Float.valueOf(sp2.hotelStar)) ? 1:0 ;
    }


}


