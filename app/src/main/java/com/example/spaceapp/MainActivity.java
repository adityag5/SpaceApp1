package com.example.spaceapp;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.net.Uri;
import android.content.Intent;
import android.app.Activity;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;


public class MainActivity extends AppCompatActivity {

    private TextView asteroidTextView;
    private RequestQueue requestQueue;

    String apiKey = "wPXnu2yQI5wMCoafE8wNuldzqV3NySggOfW94ooQ";

    String requestUrl = "https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=wPXnu2yQI5wMCoafE8wNuldzqV3NySggOfW94ooQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        asteroidTextView = findViewById(R.id.AsteroidView);

        final Button asteroidVideoButton = findViewById(R.id.button);
        asteroidVideoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW");
                viewIntent.setData(Uri.parse("https://www.youtube.com/watch?v=bU1QPtOZQZU"));
                startActivity(viewIntent);
            }
        });
        final Button asteroidListButton = findViewById(R.id.button2);
        asteroidListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest asteroidObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrl,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray objectArray = response.getJSONArray("near_earth_objects");
                            for (int i = 0; i < objectArray.length(); i++) {
                                JSONObject asteroid = objectArray.getJSONObject(i);
                                String name = asteroid.getString("name");
                                asteroidTextView.append(name + ", ");
                            }
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
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
