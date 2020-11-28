package com.example.hotelmanagement.model;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class PriceComparator implements Comparator<Hotel> {

    @Override
    public int compare(Hotel sp1, Hotel sp2) {
        return (Float.valueOf(sp1.price) < Float.valueOf(sp2.price) ) ? -1: (Float.valueOf(sp1.price) >  Float.valueOf(sp2.price)) ? 1:0 ;
    }


}


