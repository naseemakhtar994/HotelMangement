package com.example.hotelmanagement.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagement.R;
import com.example.hotelmanagement.model.BookingModel;

import java.util.ArrayList;

public class ReservasationAdapter extends RecyclerView.Adapter<ReservasationAdapter.ViewHolder>{
    private ArrayList<BookingModel> listdata;
   Activity activity;
    // RecyclerView recyclerView;
    public ReservasationAdapter(ArrayList<BookingModel> listdata, Activity activity) {
        this.listdata = listdata;
        this.activity=activity;
    }



//    public HotelAdapter(ArrayList data) {
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.custom_item_reservation, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }




    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final BookingModel myListData =  listdata.get(holder.getAdapterPosition());


        holder.textViewDate.setText("Date : "+myListData.booking_date);
        holder.textViewEarning.setText("£ "+myListData.earning);

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewEarning;
        public TextView textViewDate;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewEarning = (TextView) itemView.findViewById(R.id.textViewEarning);
            this.textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);


        }
    }
}

