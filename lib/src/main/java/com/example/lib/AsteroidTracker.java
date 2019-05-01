package com.example.lib;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AsteroidTracker {

    private static String asteroidName;

    private static String asteroidUrl;

    private static String closeApproachDate;

    private static String orbitBody;

    private static String orbitPeriod;

    private static String asteroidSizeMetersMin;

    private static String asteroidSizeMetersMax;

    private static String isHazardous;




    public static String getAsteroidName(final JSONObject asteroid) throws JSONException {
        System.out.println(asteroid.getString("name"));
        asteroidName = asteroid.getString("name");
        return asteroidName;
    }
    public static String getAsteroidSizeMetersMin(final JSONObject asteroid) throws JSONException {
        JSONObject diameter = asteroid.getJSONObject("estimated_diameter");
        JSONObject meters = diameter.getJSONObject("meters");
        int minSize = meters.getInt("estimated_diameter_min");
        asteroidSizeMetersMin = Integer.toString(minSize);
        return asteroidSizeMetersMin;
    }
    public static String getAsteroidSizeMetersMax(final JSONObject asteroid) throws JSONException {
        JSONObject diameter = asteroid.getJSONObject("estimated_diameter");
        JSONObject meters = diameter.getJSONObject("meters");
        int maxSize = meters.getInt("estimated_diameter_max");
        asteroidSizeMetersMax = Integer.toString(maxSize);
        return asteroidSizeMetersMax;

    }
    public static String getAsteroidUrl(final JSONObject asteroid) throws JSONException {
        asteroidUrl = asteroid.getString("nasa_jpl_url");
        return asteroidUrl;
    }
    public static String getOrbitBody(final JSONObject asteroid) throws JSONException {
        JSONArray approachArray = asteroid.getJSONArray("close_approach_data");
        if (approachArray.length() == 0) {
            orbitBody = "Unknown";
        }
        for (int i = 0; i < approachArray.length(); i++) {
            orbitBody = approachArray.getJSONObject(i).getString("orbiting_body");
        }
        return orbitBody;
    }
    public static String getIsHazardous(final JSONObject asteroid) throws JSONException {
        Boolean check = asteroid.getBoolean("is_potentially_hazardous_asteroid");
        if (check) {
            isHazardous = "Yes";
            return isHazardous;
        } else {
            isHazardous = "No";
            return isHazardous;
        }
    }
    public static String getCloseApproachDate(final JSONObject asteroid) throws JSONException {
        JSONArray approachArray = asteroid.getJSONArray("close_approach_data");
        if (approachArray.length() == 0) {
            closeApproachDate = "Unknown";
        }
        for (int i = 0; i < approachArray.length(); i++) {
            closeApproachDate = approachArray.getJSONObject(i).getString("close_approach_date");
        }
        return closeApproachDate;
    }
    public static String getOrbitalPeriod(final JSONObject asteroid) throws JSONException {
        JSONObject orbitalData = asteroid.getJSONObject("orbital_data");
        orbitPeriod = orbitalData.getString("orbital_period");
        return orbitPeriod;

    }

}
