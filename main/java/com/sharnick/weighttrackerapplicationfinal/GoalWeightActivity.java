package com.sharnick.weighttrackerapplicationfinal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class GoalWeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_weight);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.goal_weight_fragment_container);

        if (fragment == null) {
            fragment = new GoalWeightFragment();
            fm.beginTransaction()
                    .add(R.id.goal_weight_fragment_container, fragment)
                    .commit();
        }
    }
}