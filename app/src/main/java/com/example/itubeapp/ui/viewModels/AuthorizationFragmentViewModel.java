package com.example.itubeapp.ui.viewModels;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.ViewModel;

import com.example.itubeapp.dao.UserDao;
import com.example.itubeapp.data.database.AppDatabase;
import com.example.itubeapp.data.roomEntity.User;

public class AuthorizationFragmentViewModel extends ViewModel {
    private UserDao userDao;
    private LoginListener loginListener;

    public void initaiteDatabae(Context context, LoginListener listener){
        userDao = AppDatabase.getDatabase(context).userDao();
        loginListener =listener;
    }

    // Method to perform login asynchronously
    public void login(String username, String password) {
        new LoginAsyncTask().execute(username, password);
    }

    // AsyncTask to perform login operation asynchronously
    private class LoginAsyncTask extends AsyncTask<String, Void, User> {
        @Override
        protected User doInBackground(String... params) {
            // Retrieve the user by username and password from UserDao in background thread
            return userDao.getUserByUsernameAndPassword(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(User user) {
            // Check if user exists and credentials are correct
            if (user != null) {
                // User authenticated successfully
                // Handle success, navigate to next screen, etc.
                if (loginListener != null) {
                    loginListener.onLoginSuccess(user);
                }
            } else {
                // Invalid credentials or user not found
                // Handle error, display error message, etc.
                if (loginListener != null) {
                    loginListener.onLoginError("Invalid credentials or user not found");
                }
            }
        }
    }

    // Listener interface to communicate login result back to UI
    public interface LoginListener {
        void onLoginSuccess(User user);
        void onLoginError(String errorMessage);
    }

    // Method to set login listener
    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }
}
