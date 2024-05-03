package com.example.itubeapp.ui.viewModels;

import com.example.itubeapp.data.roomEntity.Playlist;

import java.util.List;

public interface PlaylistCallback {
    void onPlaylistsLoaded(List<Playlist> playlists);
    void onError(String errorMessage);
}
