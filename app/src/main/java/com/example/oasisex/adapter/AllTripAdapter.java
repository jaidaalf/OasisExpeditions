package com.example.oasisex.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oasisex.DetailsPage;
import com.example.oasisex.R;
import com.example.oasisex.model.AllTrip;
import com.example.oasisex.model.TripPackage;

import java.util.List;

public class AllTripAdapter extends RecyclerView.Adapter<AllTripAdapter.AllTripViewHolder> {
 Context context;
 List<TripPackage> allTripList;

    public AllTripAdapter(Context context, List<TripPackage> allTripList) {
        this.context = context;
        this.allTripList = allTripList;
    }

    @NonNull
    @Override

    public AllTripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.all_row_trip, parent,false);


        return new AllTripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllTripViewHolder holder, int position) {
        TripPackage trip = (TripPackage)  allTripList.get(position);
        holder.countryName.setText(allTripList.get(position).getTripName());
        holder.placeName.setText(allTripList.get(position).getDestination());
        holder.price.setText(""+allTripList.get(position).getPrice());
        holder.placeImage.setImageResource(allTripList.get(position).getImgSrc());
        holder.placeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailsPage.class);
                i.putExtra("trip", trip);
                i.putExtra("packageId", trip.getPakckage_id());
                context.startActivity(i);
            }
        });
        holder.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailsPage.class);
                i.putExtra("trip", trip);
                i.putExtra("packageId", trip.getPakckage_id());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allTripList.size();
    }

    public static final class AllTripViewHolder extends RecyclerView.ViewHolder{
        ImageView placeImage,imageView5;
        TextView placeName , countryName,price;

        public AllTripViewHolder(@NonNull View itemView) {
            super(itemView);
            placeImage=itemView.findViewById(R.id.place_image2);
            imageView5=itemView.findViewById(R.id.imageView5);
            placeName=itemView.findViewById(R.id.place_name2);
            countryName=itemView.findViewById(R.id.country_name2);
            price=itemView.findViewById(R.id.price);

        }
    }


}
