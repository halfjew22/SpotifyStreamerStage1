package com.lustig.spotifystreamerstage1.model;

import android.util.Log;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Image;

/**
 * This class will be my own Artist class.
 *
 * The reasoning behind the prepended '_' is
 * simply to distinguish my Artist class from
 * the Artist class of the SpotifyWrapper.
 */
public class _Artist {

    public static final int NUM_TOP_TRACKS = 10;

    String mName;
    String mUri;
    String mHref;

    String mArtistArtUrl;

    ArrayList<String> mImageUrls;

    _Track[] mTopTracks = new _Track[NUM_TOP_TRACKS];

    public _Artist(Artist apiArtist) {
        mName = apiArtist.name;
        mUri =  apiArtist.uri.replace("spotify:artist:", "");
        mHref = apiArtist.href;
//        mArtistArtUrl = apiArtist.images.get(0).url;

        // Weird behavior when trying to grab the 0th index
        // above. Resorting to this solution for now.
        mImageUrls = new ArrayList<>();

        for (Image i : apiArtist.images) {
            mImageUrls.add(i.url);
        }

        // Now I need to populate the list of tracks
        
    }

    public _Artist(String name) {
        mName = name;
    }

    public _Artist(String name, _Track[] topTracks) {
        mName = name;
        mTopTracks = topTracks;
    }

    public String getName() {
        return mName;
    }

    public String getUri() {
        return mUri;
    }

    public String getArtistArtUrl() {
        if (mImageUrls != null && mImageUrls.size() > 0)
            return mImageUrls.get(0);
        else
            return "http://i.ytimg.com/vi/oM1EVAYahFE/maxresdefault.jpg";
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

    void d(String msg) {

        Log.d("Artist", msg);
    }
}
