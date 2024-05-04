package com.example.oasisex.adapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oasisex.DatabaseHelper;
import com.example.oasisex.DetailsPage;
import com.example.oasisex.EditPage;
import com.example.oasisex.PayPage;
import com.example.oasisex.Trips;
import com.example.oasisex.model.MyTrip;
import com.example.oasisex.R;
import com.example.oasisex.model.MyTrip;
import com.example.oasisex.model.TripPackage;

import java.util.List;

public class MyTripAdapter extends RecyclerView.Adapter<MyTripAdapter.AllTripViewHolder> {
    private final DatabaseHelper dbHelper;
    Context context;
    List<MyTrip> myTripList;

    public MyTripAdapter(Context context, List<MyTrip> myTripList) {
        this.context = context;
        this.myTripList= myTripList;

        dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override

    public AllTripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.res_trip, parent,false);


        return new AllTripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllTripViewHolder holder, int position) {
        holder.countryName.setText(myTripList.get(position).getCountry());
        holder.placeName.setText(myTripList.get(position).getPlaceName());
        holder.price.setText(myTripList.get(position).getPrice());
        holder.placeImage.setImageResource(myTripList.get(position).getImageUrl());
        holder.goDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tripID = myTripList.get(position).getTrip_id();
                TripPackage tripPackage = (TripPackage) dbHelper.getTripPackageDetails(tripID);
                Intent i = new Intent(context, DetailsPage.class);
                i.putExtra("trip", tripPackage);
                i.putExtra("packageId", tripID);
                context.startActivity(i);
            }
        });
        holder.placeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tripID = myTripList.get(position).getTrip_id();
                TripPackage tripPackage = (TripPackage) dbHelper.getTripPackageDetails(tripID);
                Intent i = new Intent(context, DetailsPage.class);
                i.putExtra("trip", tripPackage);
                i.putExtra("packageId", tripID);
                context.startActivity(i);
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Confirm of deleting")
                        .setMessage("Are you sure you want to delete this book?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DatabaseHelper db = new DatabaseHelper(context);
                                int book_id = myTripList.get(position).getId();
                                if(db.deleteUserTripPackage(  book_id ) ){
                                    Toast.makeText(context, "book deleted successfully", Toast.LENGTH_LONG).show();
                                    myTripList.remove(position);
                                    notifyDataSetChanged();
                                }else{
                                    Toast.makeText(context, "book not deleted", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();

            }
        });
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tripID = myTripList.get(position).getTrip_id();
                int book_id = myTripList.get(position).getId();
                TripPackage tripPackage = (TripPackage) dbHelper.getTripPackageDetails(tripID);
                Intent i = new Intent(context, EditPage.class);
                i.putExtra("trip", tripPackage);
                i.putExtra("book_id", book_id);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myTripList.size();
    }

    public void updateList(List<MyTrip> newList) {
        myTripList.clear();
        myTripList.addAll(newList);
        notifyDataSetChanged();
    }


    public static final class AllTripViewHolder extends RecyclerView.ViewHolder{
        ImageView placeImage,goDetailsBtn;
        TextView placeName , countryName,price;
        Button deleteBtn, editBtn;

        public AllTripViewHolder(@NonNull View itemView) {
            super(itemView);
            placeImage=itemView.findViewById(R.id.place_image2);
            goDetailsBtn=itemView.findViewById(R.id.goDetailsBtn);
            placeName=itemView.findViewById(R.id.place_name2);
            countryName=itemView.findViewById(R.id.country_name2);
            price=itemView.findViewById(R.id.price);
            editBtn=itemView.findViewById(R.id.editBtn);
            deleteBtn=itemView.findViewById(R.id.deleteBtn);
        }
    }


}
