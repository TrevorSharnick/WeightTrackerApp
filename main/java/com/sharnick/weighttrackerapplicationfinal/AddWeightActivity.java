package com.sharnick.weighttrackerapplicationfinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;


public class AddWeightActivity extends AppCompatActivity {

    public static final String EXTRA_WEIGHTS_LIST = "com.sharnick.weighttrackerapplicationfinal.weights_list";

    // In the onCreate() method, the layout is inflated using setContentView().
    // The FragmentManager is retrieved using getSupportFragmentManager().
    // The Fragment associated with the add_weight_fragment_container is retrieved using findFragmentById().
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);

        // The Fragment associated with the add_weight_fragment_container is retrieved using findFragmentById().
        // If the Fragment is not found, a new instance of the AddWeightFragment is created using the newInstance() method and the weights list from the intent extras.

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.add_weight_fragment_container);

        // The Fragment transaction is started and the AddWeightFragment is added to the add_weight_fragment_container using the add() method.
        if (fragment == null) {
            ArrayList<WeightModel> weights = getIntent().getParcelableArrayListExtra(EXTRA_WEIGHTS_LIST);
            fragment = AddWeightFragment.newInstance(weights);
            fragmentManager.beginTransaction()
                    .add(R.id.add_weight_fragment_container, fragment)
                    .commit();

        }

    }

}
