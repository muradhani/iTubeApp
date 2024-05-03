package com.example.itubeapp.data.roomEntity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "playlists")
public class Playlist {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private String playlistLink;

    // Constructor
    public Playlist(int userId, String playlistLink) {
        this.userId = userId;
        this.playlistLink = playlistLink;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlaylistLink() {
        return playlistLink;
    }

    public void setPlaylistLink(String playlistLink) {
        this.playlistLink = playlistLink;
    }
}
