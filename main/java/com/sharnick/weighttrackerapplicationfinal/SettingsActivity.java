
package com.sharnick.weighttrackerapplicationfinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.settings_fragment_container);

        if (fragment == null) {
            fragment = new SettingsFragment();
            fm.beginTransaction()
                    .add(R.id.settings_fragment_container, fragment)
                    .commit();
        }
    }
    @Override
    public void onBackPressed() {
        // Call onResume() for WeightListFragment
        FragmentManager fm = getSupportFragmentManager();
        WeightListFragment weightListFragment = (WeightListFragment) fm.findFragmentById(R.id.list_fragment_container);
        if (weightListFragment != null) {
            weightListFragment.onResume();
        }
        super.onBackPressed();
    }


}

