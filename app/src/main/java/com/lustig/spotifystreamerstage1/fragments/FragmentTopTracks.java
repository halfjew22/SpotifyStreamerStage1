package com.lustig.spotifystreamerstage1.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.api.SpotifyHelper;
import com.lustig.spotifystreamerstage1.model.CurrentScenario;

import java.util.ArrayList;
import java.util.HashMap;

import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTopTracks extends Fragment {

    ArrayList<String> mTrackNames;

    TextView mText;

    public static final String TAG = "FragmentTopTracks";

    public FragmentTopTracks() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top_tracks, container, false);

        mTrackNames = new ArrayList<String>();

        HashMap<String, Object> map = new HashMap<>();

        map.put("country", "US");

        SpotifyHelper
                .getInstance()
                .getService()
                .getArtistTopTrack(
                        CurrentScenario.getInstance().getCurrentArtist().getUri(),
                        map,
                        new Callback<Tracks>() {

                            @Override
                            public void success(Tracks tracks, Response response) {

                                for (Track t : tracks.tracks) {
                                    d(t.name.toString());
                                    mTrackNames.add(t.name);
                                }

                                getActivity().runOnUiThread(
                                        new Runnable() {

                                            @Override
                                            public void run() {

                                                for (String name : mTrackNames) {
                                                    mText.append(name + "\n");
                                                }
                                            }
                                        });
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });

        mText = (TextView) v.findViewById(R.id.text);

        mText.setText(CurrentScenario.getInstance().getCurrentArtist().getName() + "\n");

        return v;
    }

    void d(String msg) {

        Log.d(TAG, msg);
    }
}
