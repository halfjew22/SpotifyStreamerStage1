package com.lustig.spotifystreamerstage1.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.model._Artist;

public class ArtistViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout mRootView;

    private ImageView mArtistImage;
    private TextView mArtistName;

    public ArtistViewHolder(View itemView) {
        super(itemView);

        mRootView = (RelativeLayout) itemView.findViewById(R.id.artistItemRoot);

        mArtistImage = (ImageView) itemView.findViewById(R.id.ivArtistImage);
        mArtistName = (TextView) itemView.findViewById(R.id.tvArtistName);
    }

    // ToDo figure out params for this method and implement functionality
    public void setArtistImage() {}

    public void setArtistName(String artistName) {
        mArtistName.setText(artistName);
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
    }
}
