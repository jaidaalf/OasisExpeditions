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
import com.example.oasisex.model.RecentsTrip;
import com.example.oasisex.model.TripPackage;

import java.util.List;

   public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder> {
    Context context;
    List<TripPackage> recentsTripList;

       public RecentsAdapter(Context context, List<TripPackage> recentsTripList) {
           this.context = context;
           this.recentsTripList = recentsTripList;
       }

       @NonNull
       @Override

       public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View view= LayoutInflater.from(context).inflate(R.layout.recents_row_trip, parent,false);


           return new RecentsViewHolder(view);
       }

       @Override
       public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {
           TripPackage trip = (TripPackage)  recentsTripList.get(position);
           holder.countryName.setText(recentsTripList.get(position).getTripName());
           holder.placeName.setText(recentsTripList.get(position).getDestination());
           holder.price.setText(""+recentsTripList.get(position).getPrice());
           holder.placeImage.setImageResource(recentsTripList.get(position).getImgSrc());

           holder.placeImage.setOnClickListener(new View.OnClickListener() {
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
           return recentsTripList.size();
       }

       public static final class RecentsViewHolder extends RecyclerView.ViewHolder{
           ImageView placeImage;
           TextView placeName , countryName,price;

           public RecentsViewHolder(@NonNull View itemView) {
               super(itemView);
               placeImage=itemView.findViewById(R.id.place_image2);
               placeName=itemView.findViewById(R.id.place_name2);
               countryName=itemView.findViewById(R.id.country_name2);
               price=itemView.findViewById(R.id.price);
           }
       }


}