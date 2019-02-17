package com.t6labs.listProj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ServiceInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info);
        Intent intent = getIntent();
        TextView title = (TextView)findViewById(R.id.title);
        TextView desc = (TextView)findViewById(R.id.description);
        Services services =ListActivity.getServices();
        if(intent.hasExtra("com.t6labs.ListActivity")) {
            int id = intent.getIntExtra("com.t6labs.ListActivity",0);
            for(int i=0; i<services.getSize();i++){
                if(id==services.getId(i)){
                    title.setText(services.getName(i));
                    desc.setText(services.getDescription(i));
                }
            }
        }
    }
}
