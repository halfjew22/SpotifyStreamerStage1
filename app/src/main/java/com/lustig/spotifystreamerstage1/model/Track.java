package com.lustig.spotifystreamerstage1.model;

public class Track {

    String mTitle;
    String mAlbum;

    public Track(String title, String album) {
        mTitle = title;
        mAlbum = album;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAlbum() {
        return mAlbum;
    }
}
