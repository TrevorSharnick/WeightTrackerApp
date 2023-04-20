package com.sharnick.weighttrackerapplicationfinal;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This class is responsible for handling the UI logic for user authentication and navigation to different fragments in the app.
 */
public class LoginActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener, CreateAccountFragment.CreateAccountFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set the initial fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new LoginFragment())
                .commit();
    }

    /**
     * This method is called when the user successfully logs in.
     * It displays a success message to the user, and navigates to the WeightListFragment.
     * @param user the user object for the logged in user
     */
    public void onLoginSuccess(User user) {
        // Handle successful login here
        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

        // Navigate to the WeightListFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new WeightListFragment())
                .commit();
    }


    /**
     * This method is called when a user object is passed from the CreateAccountFragment to the LoginFragment.
     * It sets the user object in the LoginFragment.
     * @param user the user object to be passed to the fragment
     */
    private void passUserToFragment(User user) {
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (loginFragment != null) {
            loginFragment.setUser(user);
        }
    }
    /**
     * This method is called when the user selects the "create account" button in the LoginFragment.
     * It switches the current fragment to the CreateAccountFragment.
     */
    @Override
    public void onCreateAccountSelected() {
        // Switch to the create account fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new CreateAccountFragment())
                .addToBackStack(null)
                .commit();
    }
    /**
     * This method is called when the user selects the "login" button in the LoginFragment.
     * It retrieves the user object from the database and validates the password.
     * If the user is not found or the password is incorrect, error messages are displayed to the user.
     * If the login is successful, the onLoginSuccess() method is called.
     * @param username the entered username
     * @param password the entered password
     */
    @Override
    public void onLoginSelected(String username, String password) {
        User user = UserDatabase.getInstance(this).getUser(username);
        if (user == null) {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        } else if (user.getPassword().equals(password)) {
            onLoginSuccess(user);
        } else {
            Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * This method is called when a new account is successfully created in the CreateAccountFragment.
     * It displays a success message to the user, and navigates back to the LoginFragment.
     */
    @Override
    public void onAccountCreated() {
        // Switch back to the login fragment after account creation
        getSupportFragmentManager().popBackStackImmediate();
        Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
    }
}