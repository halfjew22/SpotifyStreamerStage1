package com.lustig.spotifystreamerstage1.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lustig.spotifystreamerstage1.api.SpotifyHelper;
import com.lustig.spotifystreamerstage1.interfaces.TrackLoadingListener;
import com.lustig.spotifystreamerstage1.utility.U;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TrackList {

    private final String NONE_FOUND = "NONE_FOUND";

    private ArrayList<_Track> mTracks;

    Context mContext;

    _Artist mArtist;

    String mArtistTopTracksKey;

    TrackLoadingListener mTrackLoadingListener;

    public TrackList(ArrayList<_Track> tracks,
                     _Artist artist,
                     Context context,
                     TrackLoadingListener listener) {

        mTracks = tracks;
        mArtist = artist;
        mContext = context;
        mTrackLoadingListener = listener;

        mArtistTopTracksKey = artist.getName() + "_" + artist.getUri();

        // Attempt loading Tracks from SharedPrefs if none passed in
        if (mTracks == null) {

            mTracks = loadTracksFromSharedPrefs();
        }




        // If, after attempting load from SP, we have 0 tracks, load from Internet
        if (mTracks.size() != 0) {

            for (_Track t : getTracks()) {
                U.d(t.getTitle());
            }

            U.d("Tracks loaded from SharedPrefs");
            mTrackLoadingListener.onLoadingComplete(this);

        } else {

            U.d("Loading Tracks from internet");

            HashMap<String, Object> map = new HashMap<>();

            map.put("country", "US");

            SpotifyHelper
                    .getInstance()
                    .getService()
                    .getArtistTopTrack(
                            mArtist.getUri(),
                            map,
                            new Callback<Tracks>() {

                                @Override
                                public void success(Tracks tracks, Response response) {

                                    for (Track t : tracks.tracks) {

                                        add(new _Track(t));
                                    }

                                    mTrackLoadingListener.onLoadingComplete(TrackList.this);
                                }

                                @Override
                                public void failure(RetrofitError error) {

                                    U.d(error.getBody().toString());
                                }
                            });
        }
    }

    public TrackList(_Artist artist, Context context, TrackLoadingListener listener) {

        this(null, artist, context, listener);
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

        Type type = new TypeToken<ArrayList<_Track>>() {

        }.getType();

        loadedTracks = g.fromJson(jsonTracks, type);

        return loadedTracks;
    }
}
