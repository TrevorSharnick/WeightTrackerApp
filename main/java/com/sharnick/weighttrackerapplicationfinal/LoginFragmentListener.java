package com.sharnick.weighttrackerapplicationfinal;

/**
 * Implemented in the LoginActivity and LoginFragment classes
 */
public interface LoginFragmentListener {
    void onLogin(String username, String password);
    void onCreateAccount();
}
