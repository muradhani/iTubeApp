package com.example.itubeapp.ui.viewModels;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.ViewModel;

import com.example.itubeapp.dao.UserDao;
import com.example.itubeapp.data.database.AppDatabase;
import com.example.itubeapp.data.roomEntity.User;

public class RegisterFragmentViewModel extends ViewModel {
    private UserDao userDao;
    private RegisterListener registerListener;
    public void initaiteDatabae(Context context,RegisterListener listener){
        userDao = AppDatabase.getDatabase(context).userDao();

        registerListener = listener;
    }
    // Method to register a new user asynchronously
    public void registerUser(String fullName, String username, String password) {
        new RegisterAsyncTask().execute(fullName, username, password);
    }

    // AsyncTask to perform registration operation asynchronously
    private class RegisterAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            // Create a new User object
            User user = new User(params[0], params[1], params[2], null); // Assuming playlists is initially null

            // Insert the new user into the database
            userDao.insertUser(user);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Notify listener that registration is complete
            if (registerListener != null) {
                registerListener.onRegistrationComplete();
            }
        }
    }

    // Listener interface to communicate registration completion
    public interface RegisterListener {
        void onRegistrationComplete();
    }

    // Method to set register listener
    public void setRegisterListener(RegisterListener listener) {
        this.registerListener = listener;
    }
}
