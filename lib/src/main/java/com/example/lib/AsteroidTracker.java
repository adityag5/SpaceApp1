package com.example.lib;
//import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AsteroidTracker {

    AsteroidTracker(){
    }


    public static String getAsteroidName(final JSONObject asteroid) throws JSONException {
        System.out.println(asteroid.getString("name"));
        return asteroid.getString("name");
    }
    public int getAsteroidSizeMetersMin(final JSONObject asteroid) throws JSONException {
        JSONObject diameter = asteroid.getJSONObject("estimated_diameter");
        JSONObject meters = diameter.getJSONObject("meters");
        return meters.getInt("estimated_diameter_min");
    }
    public int getAsteroidSizeMetersMax(final JSONObject asteroid) throws JSONException {
        JSONObject diameter = asteroid.getJSONObject("estimated_diameter");
        JSONObject meters = diameter.getJSONObject("meters");
        return meters.getInt("estimated_diameter_max");
    }
    public String getAsteroidUrl(final JSONObject asteroid) throws JSONException {
        return asteroid.getString("nasa_jpl_url");
    }
}
