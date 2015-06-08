package com.lustig.spotifystreamerstage1.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.interfaces.OnTrackClickListener;
import com.lustig.spotifystreamerstage1.model._Track;
import com.lustig.spotifystreamerstage1.viewholders.TrackViewHolder;

import java.util.ArrayList;

public class TrackAdapter extends RecyclerView.Adapter<TrackViewHolder> {

    ArrayList<_Track> mTracks;
    Context mContext;

    OnTrackClickListener mTrackClickListener;

    public TrackAdapter(ArrayList<_Track> tracks, Context context) {
        mTracks = tracks;
        mContext = context;
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.item_view_track, parent, false);

        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder trackViewHolder, int position) {

        final _Track currentTrack = mTracks.get(position);

        trackViewHolder.setTrackDetails(currentTrack);
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }
}