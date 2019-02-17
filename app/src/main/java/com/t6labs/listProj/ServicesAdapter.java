package com.t6labs.listProj;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class ServicesAdapter  extends
        RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
    private Services services;
    private ServiceListener serviceListener;
    // Pass in the contact array into the constructor
    public ServicesAdapter() {
        this.services=ListActivity.getServices();
        serviceListener = new ServiceListener(services);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View servicesView = inflater.inflate(R.layout.services_list_layout, viewGroup, false);
        servicesView.setOnClickListener(serviceListener);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(servicesView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ServicesAdapter.ViewHolder viewHolder, int i) {

        // Set item views based on your views and data model
        TextView serviceName = viewHolder.sName;
        serviceName.setText(services.getName(i));
        TextView serviceDesc = viewHolder.sDesc;
        serviceDesc.setText(services.getDescription(i));
        TextView users = viewHolder.user;
        users.setText(services.getUser(i));
        RatingBar ratings = viewHolder.rating;
        ratings.setRating(services.getRating(i));
    }

    @Override
    public int getItemCount() {
        return services.getSize();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView sName;
        public TextView sDesc;
        public TextView user;
        public RatingBar rating;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            sName = (TextView) itemView.findViewById(R.id.sName);
            sDesc =(TextView) itemView.findViewById(R.id.sDescription);
            user = (TextView) itemView.findViewById(R.id.user);
            rating = (RatingBar) itemView.findViewById(R.id.ratingBar);
        }
    }
}
