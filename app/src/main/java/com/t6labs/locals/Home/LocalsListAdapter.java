package com.t6labs.locals.Home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.t6labs.locals.Dtos.LocalsDto;
import com.t6labs.locals.R;

import java.util.ArrayList;

public class LocalsListAdapter extends RecyclerView.Adapter<LocalsListAdapter.LocalsListViewHolder> {

    private ArrayList<LocalsDto> localsArrayList;
    private LocalsListingClickListener listener;

    LocalsListAdapter(ArrayList<LocalsDto> localsArrayList, LocalsListingClickListener listener) {
        this.localsArrayList = localsArrayList;
        this.listener = listener;
    }

    @Override
    @NonNull
    public LocalsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.services_list_layout, parent, false);

        return new LocalsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalsListViewHolder viewHolder, int i) {
        viewHolder.rating.setRating(3.5f);
        viewHolder.sUser.setText("John Doe");
        //TODO handle empty check somewhere else
        if (!localsArrayList.isEmpty()) {

            if (localsArrayList.get(i).getTitle() != null) {
                viewHolder.sName.setText(localsArrayList.get(i).getTitle());
            }

            if (localsArrayList.get(i).getDescription() != null) {
                viewHolder.sDesc.setText(localsArrayList.get(i).getDescription());
            }
            if(localsArrayList.get(i).getUsername()!=null){
                viewHolder.sUser.setText(localsArrayList.get(i).getUsername());
            }
        }

        //TODO remove hardcoded rating

    }

    @Override
    public int getItemCount() {
        return localsArrayList.size();
    }

    class LocalsListViewHolder extends RecyclerView.ViewHolder {

        TextView sName;
        TextView sDesc;
        TextView sUser;
        RatingBar rating;

        LocalsListViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> listener.onItemClick(v, getAdapterPosition()));

            sName = itemView.findViewById(R.id.listTitle);
            sDesc = itemView.findViewById(R.id.listDescription);
            sUser = itemView.findViewById(R.id.listUser);
            rating = itemView.findViewById(R.id.listRating);
        }
    }

}