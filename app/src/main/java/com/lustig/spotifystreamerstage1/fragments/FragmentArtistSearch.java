package com.lustig.spotifystreamerstage1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lustig.spotifystreamerstage1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentArtistSearch extends Fragment {

    View mArtistSearchLayout;

    EditText mArtistSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mArtistSearchLayout = inflater.inflate(
                R.layout.fragment_artist_search, container, false);

        bindViews();

        setTextChangedWatcher();

        return mArtistSearchLayout;
    }

    private void setTextChangedWatcher() {
        mArtistSearch.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        d(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
    }

    /**
     * Contains this Fragment's findViewById calls to reduce clutter in onCreateview
     */
    void bindViews() {
        mArtistSearch =
                (EditText) mArtistSearchLayout.findViewById(R.id.etArtistSearch);
    }

    void d(String msg) {
        Log.d("FragmentArtistSearch", msg);
    }
}
