package com.lustig.spotifystreamerstage1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.activities.MainActivity;
import com.lustig.spotifystreamerstage1.adapters.ArtistAdapter;
import com.lustig.spotifystreamerstage1.api.SpotifyHelper;
import com.lustig.spotifystreamerstage1.model.CurrentScenario;
import com.lustig.spotifystreamerstage1.model._Artist;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentArtistSearch extends Fragment {

    View mArtistSearchLayout;

    EditText mArtistSearch;

    RecyclerView mRecyclerView;

    ArtistAdapter mAdapter;

    SpotifyService mSpotify;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mArtistSearchLayout = inflater.inflate(
                R.layout.fragment_artist_search, container, false);

        mSpotify = SpotifyHelper.getInstance().getService();

        bindViews();

        setTextChangedWatcher();

        setUpRecyclerView();

        return mArtistSearchLayout;
    }

    private void setTextChangedWatcher() {

        mArtistSearch.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(final CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        d(s.toString());

                        CurrentScenario.getInstance().setArtistSearchText(s.toString());

                        mSpotify.searchArtists(
                                s.toString(), new Callback<ArtistsPager>() {

                                    @Override
                                    public void success(ArtistsPager artistsPager, Response response) {

                                        final ArrayList<_Artist> _artists = new ArrayList<_Artist>();

                                        List<Artist> artists = artistsPager.artists.items;
                                        for (kaaes.spotify.webapi.android.models.Artist a : artists) {
                                            d(a.name);


                                            _artists.add(new _Artist(a));
                                        }

                                        getActivity().runOnUiThread(
                                                new Runnable() {

                                                    @Override
                                                    public void run() {
                                                        mAdapter = new ArtistAdapter(_artists, getActivity());
                                                        mAdapter.setArtistClickListener((MainActivity) getActivity());
                                                        mRecyclerView.setAdapter(mAdapter);
                                                        mAdapter.notifyDataSetChanged();
                                                    }
                                                });
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {

                                    }
                                });
                    }
                });
    }

    /**
     * Contains this Fragment's findViewById calls to reduce clutter in onCreateview
     */
    void bindViews() {

        mArtistSearch =
                (EditText) mArtistSearchLayout.findViewById(R.id.etArtistSearch);

        mRecyclerView =
                (RecyclerView) mArtistSearchLayout.findViewById(R.id.rvArtistSearch);
    }

    void setUpRecyclerView() {

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new ArtistAdapter(getActivity());

        mAdapter.setArtistClickListener((MainActivity) getActivity());

        mRecyclerView.setAdapter(mAdapter);
    }

    void d(String msg) {

        Log.d("FragmentArtistSearch", msg);
    }

    @Override
    public void onResume() {
        super.onResume();

        String searchText =
                CurrentScenario.getInstance().getArtistSearchText();

        if (searchText != null) {
            mArtistSearch.setText(searchText);
        }
    }
}
