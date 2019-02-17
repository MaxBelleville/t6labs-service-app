package  com.t6labs.listProj;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Services {
    private String[] servicesName;
    private String[] servicesDesc;
    private String[] users;
    private int[] servicesID;
    private float[] servicesRating;

    public Services(Context ctx,String url){
        RequestQueue queue = Volley.newRequestQueue(ctx);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Parse the json information from the git

                        parseJson(response);
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
            users = new String[jArray.length()];
            servicesID = new int[jArray.length()];
            for(int i=0;i<jArray.length();i++){
                JSONObject obj = jArray.getJSONObject(i);
                // Pulling items from the array
                servicesName[i] = obj.getString("title");
                servicesDesc[i] = obj.getString("description");
                servicesRating[i]= 0.0f;
                servicesID[i]= obj.getInt("id");
                users[i]="tempUser";

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getSize(){
        return servicesName.length;
    }
    public String[] getNames(){
        return servicesName;
    }
    public String[] getDescriptions(){
        return servicesDesc;
    }
    public String[] getUsers(){
        return users;
    }
    public int[] getIds() {return servicesID;}
    public float[] getRatings(){
        return servicesRating;
    }
    public String getName(int indx){
        return servicesName[indx];
    }
    public String getDescription(int indx){
        return servicesDesc[indx];
    }
    public String getUser(int indx){
        return users[indx];
    }
    public int getId(int indx) {return servicesID[indx];}
    public float getRating(int indx){
        return servicesRating[indx];
    }

}
