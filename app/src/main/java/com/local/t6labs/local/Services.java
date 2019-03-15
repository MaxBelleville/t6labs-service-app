package com.local.t6labs.local;

import android.content.Context;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Services {
    private String[] servicesName;
    private String[] servicesDesc;
    private String[] users;
    private int[] servicesDistance;
    private String[] servicesImg;
    private int[] servicesID;
    private float[] servicesRating;

    public Services(Context ctx,String url){
        Cache cache = new DiskBasedCache(ctx.getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        RequestQueue queue = new RequestQueue(cache, network);
        queue.start();
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Parse the json information from the git
                        parseJson(response);
                        MainActivity.getList().setAdapter(new ServicesAdapter());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void parseJson(String result){
        try {
            JSONArray jArray = new JSONArray(result);
            servicesDesc = new String[jArray.length()];
            servicesName = new String[jArray.length()];
            servicesRating = new float[jArray.length()];
            servicesDistance = new int[jArray.length()];
            servicesImg= new String[jArray.length()];
            users = new String[jArray.length()];
            servicesID = new int[jArray.length()];
            for(int i=0;i<jArray.length();i++){
                JSONObject obj = jArray.getJSONObject(i);
                // Pulling items from the array
                servicesName[i] = obj.getString("title");
                servicesDesc[i] = obj.getString("description");
                servicesRating[i]= 3.5f;
                servicesID[i]= obj.getInt("id");
                servicesDistance[i]=0;
                servicesImg[i]="";
                users[i]="tempUser";

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getSize(){
        return servicesName.length;
    }

    public String getName(int indx){
        return servicesName[indx];
    }
    public int getDistance(int indx){
        return servicesDistance[indx];
    }
    public String getDescription(int indx){
        return servicesDesc[indx];
    }
    public String getImage(int indx){
        return servicesImg[indx];
    }
    public String getUser(int indx){
        return users[indx];
    }
    public int getId(int indx) {return servicesID[indx];}
    public float getRating(int indx){
        return servicesRating[indx];
    }

}
