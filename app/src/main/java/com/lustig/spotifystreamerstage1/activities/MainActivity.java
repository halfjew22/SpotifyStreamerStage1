package com.lustig.spotifystreamerstage1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.interfaces.OnArtistClickListener;
import com.lustig.spotifystreamerstage1.model.CurrentScenario;
import com.lustig.spotifystreamerstage1.model._Artist;

public class MainActivity extends AppCompatActivity implements OnArtistClickListener {

    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method handles clicks on the Artist list.
     *
     * When an artist is clicked, I want to start a new
     * Activity with a Fragment displaying the top
     * number of tracks from that current artist.
     *
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

        startActivity(new Intent(this, TopTracksActivity.class));
    }

    public void toast(String msg) {

        // If a toast is already being shown, cancel it
        if (mToast != null) {
            mToast.cancel();
        }

        mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        mToast.show();
    }
}
