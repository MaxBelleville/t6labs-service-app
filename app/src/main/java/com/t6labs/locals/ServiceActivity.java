package com.t6labs.locals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
      /*  Intent intent = getIntent();
        TextView title = (TextView)findViewById(R.id.title);
        TextView desc = (TextView)findViewById(R.id.description);
        Services services = MainActivity.getServices();
        if(intent.hasExtra("com.t6labs.MainActivity")) {
            int id = intent.getIntExtra("com.t6labs.MainActivity",0);
            for(int i=0; i<services.getSize();i++){
                if(id==services.getId(i)){
                    title.setText(services.getName(i));
                    desc.setText(services.getDescription(i));
                }
            }
        }*/
    }
}
