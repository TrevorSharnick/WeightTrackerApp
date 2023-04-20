
package com.sharnick.weighttrackerapplicationfinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private Button permissionButton;
    private TextView permissionStatus;
    private SwitchCompat goalWeightSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Find views by their IDs
        permissionButton = view.findViewById(R.id.permission_button);
        permissionStatus = view.findViewById(R.id.permission_status);
        goalWeightSwitch = view.findViewById(R.id.goal_weight_switch);

        // Set click listener on the permission button
        permissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Print a simple toast to verify the button's reaction
                Toast.makeText(getContext(), "Permissions Requested", Toast.LENGTH_SHORT).show();
            }
        });



        // Set onCheckedChangeListener on the goal weight switch
        goalWeightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Get the shared preferences instance
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

                // Get the editor to edit the shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Save the notification setting
                editor.putBoolean("notification_setting", isChecked);
                editor.apply();

                if (isChecked) {
                    Toast.makeText(getContext(), "Notifications enabled", Toast.LENGTH_SHORT).show();
                } else {
                    // Do nothing if notification is disabled
                }
            }
        });

        return view;
    }
}
