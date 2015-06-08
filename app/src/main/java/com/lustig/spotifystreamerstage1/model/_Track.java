package com.lustig.spotifystreamerstage1.model;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;

public class _Track {

    String mTitle;
    String mAlbumName;
    String mImageUrl;

    ArrayList<String> mImageUrls;

    public _Track(Track apiTrack) {
        mTitle = apiTrack.name;
        mAlbumName = apiTrack.album.name;
//        mImageUrl = apiTrack.album.images.get(0).url;

        mImageUrls = new ArrayList<>();

        for (Image image : apiTrack.album.images) {
            mImageUrls.add(image.url);
        }
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

        if (mImageUrls != null && mImageUrls.size() > 0) {
            return mImageUrls.get(0);
        } else {
            return "http://i.ytimg.com/vi/oM1EVAYahFE/maxresdefault.jpg";
        }
    }
}
