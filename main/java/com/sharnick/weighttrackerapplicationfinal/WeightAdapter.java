package com.sharnick.weighttrackerapplicationfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * The WeightHolder and WeightAdapter represent the main logic behind the RecyclerView's user Weight objects
 */
public class WeightAdapter extends RecyclerView.Adapter<WeightHolder> {
    private List<WeightModel> mWeights;

    public WeightAdapter(List<WeightModel> weights) {
        mWeights = weights;
    }

    public void setWeights(List<WeightModel> weights) {
        mWeights = weights;
    }

    // onCreateViewHolder: This method creates and returns a new WeightHolder object which holds a single item view.
    @Override
    public WeightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_weight, parent, false);
        return new WeightHolder(view);
    }
    // onBindViewHolder: This method binds the data to the view holder by setting the values of the view's various widgets.
    @Override
    public void onBindViewHolder(WeightHolder holder, int position) {
        WeightModel weight = mWeights.get(position);
        holder.bind(weight);
    }
    // getItemCount: This method returns the total number of items in the list.
    @Override
    public int getItemCount() {
        return mWeights.size();
    }
}
