package com.sharnick.weighttrackerapplicationfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The WeightListFragment class is responsible for displaying a list of WeightModel objects in a RecyclerView.
 * It contains an adapter that populates the RecyclerView with the WeightModel objects, and each WeightModel object is displayed in a list item.
 */
public class WeightListFragment extends Fragment {

    private RecyclerView mWeightRecyclerView;
    private WeightAdapter mAdapter;

    private static final int REQUEST_EDIT_WEIGHT = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight_list, container, false);

        mWeightRecyclerView = view.findViewById(R.id.weight_recycler_view);
        mWeightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /**
         * The WeightListFragment also provides a FloatingActionButton for adding a new WeightModel object to the list,
         * and ImageButton for navigating to the GoalWeightActivity and the SettingsFragment.
         * Clicking on a list item navigates the user to the WeightDetailActivity where they can edit the selected WeightModel object.
         */

        // ADD WEIGHT BUTTON
        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WeightLab weightLab = WeightLab.get(getActivity());
                ArrayList<WeightModel> weightsList = new ArrayList<>(weightLab.getWeights());

                Intent intent = new Intent(getActivity(), AddWeightActivity.class);
                intent.putParcelableArrayListExtra(AddWeightActivity.EXTRA_WEIGHTS_LIST, weightsList);
                startActivity(intent);
            }
        });

        // GOAL WEIGHT BUTTON
        ImageButton goalWeightButton = view.findViewById(R.id.goal_weight_button);
        goalWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoalWeightActivity.class);
                startActivity(intent);
            }
        });

        // SETTINGS BUTTON
        ImageButton settingsButton = view.findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace current fragment with SettingsFragment
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.settings_fragment_container, new SettingsFragment());
                ft.addToBackStack(null);
                ft.commit();
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("DEBUG", "onResume() called");
        updateUI();
    }

    private void updateUI() {
        WeightLab weightLab = WeightLab.get(getActivity());
        List<WeightModel> weights = weightLab.getWeights();

        if (mAdapter == null) {
            mAdapter = new WeightAdapter(weights);
            mWeightRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setWeights(weights);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class WeightAdapter extends RecyclerView.Adapter<WeightHolder> {
        private List<WeightModel> mWeights;

        public WeightAdapter(List<WeightModel> weights) {
            mWeights = weights;
        }

        public void setWeights(List<WeightModel> weights) {
            mWeights = weights;
        }

        @Override
        public WeightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new WeightHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(WeightHolder holder, int position) {
            WeightModel weight = mWeights.get(position);
            holder.bind(weight);
        }

        @Override
        public int getItemCount() {
            return mWeights.size();
        }
    }

    private class WeightHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mWeightValueTextView;
        private TextView mDateTextView;

        private WeightModel mWeight;

        public WeightHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_weight, parent, false));
            itemView.setOnClickListener(this);

            mWeightValueTextView = itemView.findViewById(R.id.weight_value);
            mDateTextView = itemView.findViewById(R.id.weight_date);
        }

        public void bind(WeightModel weight) {
            mWeight = weight;
            mWeightValueTextView.setText(Double.toString(mWeight.getWeight()));

            // Format the date into a more user-friendly string
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);
            String dateString = dateFormat.format(mWeight.getDate());

            mDateTextView.setText(dateString);
        }

        @Override
        public void onClick(View view) {
            Intent intent = WeightDetailActivity.newIntent(getActivity(), mWeight.getId());
            startActivityForResult(intent, REQUEST_EDIT_WEIGHT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_EDIT_WEIGHT) {
            updateUI();
        }
    }
}
