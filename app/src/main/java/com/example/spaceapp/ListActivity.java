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
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ListActivity extends AppCompatActivity {

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

        final ListView results = (ListView) findViewById(R.id.list);

        asteroidTextView = findViewById(R.id.AsteroidText);

        final List<HashMap<String,String>> list = new ArrayList<>();

        final SimpleAdapter sa = new SimpleAdapter(this, list,
                R.layout.individual,
                new String[] { "line1", "line2", "line3", "line4"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d} );


        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest asteroidObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrl,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray objectArray = response.getJSONArray("near_earth_objects");
                    String[][] asteroidMultiArray = new String[objectArray.length()][4];

                    for (int i = 0; i < objectArray.length(); i++) {

                        JSONObject asteroid = objectArray.getJSONObject(i);

                        asteroidMultiArray[i][0] = AsteroidTracker.getAsteroidName(asteroid);
                        asteroidMultiArray[i][1] = AsteroidTracker.getAsteroidUrl(asteroid);
                        asteroidMultiArray[i][2] = AsteroidTracker.getNeoReferenceId(asteroid);
                        asteroidMultiArray[i][3] = AsteroidTracker.getOrbitalPeriod(asteroid);


                        //asteroidTextView.append(AsteroidTracker.getCloseApproachDate(asteroid));
                    }
                    HashMap<String,String> item = new HashMap<>();
                    for(int i = 0; i < asteroidMultiArray.length; i++){
                        item.put( "line1", asteroidMultiArray[i][0]);
                        item.put( "line2", asteroidMultiArray[i][1]);
                        item.put( "line3", asteroidMultiArray[i][2]);
                        item.put( "line4", asteroidMultiArray[i][3]);
                    }

                    Iterator it = item.entrySet().iterator();
                    while(it.hasNext()) {
                        HashMap<String, String> resultsMap = new HashMap<>();
                        Map.Entry pair = (Map.Entry)it.next();
                        resultsMap.put("line1", pair.getKey().toString());
                        resultsMap.put("line2", pair.getKey().toString());
                        resultsMap.put("line3", pair.getKey().toString());
                        resultsMap.put("line4", pair.getKey().toString());
                        list.add(resultsMap);

                    }
                    results.setAdapter(sa);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(asteroidObjectRequest);
        //((ListView)findViewById(R.id.list)).setAdapter(sa);
    }
}
