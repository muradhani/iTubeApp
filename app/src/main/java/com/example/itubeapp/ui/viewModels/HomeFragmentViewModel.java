package com.example.itubeapp.ui.viewModels;

import androidx.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;

import com.example.itubeapp.dao.PlaylistDao;
import com.example.itubeapp.dao.UserDao;
import com.example.itubeapp.data.database.AppDatabase;
import com.example.itubeapp.data.roomEntity.Playlist;
import com.example.itubeapp.data.roomEntity.User;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentViewModel extends ViewModel {
    private UserDao userDao;
    private PlaylistDao playlistDao;
    private AddPlaylistListener addPlaylistListener;
    public void initaiteDatabae(Context context,AddPlaylistListener listener){
        userDao = AppDatabase.getDatabase(context).userDao();
        playlistDao = AppDatabase.getDatabase(context).playlistDao();
        addPlaylistListener = listener;
    }


    public void addPlaylistLink(int userId, String playlistLink) {
        new AddPlaylistAsyncTask().execute(userId, playlistLink);
    }

    // AsyncTask to perform adding playlist link operation asynchronously
    private class AddPlaylistAsyncTask extends AsyncTask<Object, Void, Void> {
        @Override
        protected Void doInBackground(Object... params) {
            int userId = (int) params[0];
            String playlistLink = (String) params[1];

            // Add the playlist link to the user's record using UserDao's addPlaylistLink method
            Playlist playlist = new Playlist(userId, playlistLink);

            // Insert the playlist into the database using PlaylistDao
            playlistDao.insertPlaylist(playlist);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Notify listener that adding playlist link is complete
            if (addPlaylistListener != null) {
                addPlaylistListener.onPlaylistAdded();
            }
        }
    }

    // Listener interface to communicate adding playlist completion
    public interface AddPlaylistListener {
        void onPlaylistAdded();
    }

    // Method to set add playlist listener
    public void setAddPlaylistListener(AddPlaylistListener listener) {
        this.addPlaylistListener = listener;
    }
}
