package com.example.spaceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    String apiKey = "wPXnu2yQI5wMCoafE8wNuldzqV3NySggOfW94ooQ";
    String requestUrl = "https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=wPXnu2yQI5wMCoafE8wNuldzqV3NySggOfW94ooQ";
    TextView asteroidTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final ListView results = (ListView) findViewById(R.id.list);

        asteroidTextView = findViewById(R.id.AsteroidText);

        final List<HashMap<String,String>> list = new ArrayList<>();



        final SimpleAdapter sa = new SimpleAdapter(this, list,
                R.layout.individual,
                new String[] { "line1", "line2", "line3", "line4", "line5", "line6", "line7", "line8"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e, R.id.line_f, R.id.line_g, R.id.line_h} );


        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest asteroidObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrl,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray objectArray = response.getJSONArray("near_earth_objects");
                    String[][] asteroidMultiArray = new String[objectArray.length()][8];

                    for (int i = 0; i < objectArray.length(); i++) {

                        JSONObject asteroid = objectArray.getJSONObject(i);

                        asteroidMultiArray[i][0] = AsteroidTracker.getAsteroidName(asteroid);
                        asteroidMultiArray[i][1] = AsteroidTracker.getAsteroidUrl(asteroid);
                        asteroidMultiArray[i][2] = AsteroidTracker.getCloseApproachDate(asteroid);
                        asteroidMultiArray[i][3] = AsteroidTracker.getOrbitBody(asteroid);
                        asteroidMultiArray[i][4] = AsteroidTracker.getAsteroidSizeMetersMin(asteroid);
                        asteroidMultiArray[i][5] = AsteroidTracker.getAsteroidSizeMetersMax(asteroid);
                        asteroidMultiArray[i][6] = AsteroidTracker.getIsHazardous(asteroid);
                        asteroidMultiArray[i][7] = AsteroidTracker.getOrbitalPeriod(asteroid);

                    }
                    HashMap<String, String> item = new HashMap<>();
                    for (int i = 0; i < asteroidMultiArray.length; i++) {
                        item.put(asteroidMultiArray[i][0], asteroidMultiArray[i][1]);
                        item.put(asteroidMultiArray[i][0], asteroidMultiArray[i][1]);
                        item.put(asteroidMultiArray[i][0], asteroidMultiArray[i][2]);
                        item.put(asteroidMultiArray[i][0], asteroidMultiArray[i][3]);

                        HashMap<String, String> resultsMap = new HashMap<>();

                        resultsMap.put("line1", "Name : " + asteroidMultiArray[i][0]);
                        resultsMap.put("line2", "JPL URL : " + asteroidMultiArray[i][1]);
                        resultsMap.put("line3", "Close Approach Date : " + asteroidMultiArray[i][2]);
                        resultsMap.put("line4", "Orbit Body : " + asteroidMultiArray[i][3]);
                        resultsMap.put("line5", "Minimum Asteroid Size (meters) : " + asteroidMultiArray[i][4]);
                        resultsMap.put("line6", "Maximum Asteroid Size (meters) : " + asteroidMultiArray[i][5]);
                        resultsMap.put("line7", "Is it Hazardous? : " + asteroidMultiArray[i][6]);
                        resultsMap.put("line8", "Orbital Period (days) : " + asteroidMultiArray[i][7]);
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

    }
}
