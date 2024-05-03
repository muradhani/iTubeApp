package com.example.itubeapp.ui.viewModels;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.ViewModel;

import com.example.itubeapp.dao.PlaylistDao;
import com.example.itubeapp.data.database.AppDatabase;
import com.example.itubeapp.data.roomEntity.Playlist;
import com.example.itubeapp.data.roomEntity.User;

import java.util.List;

public class PlaylistsFragmentViewModel extends ViewModel {
    private PlaylistDao playlistDao;
    public void initaiteDatabae(Context context){
        playlistDao = AppDatabase.getDatabase(context).playlistDao();
    }
    public void getPlaylistsByUserId(int userId, PlaylistCallback callback) {
        // Fetch playlists by userId asynchronously
        new AsyncTask<Integer, Void, List<Playlist>>() {
            @Override
            protected List<Playlist> doInBackground(Integer... params) {
                return playlistDao.getPlaylistsByUserId(params[0]);
            }

            @Override
            protected void onPostExecute(List<Playlist> playlists) {
                // Invoke the appropriate callback method with the result
                if (playlists != null) {
                    callback.onPlaylistsLoaded(playlists);
                } else {
                    callback.onError("Failed to fetch playlists");
                }
            }
        }.execute(userId);
    }
}


