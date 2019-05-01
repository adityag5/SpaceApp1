package com.example.spaceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.net.Uri;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });
        final Button nasaNewsButton = findViewById(R.id.NASANewsButton);
        nasaNewsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent("android.intent.action.VIEW");
                newIntent.setData(Uri.parse("https://www.nasa.gov/asteroid-and-comet-watch"));
                startActivity(newIntent);
            }
        });
        final Button asteroidButton = findViewById(R.id.AsteroidFactsButton);
        asteroidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent astIntent = new Intent("android.intent.action.VIEW");
                astIntent.setData(Uri.parse("https://www.nasa.gov/mission_pages/asteroids/overview/fastfacts.html"));
                startActivity(astIntent);
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
