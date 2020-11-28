package com.example.hotelmanagement.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagement.HotelDetailsActivity;
import com.example.hotelmanagement.R;
import com.example.hotelmanagement.model.BookingModel;
import com.example.hotelmanagement.model.Hotel;

import java.io.Serializable;
import java.util.ArrayList;

public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.ViewHolder>{
    private ArrayList<BookingModel> listdata;
   Activity activity;
    // RecyclerView recyclerView;
    public MyBookingAdapter(ArrayList<BookingModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity=activity;
    }



//    public HotelAdapter(ArrayList data) {
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.cutom_my_boking, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }




    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final BookingModel myListData =  listdata.get(holder.getAdapterPosition());

        holder.textViewCustomerName.setText(myListData.name);
        holder.textViewhotelName.setText(myListData.hotelName);
        holder.textViewDate.setText(myListData.dates);
        holder.textViewRoom.setText("Rooms : "+myListData.room);

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewCustomerName;
        public TextView textViewhotelName;
        public TextView textViewRoom;
        public TextView textViewDate;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewCustomerName = (TextView) itemView.findViewById(R.id.textViewCustomerName);
            this.textViewhotelName = (TextView) itemView.findViewById(R.id.textViewhotelName);
            this.textViewRoom = (TextView) itemView.findViewById(R.id.textViewRoom);
            this.textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);

        }
    }
}

