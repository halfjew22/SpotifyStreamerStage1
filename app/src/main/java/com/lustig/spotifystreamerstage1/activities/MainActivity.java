package com.lustig.spotifystreamerstage1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.dialogs.MediaPlayerDialog;
import com.lustig.spotifystreamerstage1.fragments.FragmentTopTracks;
import com.lustig.spotifystreamerstage1.interfaces.OnArtistClickListener;
import com.lustig.spotifystreamerstage1.interfaces.OnTrackClickListener;
import com.lustig.spotifystreamerstage1.model.CurrentScenario;
import com.lustig.spotifystreamerstage1.model._Artist;
import com.lustig.spotifystreamerstage1.model._Track;

public class MainActivity extends AppCompatActivity
        implements OnArtistClickListener, OnTrackClickListener {

    Toast mToast;

    boolean mTwoPane = false;

    FragmentTopTracks mTopTracksFragment = null;

    MediaPlayerDialog mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTwoPane = findViewById(R.id.fragment_detail_container) != null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        super.onSaveInstanceState(outState, outPersistentState);
    }

    /**
     * This method handles clicks on the Artist list.
     * <p/>
     * When an artist is clicked, I want to start a new
     * Activity with a Fragment displaying the top
     * number of tracks from that current artist.
     * <p/>
     * Soon, I'll have to check two pane layouts.
     * For now, I'm starting a new Activity for
     * certain every click.
     *
     * @param artist
     */
    @Override
    public void onArtistClick(_Artist artist) {

        toast(artist.getName());

        CurrentScenario.getInstance().setCurrentArtist(artist);

        /**
         * If our Activity only has a single pane, then I need
         * to start a new Activity to display the top tracks.
         *
         * Conversely, I will have two panes, in which case I should
         * show the detail in the other pane.
         */
        if (!mTwoPane) {
            startActivity(new Intent(this, TopTracksActivity.class));
        } else {

            mTopTracksFragment = new FragmentTopTracks();

            getSupportFragmentManager().beginTransaction()
                                       .replace(R.id.fragment_detail_container, mTopTracksFragment)
                                       .commit();
        }
    }

    public void clearTracks() {
        if (null != mTopTracksFragment) {
            mTopTracksFragment.clearTracks();
        }
    }

    public void toast(String msg) {

        // If a toast is already being shown, cancel it
        if (mToast != null) {
            mToast.cancel();
        }

        mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    @Override
    public void onTrackClick(_Track track) {

        /**
         * If we aren't in two pane mode, don't do anything
         * (let the TopTracksActivity do the work)
         */
        if (!mTwoPane) {
            return;
        }

        CurrentScenario.getInstance().setCurrentTrack(track);

        if (mMediaPlayer != null) {
            mMediaPlayer.stopMediaPlayer();
        }

        mMediaPlayer = new MediaPlayerDialog();
        mMediaPlayer.show(getSupportFragmentManager(), "dialog");
    }
}
