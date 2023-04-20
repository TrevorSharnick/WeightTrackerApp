package com.sharnick.weighttrackerapplicationfinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class WeightListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        // Replace the fragment container with the WeightListFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_fragment_container, new WeightListFragment())
                .commit();
    }


}

