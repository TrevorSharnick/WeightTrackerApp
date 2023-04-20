package com.sharnick.weighttrackerapplicationfinal;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Locale;
/**
 * The WeightHolder and WeightAdapter represent the main logic behind the RecyclerView's user Weight objects
 * The WeightHolder class is a RecyclerView ViewHolder that represents a single item view in the RecyclerView of weight entries.
 * It has two private variables, mWeightValueTextView and mDateTextView, which are TextView instances that display the weight value and date, respectively.
 */
public class WeightHolder extends RecyclerView.ViewHolder {
    private TextView mWeightValueTextView;
    private TextView mDateTextView;

    private WeightModel mWeight;
    private Context mContext;

    public WeightHolder(View itemView) {
        super(itemView);
        /**
         * The itemView's click listener starts an Intent to open the WeightDetailActivity with the WeightDetailFragment displaying the details of the selected weight entry.
         * The mWeight.getId() method call retrieves the ID of the weight entry, which is passed as an extra to the WeightDetailActivity.
         */
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WeightDetailActivity.class);
                intent.putExtra(WeightDetailFragment.EXTRA_WEIGHT_ID, mWeight.getId());
                view.getContext().startActivity(intent);
            }
        });
        mWeightValueTextView = itemView.findViewById(R.id.weight_value);
        mDateTextView = itemView.findViewById(R.id.weight_date);
    }

    /**
     * The bind method takes a WeightModel object and binds its data to the view.
     * The weight value is displayed in mWeightValueTextView by converting it to a string,
     * and the date is formatted using a SimpleDateFormat instance to a more user-friendly format and displayed in mDateTextView.
     * @param weight
     */
    public void bind(WeightModel weight) {
        mWeight = weight;
        mWeightValueTextView.setText(Double.toString(mWeight.getWeight()));

        // Format the date into a more user-friendly string
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);
        String dateString = dateFormat.format(mWeight.getDate());

        mDateTextView.setText(dateString);
    }
}