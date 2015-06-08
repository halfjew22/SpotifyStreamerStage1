package com.lustig.spotifystreamerstage1.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.model._Track;
import com.squareup.picasso.Picasso;

public class TrackViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout mRootView;

    Context mContext;

    private ImageView mAlbumImage;

    private TextView mTrackName;
    private TextView mAlbumName;

    public TrackViewHolder(View itemView) {
        super(itemView);

        mRootView = (RelativeLayout) itemView.findViewById(R.id.trackItemRoot);

        mContext = mRootView.getContext();

        mAlbumImage = (ImageView) itemView.findViewById(R.id.ivAlbumImage);

        mTrackName = (TextView) itemView.findViewById(R.id.tvTrackName);
        mAlbumName = (TextView) itemView.findViewById(R.id.tvAlbumName);
    }

    public void setTrackName(String trackName) {
        mTrackName.setText(trackName);
    }

    public void setAlbumName(String albumName) {
        mAlbumName.setText(albumName);
    }

    public void setAlbumImage(String imageUrl) {
        Picasso.with(mContext).load(imageUrl).into(mAlbumImage);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        mRootView.setOnClickListener(listener);
    }

    public void setOnClickListener(View v, View.OnClickListener listener) {
        v.setOnClickListener(listener);
    }

    public RelativeLayout getRootView() {
        return mRootView;
    }

    public void setTrackDetails(_Track currentTrack) {

        setTrackName(currentTrack.getTitle());
        setAlbumName(currentTrack.getAlbumName());

        if (currentTrack.getAlbumArtUrl() != null) {
            setAlbumImage(currentTrack.getAlbumArtUrl());
        }
    }
}
