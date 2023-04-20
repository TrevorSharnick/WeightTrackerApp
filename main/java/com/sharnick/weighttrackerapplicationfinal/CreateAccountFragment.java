package com.sharnick.weighttrackerapplicationfinal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


public class CreateAccountFragment extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button createAccountButton;

    private CreateAccountFragmentListener listener;
    /**
     * This interface defines the callback method to be implemented by the calling activity
     * when a new account is successfully created.
     */
    public interface CreateAccountFragmentListener {
        void onAccountCreated();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);

        // Initialize views
        usernameEditText = view.findViewById(R.id.username_edittext);
        passwordEditText = view.findViewById(R.id.password_edittext);
        createAccountButton = view.findViewById(R.id.create_account_button);

        // Set click listener for create account button
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate input
                if (username.isEmpty()) {
                    usernameEditText.setError("Username required");
                    usernameEditText.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    passwordEditText.setError("Password required");
                    passwordEditText.requestFocus();
                    return;
                }

                // Create user and add to database
                User user = new User(0, username, password);
                UserDatabase.getInstance(getContext()).addUser(user);

                // Notify listener that account was created
                listener.onAccountCreated();
            }
        });

        return view;
    }

    /**
     * This method is called when the fragment is attached to the activity.
     * It checks if the activity implements the CreateAccountFragmentListener interface,
     * and if so, sets the listener accordingly.
     * @param context the activity context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateAccountFragmentListener) {
            listener = (CreateAccountFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CreateAccountFragmentListener");
        }
    }
}
