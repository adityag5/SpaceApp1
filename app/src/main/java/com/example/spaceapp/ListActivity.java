package com.example.spaceapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.lib.AsteroidTracker;

import java.util.ArrayList;
import java.util.HashMap;


public class ListActivity extends AppCompatActivity {

    public ArrayList<HashMap<String, String>> list = new ArrayList<>();
    private SimpleAdapter sa;

    private RequestQueue requestQueue;
    String apiKey = "wPXnu2yQI5wMCoafE8wNuldzqV3NySggOfW94ooQ";
    String requestUrl = "https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=wPXnu2yQI5wMCoafE8wNuldzqV3NySggOfW94ooQ";
    TextView asteroidTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        asteroidTextView = findViewById(R.id.AsteroidText);


        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest asteroidObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrl,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray objectArray = response.getJSONArray("near_earth_objects");
                    for (int i = 0; i < objectArray.length(); i++) {
                        JSONObject asteroid = objectArray.getJSONObject(i);
                        asteroidTextView.append(AsteroidTracker.getAsteroidName(asteroid) + ", ");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HashMap<String,String> item;
                for(int i = 0; i < AsteroidMultiArray.length; i++){
                    item = new HashMap<String,String>();
                    item.put( "line1", AsteroidMultiArray[i][0]);
                    item.put( "line2", AsteroidMultiArray[i][1]);
                    item.put( "line3". AsteroidMultiArray[i][2]);
                    item.put( "line4", AsteroidMultiArray[i][3]);
                    list.add(item);
                }
                sa = new SimpleAdapter(this, AsteroidMultiArray,
                        R.layout.individual,
                        new String[] { "line1", "line2", "line3", "line4"},
                        new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d} );
                ((ListView)findViewById(R.id.list)).setAdapter(sa);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(asteroidObjectRequest);
    }


}
