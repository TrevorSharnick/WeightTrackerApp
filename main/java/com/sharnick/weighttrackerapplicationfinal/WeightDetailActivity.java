package com.sharnick.weighttrackerapplicationfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * The WeightDetailActivity is an activity that displays the details of a single weight entry.
 * It retrieves the weightId from its intent and uses it to fetch the corresponding WeightModel from the WeightLab.
 * It then creates a new instance of the WeightDetailFragment and passes the weightId as an argument.
 * Finally, it uses a FragmentManager and a FragmentTransaction to add the WeightDetailFragment to the FrameLayout.
 */
public class WeightDetailActivity extends AppCompatActivity {
    private static final String EXTRA_WEIGHT_ID = "com.sharnick.weighttrackerapplicationfinal.weight_id";

    private WeightModel mWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_detail);

        long weightId = getIntent().getLongExtra(EXTRA_WEIGHT_ID, -1);
        if (weightId == -1) {
            finish();
        }

        mWeight = WeightLab.get(this).getWeight(weightId);

        // Create a new instance of WeightDetailFragment and pass the weightId as an argument
        WeightDetailFragment fragment = WeightDetailFragment.newInstance(weightId);

        // Use a FragmentManager and a FragmentTransaction to add the WeightDetailFragment to the FrameLayout
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.weight_detail_fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public static Intent newIntent(Context packageContext, long weightId) {
        Intent intent = new Intent(packageContext, WeightDetailActivity.class);
        intent.putExtra(EXTRA_WEIGHT_ID, weightId);
        return intent;
    }
}
