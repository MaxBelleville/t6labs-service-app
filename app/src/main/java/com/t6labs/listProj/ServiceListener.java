package com.t6labs.listProj;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class ServiceListener implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Services services;
    private Context appContext;
    public ServiceListener(Services services) {
        this.services=services;
        recyclerView=ListActivity.getList();
        appContext =ListActivity.getAppContext();
    }
    @Override
    public void onClick(View v) {
        int itemPos = recyclerView.getChildLayoutPosition(v);
        Intent intent = new Intent(appContext,ServiceInfoActivity.class);
        intent.putExtra("com.t6labs.ListActivity",services.getId(itemPos));
        appContext.startActivity(intent);
    }
}
