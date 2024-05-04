package com.example.oasisex.adapter;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.oasisex.ManagementCart;
import com.example.oasisex.model.TripPackage;
import com.example.oasisex.*;
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewholder> {
    ArrayList<TripPackage> list;
    private ManagementCart managementCart;

    public CartAdapter(ArrayList<TripPackage> list, Context context) {
        this.list = list;
        managementCart = new ManagementCart(context);
    }

    @NonNull
    @Override
    public CartAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewholder holder, int position) {
        TripPackage item = list.get(position);

        holder.title.setText(item.getDestination());
        holder.feeEachItem.setText(String.format("$%.2f", item.getPrice()));
        holder.totalEachItem.setText(String.format("1 x $%.2f", item.getPrice()));
        holder.num.setText("1");

        /*Glide.with(holder.itemView.getContext())
                .load(item.getImageURL())  // Make sure getImageURL() is defined in TripPackageDomain.
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);*/

        /*holder.plusItem.setOnClickListener(v -> {
            managementCart.plusNumberItem(list, position);
            notifyDataSetChanged();
        });

        holder.minusItem.setOnClickListener(v -> {
            managementCart.minusNumberItem(list, position);
            notifyDataSetChanged();
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, plusItem, minusItem, totalEachItem, num;
        ImageView pic;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            //plusItem = itemView.findViewById(R.id.plusCartBtn);
            //minusItem = itemView.findViewById(R.id.minusCartBtn);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.cancelLbl);
        }
    }
}
