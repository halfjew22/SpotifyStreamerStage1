package com.lustig.spotifystreamerstage1.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.model._Artist;
import com.squareup.picasso.Picasso;

public class ArtistViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout mRootView;

    Context mContext;

    private ImageView mArtistImage;
    private TextView mArtistName;

    public SparseBooleanArray selectedArtists;

    public ArtistViewHolder(View itemView) {
        super(itemView);

        selectedArtists = new SparseBooleanArray();

        mRootView = (RelativeLayout) itemView.findViewById(R.id.artistItemRoot);

        mContext = mRootView.getContext();

        mArtistImage = (ImageView) itemView.findViewById(R.id.ivArtistImage);
        mArtistName = (TextView) itemView.findViewById(R.id.tvArtistName);
    }

    public void setArtistName(String artistName) {
        mArtistName.setText(artistName);
    }

    public void setArtistImage(String imageUrl) {
        Picasso.with(mContext).load(imageUrl).into(mArtistImage);
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

    public void setArtistDetails(_Artist currentArtist) {

        setArtistName(currentArtist.getName());

        if (currentArtist.getArtistArtUrl() != null) {
            setArtistImage(currentArtist.getArtistArtUrl());
        }
    }

}
