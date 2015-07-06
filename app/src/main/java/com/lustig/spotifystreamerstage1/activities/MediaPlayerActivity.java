package com.lustig.spotifystreamerstage1.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.lustig.spotifystreamerstage1.dialogs.MediaPlayerDialog;

public class MediaPlayerActivity extends AppCompatActivity {

    private MediaPlayerDialog mMediaPlayerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            mMediaPlayerDialog = new MediaPlayerDialog();

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(android.R.id.content, mMediaPlayerDialog)
                    .commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mMediaPlayerDialog.stopMediaPlayer();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        mMediaPlayerDialog.stopMediaPlayer();
    }
}
