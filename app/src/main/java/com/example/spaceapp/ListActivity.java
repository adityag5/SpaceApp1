package com.example.spaceapp;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;



public class ListActivity {
    Button button;
    public class App2Activity extends Activity {

        Button button;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.list_page);
        }
    }
}
