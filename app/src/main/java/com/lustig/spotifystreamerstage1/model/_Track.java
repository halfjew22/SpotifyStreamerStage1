package com.lustig.spotifystreamerstage1.model;

import android.util.Log;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;

public class _Track {

    // Title of the track
    String mTitle;

    // Name of track's album
    String mAlbumName;

    // URL of 30 second clip of track
    String mPreviewUrl;

    // See comment below for reasoning of storing each image URL
    ArrayList<String> mAlbumImageUrls;

    /**
     * This constructor accepts a Track parameter from the
     * SpotifyWrapper and returns a _Track object that
     * has grabbed all the information it needs.
     *
     * This constructor may not be necessary, but it cuts down
     * on any unneeded fields returned from the query and
     * keeps my life simpler.
     *
     * @param apiTrack Track object returned from SpotifyApi query
     */
    public _Track(Track apiTrack) {
        mTitle = apiTrack.name;
        mAlbumName = apiTrack.album.name;
        mPreviewUrl = apiTrack.preview_url;

        /**
         * Problem
         *
         * When I tried to set the imageUrl to the 0th
         * index of the apiTrack.album.images List,
         * I kept receiving a NPE. I couldn't figure
         * out why that wouldn't work, but manually
         * adding each url to my own ArrayList would,
         * but my below implementation works.
         *
         * I know it's not ideal, but it's my current
         * solution.
         */
        mAlbumImageUrls = new ArrayList<>();

        for (Image image : apiTrack.album.images) {
            mAlbumImageUrls.add(image.url);
        }

        d(apiTrack.preview_url);
    }

    public _Track(String title, String album) {
        mTitle = title;
        mAlbumName = album;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public String getAlbumArtUrl() {

        if (mAlbumImageUrls != null && mAlbumImageUrls.size() > 0) {
            return mAlbumImageUrls.get(0);
        } else {
            return "http://i.ytimg.com/vi/oM1EVAYahFE/maxresdefault.jpg";
        }
    }

    public String getPreviewUrl() {
        return mPreviewUrl;
    }

    void d(String msg) {
        Log.d("Track", msg);
    }
}
