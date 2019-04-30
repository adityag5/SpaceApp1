package com.example.lib;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AsteroidTracker {

    private static String asteroidName;

    private static String asteroidUrl;

    private static String closeApproachDate;

    public static String getAsteroidName(final JSONObject asteroid) throws JSONException {
        System.out.println(asteroid.getString("name"));
        asteroidName = asteroid.getString("name");
        return asteroidName;
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
    public static String getAsteroidUrl(final JSONObject asteroid) throws JSONException {
        asteroidUrl = asteroid.getString("nasa_jpl_url");
        return asteroidUrl;
    }
    public String getOrbitBody(final JSONObject asteroid) throws JSONException {
        JSONArray approachArray = asteroid.getJSONArray("close_approach_data");
        return approachArray.getJSONObject(4).toString();
    }
    public boolean isHazardous(final JSONObject asteroid) throws JSONException {
        return asteroid.getBoolean("is_potentially_hazardous_asteroid");
    }
    public static String getCloseApproachDate(final JSONObject asteroid) throws JSONException {
        JSONArray approachArray = asteroid.getJSONArray("close_approach_data");
        closeApproachDate = approachArray.getJSONObject(0).getString("close_approach_date");
        return closeApproachDate;
    }

}
