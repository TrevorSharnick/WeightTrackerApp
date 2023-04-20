package com.sharnick.weighttrackerapplicationfinal;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
/**
 * This class is responsible for handling the UI logic for creating a new user account in the app.
 */

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountFragment.CreateAccountFragmentListener {
    /**
     * This method is called when the activity is created.
     * It sets up the UI and adds the CreateAccountFragment to the container.
     * @param savedInstanceState the saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Set the initial fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.create_account_fragment_container, new CreateAccountFragment())
                .commit();
    }
    /**
     * This method is called when a new account is successfully created in the CreateAccountFragment.
     * It displays a success message to the user and finishes the activity, returning to the login screen.
     */
    @Override
    public void onAccountCreated() {
        // Finish activity and return to login screen
        Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }
}