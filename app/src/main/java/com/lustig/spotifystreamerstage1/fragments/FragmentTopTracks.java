package com.lustig.spotifystreamerstage1.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.activities.TopTracksActivity;
import com.lustig.spotifystreamerstage1.adapters.TrackAdapter;
import com.lustig.spotifystreamerstage1.api.SpotifyHelper;
import com.lustig.spotifystreamerstage1.model.CurrentScenario;
import com.lustig.spotifystreamerstage1.model._Track;
import com.lustig.spotifystreamerstage1.utility.U;

import java.util.ArrayList;
import java.util.HashMap;

import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Fragment to display top tracks from a selected artist
 */
public class FragmentTopTracks extends Fragment {

    View mRootLayout;

    RecyclerView mRecyclerView;

    TrackAdapter mAdapter;

    ArrayList<_Track> mTracks;

    public FragmentTopTracks() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            U.d("TracksFragment savedInstanceState is null");
        } else {
            U.d("TracksFragment savedInstanceState is NOT null");
        }

        // Inflate the layout for this fragment
        mRootLayout = inflater.inflate(R.layout.fragment_top_tracks, container, false);

        setUpRecyclerView();

        loadTracks();

        return mRootLayout;
    }

    private void loadTracks() {

        mTracks = new ArrayList<>();

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

                                    mTracks.add(new _Track(t));

                                    mAdapter = new TrackAdapter(mTracks, getActivity());
                                    mAdapter.setOnTrackClickListener((TopTracksActivity) getActivity());
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

                                U.d(error.getBody().toString());
                            }
                        });
    }

    private void setUpRecyclerView() {

        mRecyclerView = (RecyclerView) mRootLayout.findViewById(R.id.rvTopTracks);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
