
package com.sharnick.weighttrackerapplicationfinal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
/**
 * This fragment is responsible for displaying the login UI and handling user input.
 */
public class LoginFragment extends Fragment {

    private LoginFragmentListener listener;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button createAccountButton;

    /**
     * This interface is used to communicate with the activity hosting the fragment.
     */
    public interface LoginFragmentListener {
        void onLoginSelected(String username, String password);
        void onCreateAccountSelected();
    }
    /**
     * This method is called when the fragment is attached to the hosting activity.
     * It sets up the listener for communication with the activity.
     * @param context the context of the hosting activity
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragmentListener) {
            listener = (LoginFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LoginFragmentListener");
        }
    }
    /**
     * This method sets the text of the username EditText view to the username of the passed user object.
     * @param user the user object containing the username
     */
    public void setUser(User user) {
        if (getView() != null) {
            EditText usernameEditText = getView().findViewById(R.id.username_edittext);
            usernameEditText.setText(user.getUsername());
        }
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize UI components
        usernameEditText = view.findViewById(R.id.username_edittext);
        passwordEditText = view.findViewById(R.id.password_edittext);
        loginButton = view.findViewById(R.id.login_button);
        createAccountButton = view.findViewById(R.id.create_account_button);

        // Set click listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                listener.onLoginSelected(username, password);
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCreateAccountSelected();
            }
        });

        return view;
    }
}