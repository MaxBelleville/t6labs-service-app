package com.t6labs.locals;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.t6labs.locals.models.LocalsDto;

import java.util.ArrayList;

public class LocalsListAdapter extends RecyclerView.Adapter<LocalsListAdapter.LocalsListViewHolder> {

    private ArrayList<LocalsDto> localsArrayList;

    public LocalsListAdapter(ArrayList<LocalsDto> localsArrayList) {
        this.localsArrayList = localsArrayList;
    }

    @Override
    public LocalsListViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.services_list_layout, parent, false);
        return new LocalsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocalsListViewHolder viewHolder, int i) {

        //TODO handle empty check somewhere else
        if(!localsArrayList.isEmpty()) {

            if(localsArrayList.get(i).getTitle() != null) {
                viewHolder.sName.setText(localsArrayList.get(i).getTitle());
            }

            if(localsArrayList.get(i).getDescription() != null ) {
                viewHolder.sDesc.setText(localsArrayList.get(i).getDescription());
            }

        }

        //TODO remove hardcoded rating
        viewHolder.rating.setRating(3.5f);
        viewHolder.sUser.setText(localsArrayList.get(i).getUserName());
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
            sName = itemView.findViewById(R.id.sName);
            sDesc = itemView.findViewById(R.id.sDescription);
            sUser = itemView.findViewById(R.id.sUser);
            rating = itemView.findViewById(R.id.ratingBar);
        }
    }

}
