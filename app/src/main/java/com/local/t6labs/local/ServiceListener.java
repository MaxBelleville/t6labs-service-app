package com.local.t6labs.local;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ServiceListener implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Services services;
    private Context appContext;
    public ServiceListener(Services services) {
        this.services=services;
        recyclerView=MainActivity.getList();
        appContext =MainActivity.getAppContext();
    }
    @Override
    public void onClick(View v) {
        int itemPos = recyclerView.getChildLayoutPosition(v);
        Intent intent = new Intent(appContext,ServiceActivity.class);
        intent.putExtra("com.t6labs.MainActivity",services.getId(itemPos));
        appContext.startActivity(intent);
    }
}
