package com.example.itubeapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.itubeapp.data.roomEntity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM users WHERE username = :username")
    User getUserByUsernameAndPassword(String username);

    @Insert
    void insertUser(User user);

    @Query("UPDATE users SET playlists = :playlistLink WHERE username = :username")
    void addPlaylistLink(String username, String playlistLink);

    @Query("SELECT playlists FROM users WHERE id = :userId")
    String getPlaylistLinkByUsername(Integer userId);
}