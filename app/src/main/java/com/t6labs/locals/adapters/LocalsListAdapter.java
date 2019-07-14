package com.t6labs.locals.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.t6labs.locals.R;
import com.t6labs.locals.models.LocalsDto;

import java.util.ArrayList;

public class LocalsListAdapter extends RecyclerView.Adapter<LocalsListAdapter.LocalsListViewHolder> {

    private ArrayList<LocalsDto> localsArrayList;
    LocalsListingClickListener listener;

    public LocalsListAdapter(ArrayList<LocalsDto> localsArrayList, LocalsListingClickListener listener) {
        this.localsArrayList = localsArrayList;
        this.listener = listener;
    }

    @Override
    public LocalsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.services_list_layout, parent, false);

        final LocalsListViewHolder viewHolder = new LocalsListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LocalsListViewHolder viewHolder, int i) {
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