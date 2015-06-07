package com.lustig.spotifystreamerstage1.model;

/**
 * Created by lustig on 6/6/15.
 */
public class _Artist {

    public static final int NUM_TOP_TRACKS = 10;

    String mName;
    String mUri;
    String mHref;

    _Track[] mTopTracks = new _Track[NUM_TOP_TRACKS];

    public _Artist(String name) {
        mName = name;
    }

    public _Artist(String name, _Track[] topTracks) {
        mName = name;
        mTopTracks = topTracks;
    }

    public _Artist(kaaes.spotify.webapi.android.models.Artist artist) {
        mName = artist.name;
        mUri =  artist.uri.replace("spotify:artist:", "");
        mHref = artist.href;
    }

    public String getName() {
        return mName;
    }

    public String getUri() {
        return mUri;
    }

    public String getHref() {
        return mHref;
    }

    public _Track[] getTopTracks() {
        return mTopTracks;
    }

    @Override
    public String toString() {
        return getName();
    }
}
