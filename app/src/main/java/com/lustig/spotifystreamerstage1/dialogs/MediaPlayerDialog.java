package com.lustig.spotifystreamerstage1.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.model.CurrentScenario;

/**
 * This Fragment will be used to play the clip of the
 * song. It will be displayed as an embedded Fragment
 * on smaller screens, and as a Dialog on larger screens.
 *
 * Regardless, the layout of the Fragment will be identical.
 */
public class MediaPlayerDialog extends DialogFragment {

    boolean mIsPlaying = false;

    ImageButton mPlayButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.media_player, container, false);

        mPlayButton = (ImageButton) root.findViewById(R.id.togglePlay);

        mPlayButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        togglePlay(v);
                    }
                });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        ((TextView) getView().findViewById(R.id.track_name)).setText(CurrentScenario.getInstance().getCurrentTrack().getTitle());


    }

    public void togglePlay(View v) {

        /**
         * If media is already playing, clicking this button
         * should pause playback and swap the button.
         */
        if (mIsPlaying) {

            // Swap button from play to pause
            ((ImageButton) v).setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));

            // Pause playback
            mIsPlaying = false;

        } else {

            // Swap button from pause to play
            ((ImageButton) v).setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));

            // Start playback
            mIsPlaying = true;
        }

    }
}
