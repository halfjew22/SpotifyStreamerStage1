package com.lustig.spotifystreamerstage1.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lustig.spotifystreamerstage1.utility.U;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TrackList {

    private final String NONE_FOUND = "NONE_FOUND";

    private ArrayList<_Track> mTracks;
    Context mContext;

    _Artist mArtist;

    String mArtistTopTracksKey;

    public TrackList(ArrayList<_Track> tracks, _Artist artist, Context context) {
        mTracks = tracks;
        mArtist = artist;
        mContext = context;

        mArtistTopTracksKey = artist.getName() + "_" + artist.getUri();
        U.d("TrackList constructor:" + mArtistTopTracksKey);

        if (mTracks == null) {
            mTracks = loadTracksFromSharedPrefs();
        }
    }

    public TrackList(_Artist artist, Context context) {
        this(null, artist, context);
    }

    public void add(_Track track) {
        mTracks.add(track);
    }

    public _Track get(int index) {
        return mTracks.get(index);
    }

    public int size() {
        return mTracks.size();
    }

    public ArrayList<_Track> getTracks() {
        return mTracks;
    }

    public void saveTracksToSharedPrefs() {

        final ArrayList<_Track> tracksToSave = mTracks;

        Gson g = new Gson();
        String jsonTracks = g.toJson(tracksToSave);

        SharedPreferences prefs =
                PreferenceManager
                        .getDefaultSharedPreferences(mContext);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(mArtistTopTracksKey, jsonTracks);

        editor.commit();
    }

    public ArrayList<_Track> loadTracksFromSharedPrefs() {

        ArrayList<_Track> loadedTracks = new ArrayList<_Track>();

        SharedPreferences prefs =
                PreferenceManager
                        .getDefaultSharedPreferences(mContext);

        String jsonTracks = prefs.getString(mArtistTopTracksKey, NONE_FOUND);

        if (jsonTracks.equals(NONE_FOUND)) {
            return loadedTracks;
        }

        Gson g = new Gson();

        Type type = new TypeToken<ArrayList<_Track>>(){}.getType();

        loadedTracks = g.fromJson(jsonTracks, type);

        return loadedTracks;
    }
}
