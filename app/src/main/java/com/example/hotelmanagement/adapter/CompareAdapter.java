package com.example.hotelmanagement.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagement.HotelDetailsActivity;
import com.example.hotelmanagement.R;
import com.example.hotelmanagement.model.Hotel;

import java.io.Serializable;
import java.util.ArrayList;

public class CompareAdapter extends RecyclerView.Adapter<CompareAdapter.ViewHolder>{
    private ArrayList<Hotel> listdata;
   Activity activity;
    // RecyclerView recyclerView;
    public CompareAdapter(ArrayList<Hotel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity=activity;
    }



//    public HotelAdapter(ArrayList data) {
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.hotel_details, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }




    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Hotel myListData =  listdata.get(holder.getAdapterPosition());

        holder.textViewName.setText( myListData.hotelName);
        holder.hotelImageView.setImageResource(myListData.image);
        holder.textViewDescription.setText( myListData.hotelDesc);
        holder.textViewStar.setText( myListData.hotelStar+" Star");
        holder.textViewRating.setText( myListData.hotelRating+" Rating");
        holder.textViewAminities.setText( myListData.amnities);
holder.buttonCompare.setVisibility(View.GONE);
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
        public ImageView hotelImageView;
        Button buttonCompare;
        public ViewHolder(View itemView) {
            super(itemView);
            this.hotelImageView = (ImageView) itemView.findViewById(R.id.imageViewHotel);
            this.buttonCompare = (Button) itemView.findViewById(R.id.compare);
            this.textViewName = (TextView) itemView.findViewById(R.id.textviewHotelName);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.textViewDesction);
            this.textViewStar = (TextView) itemView.findViewById(R.id.textviewHotelStar);
            this.textViewRating = (TextView) itemView.findViewById(R.id.textviewHotelRAting);
            this.textViewAminities = (TextView) itemView.findViewById(R.id.textViewAminities);
        }
    }
}

