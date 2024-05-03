package com.example.itubeapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.itubeapp.data.roomEntity.Playlist;
import com.example.itubeapp.data.roomEntity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    User getUserByUsernameAndPassword(String username, String password);

    @Insert
    void insertUser(User user);

    @Query("UPDATE users SET fullName = :fullName, username = :username, password = :password WHERE id = :userId")
    void updateUser(int userId, String fullName, String username, String password);

    @Query("DELETE FROM users WHERE id = :userId")
    void deleteUser(int userId);
}