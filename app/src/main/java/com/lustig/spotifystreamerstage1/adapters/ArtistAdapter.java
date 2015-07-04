package com.lustig.spotifystreamerstage1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.fragments.FragmentArtistSearch;
import com.lustig.spotifystreamerstage1.interfaces.OnArtistClickListener;
import com.lustig.spotifystreamerstage1.model._Artist;
import com.lustig.spotifystreamerstage1.viewholders.ArtistViewHolder;

import java.util.ArrayList;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistViewHolder> {

    ArrayList<_Artist> mArtists;
    Context mContext;
    FragmentArtistSearch mArtistSearchFragment;

    OnArtistClickListener mArtistClickListener;

    int mLastSelectedPosition = -1;

    public ArtistAdapter(Context context, FragmentArtistSearch fragment) {
        mContext = context;
        mArtists = new ArrayList<_Artist>();

        mArtistSearchFragment = fragment;
    }

    public ArtistAdapter(ArrayList<_Artist> artists, Context context, FragmentArtistSearch fragment) {
        mArtists = artists;
        mContext = context;

        mArtistSearchFragment = fragment;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_view_artist, viewGroup, false);

        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ArtistViewHolder artistViewHolder, final int position) {

        final _Artist currentArtist = mArtists.get(position);

        artistViewHolder.getRootView()
                        .setSelected(artistViewHolder.selectedArtists.get(position, false));

        artistViewHolder.setArtistDetails(currentArtist);

        artistViewHolder.setOnClickListener(
                new View.OnClickListener() {



                    @Override
                    public void onClick(View v) {

                        d(mLastSelectedPosition + "");
                        d(position + "");

                        mArtistSearchFragment.updateSelections(position, mLastSelectedPosition);

                        mLastSelectedPosition = position;

                        mArtistClickListener.onArtistClick(currentArtist);
                    }
                });
    }

    public static ArrayList<_Artist> getDummyArtistData() {

        ArrayList<_Artist> dummyData = new ArrayList<_Artist>();

        for (int i = 0; i < 14; i++) {

            _Artist a = new _Artist("Artist #" + i);

            dummyData.add(a);
        }

        return dummyData;
    }

    public void setArtistClickListener(OnArtistClickListener listener) {
        mArtistClickListener = listener;
    }

    public void d(String msg) {
        Log.d("ArtistAdapter", msg);
    }

    @Override
    public int getItemCount() {
        return mArtists.size();
    }
}
