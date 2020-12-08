package com.example.hotelmanagement.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.hotelmanagement.HotelDetailsActivity;
import com.example.hotelmanagement.R;
import com.example.hotelmanagement.model.Hotel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder>{
    private ArrayList<Hotel> listdata;
   Activity activity;
    // RecyclerView recyclerView;
    public HotelAdapter(ArrayList<Hotel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity=activity;
    }



//    public HotelAdapter(ArrayList data) {
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.itme_hotel_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }




    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Hotel myListData =  listdata.get(holder.getAdapterPosition());

        holder.textViewName.setText( myListData.hotelName);
        holder.hotelImageView.setImageResource(myListData.image);
        holder.textViewDescription.setText( myListData.hotelShortDesc);
        holder.textViewStar.setText( myListData.hotelStar);
        holder.textViewRating.setText( myListData.hotelRating);
        holder.textViewAminities.setText( myListData.amnities);
holder.pernightPrice.setText("£"+myListData.price);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, HotelDetailsActivity.class);
                Bundle bundle=new Bundle();
                intent.putExtra("hotel", (Serializable) myListData);
                activity.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewDescription;
        public TextView textViewStar;
        public TextView textViewRating;
        public TextView textViewAminities;
        public TextView pernightPrice;
        public ImageView hotelImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.hotelImageView = (ImageView) itemView.findViewById(R.id.imageViewHotel);
            this.textViewName = (TextView) itemView.findViewById(R.id.textviewHotelName);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.textViewDesction);
            this.pernightPrice = (TextView) itemView.findViewById(R.id.pernightPrice);
            this.textViewStar = (TextView) itemView.findViewById(R.id.textviewHotelStar);
            this.textViewRating = (TextView) itemView.findViewById(R.id.textviewHotelRAting);
            this.textViewAminities = (TextView) itemView.findViewById(R.id.textViewAminities);
        }
    }
}

