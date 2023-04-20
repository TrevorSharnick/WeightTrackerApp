package com.sharnick.weighttrackerapplicationfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
This fragment is responsible for displaying the UI for setting the user's goal weight and saving it to the database.

In the onCreateView() method, the layout for the fragment is inflated and the references to the views are obtained.
The mSaveButton is also set up with an OnClickListener that retrieves the value entered into the mGoalWeightEditText and saves it to the database using the WeightLab singleton instance.
Finally, the activity is finished to return to the previous screen.
 */

public class GoalWeightFragment extends Fragment {

    private EditText mGoalWeightEditText;
    private Button mSaveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goal_weight, container, false);

        mGoalWeightEditText = view.findViewById(R.id.goal_weight_edit_text);
        mSaveButton = view.findViewById(R.id.button_save_goal_weight);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goalWeightString = mGoalWeightEditText.getText().toString();

                // Check if goal weight is a valid number
                double goalWeight = 0;
                try {
                    goalWeight = Double.parseDouble(goalWeightString);
                } catch (NumberFormatException e) {
                    // Show error message to user
                    Toast.makeText(getActivity(), "Please enter a valid number for your goal weight", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if goal weight is a positive number
                if (goalWeight <= 0) {
                    // Show error message to user
                    Toast.makeText(getActivity(), "Please enter a positive number for your goal weight", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save the goal weight to the database
                WeightLab weightLab = WeightLab.get(getActivity());
                weightLab.setGoalWeight(goalWeight);

                // Close the fragment and return to the previous screen
                getActivity().finish();
            }
        });

        return view;
    }
}
