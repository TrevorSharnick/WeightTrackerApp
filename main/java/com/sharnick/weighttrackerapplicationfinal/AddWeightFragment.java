package com.sharnick.weighttrackerapplicationfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class is responsible for handling the UI logic for adding a new weight entry to the app.
 */
public class AddWeightFragment extends Fragment {
    /**
     * The intent extra key used to pass the list of weight entries to the fragment.
     */
    private static final String EXTRA_WEIGHTS_LIST = "com.sharnick.weighttrackerapplicationfinal.weights_list";
    /**
     * The intent extra key used to pass the ID of the newly added weight entry back to the calling activity.
     */
    private static final String EXTRA_WEIGHT_ID = "com.sharnick.weighttrackerapplicationfinal.weight_id";
    /**
     * This method is used to create a new instance of the AddWeightFragment and set the arguments to the provided list of weight entries.
     * @param weights the list of weight entries to be passed as arguments to the fragment
     * @return the new instance of the AddWeightFragment
     */
    public static AddWeightFragment newInstance(ArrayList<WeightModel> weights) {
        AddWeightFragment fragment = new AddWeightFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(EXTRA_WEIGHTS_LIST, weights);
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * This method is called when the fragment view is created.
     * It sets up the UI and adds a click listener to the save button.
     * When the save button is clicked, it retrieves the weight value from the EditText view, validates it, and adds a new weight entry to the database.
     * If the weight value is invalid, a toast message is displayed to the user.
     * @param inflater the layout inflater
     * @param container the view group container
     * @param savedInstanceState the saved state of the fragment
     * @return the inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_weight, container, false);

        // Set up the save button click listener
        Button saveButton = view.findViewById(R.id.button_save_weight);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the weight value from the EditText view
                EditText weightEditText = view.findViewById(R.id.edit_text_weight_value);
                String weightString = weightEditText.getText().toString();

                // Validate the weight value
                if (weightString.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a weight value", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double weightValue = Double.parseDouble(weightString);

                    // Create a new WeightModel object with the weight value and current date
                    WeightModel weight = new WeightModel(weightValue, new Date());

                    // Add the weight to the database
                    WeightLab weightLab = WeightLab.get(getActivity());
                    long newId = weightLab.addWeight(weight);

                    // Return the new ID to the WeightListActivity
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_WEIGHT_ID, newId);
                    getActivity().setResult(Activity.RESULT_OK, intent);
                    getActivity().finish();
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Invalid weight value", Toast.LENGTH_SHORT).show();
                }
            }


        });

        return view;
    }
}
