package com.example.for_ksh_from_jhy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class MusicData {
    private String id;
    private String artist;
    private String title;
    private String albumArt;
    private String duration;
    private int playCount;
    private int liked;

    public MusicData() {
    }

    public MusicData(String id, String artist, String title, String albumArt, String duration, int playCount, int liked) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.albumArt = albumArt;
        this.duration = duration;
        this.playCount = playCount;
        this.liked = liked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public String getDuration() {
        return duration;
    }


    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if(obj instanceof MusicData){
            MusicData data = (MusicData) obj;
            equal = (this.id).equals(data.getId());
        }

        return equal;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, artist, title, albumArt, duration, playCount, liked);
    }
}
