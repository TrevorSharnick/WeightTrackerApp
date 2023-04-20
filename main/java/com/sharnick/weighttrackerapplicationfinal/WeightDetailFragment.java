package com.sharnick.weighttrackerapplicationfinal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;

/**
 * This code defines a fragment that displays the details of a weight record.
 * It retrieves the weight record ID from its arguments,
 * gets the corresponding WeightModel object from the database using the WeightLab singleton, and displays its details in the UI.
 *
 * The user can edit the weight value of the record and save the changes by clicking a button.
 * When the save button is clicked, the WeightModel object is updated with the new weight value,
 * and the changes are saved to the database using the WeightLab singleton.
 *
 * Finally, the activity is finished and the user is returned to the WeightListFragment.
 */
public class WeightDetailFragment extends Fragment {

    public static final String EXTRA_WEIGHT_ID =
            "com.sharnick.weighttrackerapplicationfinal.weight_id";

    private WeightModel mWeight;

    private TextView mWeightValueTextView;
    private EditText mWeightEditText;
    private EditText mDateEditText;
    private Button mSaveButton;

    public static WeightDetailFragment newInstance(long weightId) {
        Bundle args = new Bundle();
        args.putLong(EXTRA_WEIGHT_ID, weightId);

        WeightDetailFragment fragment = new WeightDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("WeightDetailFragment", "onCreate called");
        long weightId = getArguments().getLong(EXTRA_WEIGHT_ID);
        mWeight = WeightLab.get(getActivity()).getWeight(weightId);
        Log.d("WeightDetailFragment", "mWeight: " + mWeight);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight_detail, container, false);

        // Find the views within the inflated layout
        mWeightEditText = view.findViewById(R.id.weight_edit_text);
        mSaveButton = view.findViewById(R.id.save_button);
        mDateEditText = view.findViewById(R.id.date_edit_text);

        // Set the initial value of the EditText to the weight value of the selected WeightModel
        mWeightEditText.setText(Double.toString(mWeight.getWeight()));

        // Set the click listener for the Save button
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the new weight value from the EditText
                String weightString = mWeightEditText.getText().toString();
                double weight = Double.parseDouble(weightString);

                // Update the weight value of the selected WeightModel
                mWeight.setWeight(weight);

                // Save the changes to the WeightModel in the database
                WeightLab.get(getActivity()).updateWeight(mWeight);

                // Finish the activity and return to the WeightListFragment
                getActivity().finish();
            }
        });

        // Update the UI fields to display the current WeightModel
        updateUIFields();

        return view;
    }

    private void updateUIFields() {
        mWeightEditText.setText(String.valueOf(mWeight.getWeight()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(mWeight.getDate());
        mDateEditText.setText(dateString);
    }

}