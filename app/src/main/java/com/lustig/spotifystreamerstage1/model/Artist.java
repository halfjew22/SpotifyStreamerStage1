package com.lustig.spotifystreamerstage1.model;

/**
 * Created by lustig on 6/6/15.
 */
public class Artist {

    public static final int NUM_TOP_TRACKS = 10;

    String mName;
    Track[] mTopTracks = new Track[NUM_TOP_TRACKS];

    public Artist(String name) {
        mName = name;
    }

    public Artist(String name, Track[] topTracks) {
        mName = name;
        mTopTracks = topTracks;
    }

    public String getName() {
        return mName;
    }

    public Track[] getTopTracks() {
        return mTopTracks;
    }

    @Override
    public String toString() {
        return getName();
    }
}
