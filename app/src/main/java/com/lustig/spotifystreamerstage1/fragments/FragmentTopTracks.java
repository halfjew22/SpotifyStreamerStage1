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
import com.lustig.spotifystreamerstage1.interfaces.TrackLoadingListener;
import com.lustig.spotifystreamerstage1.model.CurrentScenario;
import com.lustig.spotifystreamerstage1.model.TrackList;
import com.lustig.spotifystreamerstage1.model._Artist;

/**
 * Fragment to display top tracks from a selected artist
 */
public class FragmentTopTracks extends Fragment implements TrackLoadingListener {

    View mRootLayout;

    _Artist mArtist;
    String mArtistUri;

    RecyclerView mRecyclerView;

    TrackAdapter mAdapter;

    TrackList mTracks;

    public FragmentTopTracks() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /**
         * So, instead of opting for savedInstanceState management,
         * I've implemented a TrackList class that handles saving to
         * SharedPreferences on command.
         *
         * Whenever a user clicks on an artist they haven't clicked
         * before, I load the track list from the API. Once anything
         * causes the user to navigate away from the track listing,
         * I save the TrackList to SharedPrefs to be recalled next
         * time it needs to load. I could use a more efficient approach
         * than Gson / Json / SharedPrefs, but for the simplicity
         * of this project, I feel they suffice.
         *
         * So, although I don't handle sharedPrefs, I do take into
         * account orientation changes and storing the data thusly.
         */

        mArtist = CurrentScenario.getInstance().getCurrentArtist();
        mArtistUri = mArtist.getUri();

        // Inflate the layout for this fragment
        mRootLayout = inflater.inflate(R.layout.fragment_top_tracks, container, false);

        setUpRecyclerView();

        mTracks = new TrackList(mArtist, getActivity(), this);

        return mRootLayout;
    }

    @Override
    public void onPause() {
        super.onPause();

        mTracks.saveTracksToSharedPrefs();
    }

    private void setUpRecyclerView() {

        mRecyclerView = (RecyclerView) mRootLayout.findViewById(R.id.rvTopTracks);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onLoadingComplete(TrackList tracks) {

        /**
         * So, before I was trying to use this callback
         * to figure out when the tracks were done loading.
         *
         * I was loading them properly in my TrackList class,
         * but was getting a NPE in my TrackAdapter class
         * when the RecyclerView was trying to get the size
         * of my dataset.
         *
         * I'm still not sure what was happening, maybe it
         * has to do with callbacks and object references,
         * but when I pass the TrackList object who has
         * completed loading, everything works as expected.
         */
        mTracks = tracks;

        mAdapter = new TrackAdapter(mTracks, getActivity());
        mAdapter.setOnTrackClickListener((TopTracksActivity) getActivity());

        getActivity().runOnUiThread(
                new Runnable() {

                    @Override
                    public void run() {

                        mRecyclerView.setAdapter(mAdapter);
                    }
                });
    }
}
