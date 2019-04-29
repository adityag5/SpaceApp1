package com.example.lib;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AsteroidTracker {

    private static JsonParser newParser = new JsonParser();


    public String getAsteroidName(final JSONObject asteroid) throws JSONException {
        return asteroid.getString("name");
    }
    public String getAsteroidSizeMeters(final JSONObject asteroid) throws JSONException {
        JSONObject diameter = asteroid.getJSONObject("estimated_diameter");
        JSONObject meters = diameter.getJSONObject("meters");
        return meters.getString("estimated_diameter_min");
    }
    public String getAsteroidUrl(final JSONObject asteroid) throws JSONException {
        return asteroid.getString("nasa_jpl_url");
    }
}
