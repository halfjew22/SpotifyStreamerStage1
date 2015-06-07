package com.lustig.spotifystreamerstage1.model;

import kaaes.spotify.webapi.android.models.Track;

public class _Track {

    String mTitle;
    String mAlbum;
    String mImageUrl;

    public _Track(Track apiTrack) {

    }

    public _Track(String title, String album) {
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
