package com.t6labs.locals.MyListings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.t6labs.locals.R;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.LocalsListViewHolder> {

    @Override
    @NonNull
    public MyListAdapter.LocalsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.my_listings_item, parent, false);

        return new MyListAdapter.LocalsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.LocalsListViewHolder viewHolder, int i) {
        //TODO Implement Dto.

        viewHolder.rating.setRating(3.5f);
        viewHolder.sName.setText("My Listing");
        viewHolder.sDesc.setText("Please change this later.");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class LocalsListViewHolder extends RecyclerView.ViewHolder {

        TextView sName;
        TextView sDesc;
        RatingBar rating;

        LocalsListViewHolder(View itemView) {
            super(itemView);

           // itemView.setOnClickListener(v -> listener.onItemClick(v, getAdapterPosition()));

            sName = itemView.findViewById(R.id.myListTitle);
            sDesc = itemView.findViewById(R.id.myListDescription);
            rating = itemView.findViewById(R.id.myListRating);
        }
    }
}