package com.lustig.spotifystreamerstage1.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lustig.spotifystreamerstage1.R;

public class ArtistViewHolder extends RecyclerView.ViewHolder {

    private ImageView mArtistImage;
    private TextView mArtistName;

    public ArtistViewHolder(View itemView) {
        super(itemView);

        mArtistImage = (ImageView) itemView.findViewById(R.id.ivArtistImage);
        mArtistName = (TextView) itemView.findViewById(R.id.tvArtistName);
    }

    // ToDo figure out params for this method and implement functionality
    public void setArtistImage() {}

    public void setArtistName(String artistName) {

    }
}
