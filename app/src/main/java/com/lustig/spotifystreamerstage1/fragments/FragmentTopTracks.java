package com.lustig.spotifystreamerstage1.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.adapters.TrackAdapter;
import com.lustig.spotifystreamerstage1.api.SpotifyHelper;
import com.lustig.spotifystreamerstage1.model.CurrentScenario;
import com.lustig.spotifystreamerstage1.model._Track;

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

    RecyclerView mRecyclerView;

    TrackAdapter mAdapter;

    ArrayList<_Track> mTracks;

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

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rvTopTracks);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mTracks = new ArrayList<_Track>();

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
                                    mTracks.add(new _Track(t));

                                    mAdapter = new TrackAdapter(mTracks, getActivity());
                                }

                                getActivity().runOnUiThread(
                                        new Runnable() {

                                            @Override
                                            public void run() {
                                                mRecyclerView.setAdapter(mAdapter);
                                            }
                                        });
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });

        return v;
    }

    void d(String msg) {

        Log.d(TAG, msg);
    }
}
