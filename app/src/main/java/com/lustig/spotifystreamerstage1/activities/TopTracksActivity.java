package com.lustig.spotifystreamerstage1.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.interfaces.OnTrackClickListener;
import com.lustig.spotifystreamerstage1.model.CurrentScenario;
import com.lustig.spotifystreamerstage1.model._Track;
import com.lustig.spotifystreamerstage1.utility.U;

public class TopTracksActivity extends AppCompatActivity implements OnTrackClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_top_tracks);
    }

    @Override
    public void onTrackClick(_Track track) {

        CurrentScenario.getInstance().setCurrentTrack(track);

        U.d(track.getPreviewUrl());
    }
}
