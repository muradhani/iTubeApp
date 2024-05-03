package com.example.itubeapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.itubeapp.data.roomEntity.Playlist;

import java.util.List;
@Dao
public interface PlaylistDao {
    @Insert
    void insertPlaylist(Playlist playlist);

    @Query("SELECT * FROM playlists WHERE userId = :userId")
    List<Playlist> getPlaylistsByUserId(int userId);

    @Query("DELETE FROM playlists WHERE id = :playlistId")
    void deletePlaylist(int playlistId);
}
