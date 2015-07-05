package com.lustig.spotifystreamerstage1.dialogs;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.model.CurrentScenario;
import com.lustig.spotifystreamerstage1.model._Track;
import com.squareup.picasso.Picasso;

/**
 * This Fragment will be used to play the clip of the
 * song. It will be displayed as an embedded Fragment
 * on smaller screens, and as a Dialog on larger screens.
 * <p/>
 * Regardless, the layout of the Fragment will be identical.
 */
public class MediaPlayerDialog extends DialogFragment implements View.OnTouchListener {

    boolean mIsPlaying = false;
    int mPreviewLength = 0;

    ImageButton mPreviousTrackButton;
    ImageButton mTogglePlayButton;
    ImageButton mNextTrackButton;

    ImageView mAlbumArtImageView;

    TextView mArtistNameTextView;
    TextView mAlbumNameTextView;
    TextView mTrackNameTextView;

    MediaPlayer mPlayer;

    SeekBar mSeekBar;

    private final Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.media_player, container, false);

        mPlayer = new MediaPlayer();

        mPreviousTrackButton = (ImageButton) root.findViewById(R.id.previous_track);
        mTogglePlayButton = (ImageButton) root.findViewById(R.id.togglePlay);
        mNextTrackButton = (ImageButton) root.findViewById(R.id.next_track);

        mSeekBar = (SeekBar) root.findViewById(R.id.seekBar);
        mSeekBar.setMax(99);
        mSeekBar.setOnTouchListener(this);

        mPreviousTrackButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        playPreviousTrack();
                    }
                });

        mTogglePlayButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        togglePlay(v);
                    }
                });

        mNextTrackButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        playNextTrack();
                    }
                });

        return root;
    }

    @Override
    public void onStart() {

        super.onStart();

        bindViews();
        updateMediaPlayer();
    }

    private void bindViews() {

        mArtistNameTextView = (TextView) getView().findViewById(R.id.artist_name);
        mAlbumNameTextView = (TextView) getView().findViewById(R.id.album_name);
        mTrackNameTextView = (TextView) getView().findViewById(R.id.track_name);

        mAlbumArtImageView = (ImageView) getView().findViewById(R.id.album_art);
    }

    private void updateMediaPlayer() {

        setArtistTextView();
        setAlbumTextView();
        setTrackTitleTextView();

        setAlbumArt();

        mPlayer.reset();

        try {
            mPlayer.setDataSource(CurrentScenario.getInstance().getCurrentTrack().getPreviewUrl());
            mPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mPreviewLength = mPlayer.getDuration();

        updateSeekBar();

        mPlayer.start();
        mTogglePlayButton.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));
    }

    private void updateSeekBar() {

        int progress = (int) (((float) mPlayer.getCurrentPosition() / mPreviewLength) * 100);

        mSeekBar.setProgress(progress);

        if (mPlayer.isPlaying()) {
            Runnable updateSeekBarRunnable = new Runnable() {

                @Override
                public void run() {
                    updateSeekBar();
                }
            };
            handler.postDelayed(updateSeekBarRunnable, 1000);
        }

    }

    private void setArtistTextView() {

        mArtistNameTextView.setText(CurrentScenario.getInstance().getCurrentArtist().getName());
    }

    private void setAlbumTextView() {

        mAlbumNameTextView.setText(CurrentScenario.getInstance().getCurrentTrack().getAlbumName());
    }

    private void setAlbumArt() {

        String mImageUrl = CurrentScenario.getInstance().getCurrentTrack().getAlbumArtUrl();

        Picasso.with(getActivity()).load(mImageUrl).into(mAlbumArtImageView);
    }

    private void setTrackTitleTextView() {

        mTrackNameTextView.setText(CurrentScenario.getInstance().getCurrentTrack().getTitle());
    }

    public void togglePlay(View v) {

        mIsPlaying = mPlayer.isPlaying();

        /**
         * If media is already playing, clicking this button
         * should pause playback and swap the button.
         */
        if (mIsPlaying) {

            // Swap button from play to pause
            ((ImageButton) v).setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));

            // Pause playback
            mPlayer.pause();
            mIsPlaying = false;

        } else {

            // Swap button from pause to play
            ((ImageButton) v).setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));

            try {
                mPlayer.setDataSource(CurrentScenario.getInstance().getCurrentTrack().getPreviewUrl());
                mPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Start playback
            mPlayer.start();

            mIsPlaying = true;
        }
    }

    public void playNextTrack() {

        // First, I need to determine what the next track is
        _Track nextTrack = CurrentScenario.getInstance().getNextTrack();

        // If the nextTrack is null, we're already playing the last track

        /**
         * A couple of options here:
         * 1) We could loop back to the first Track
         * 2) We could tell the user that that's the end of the Track list
         */
        if (nextTrack != null) {
            CurrentScenario.getInstance().setCurrentTrack(nextTrack);
            updateMediaPlayer();
        } else {
            Toast.makeText(getActivity(), "No more tracks =(", Toast.LENGTH_SHORT).show();
        }
    }

    public void playPreviousTrack() {

        _Track previousTrack = CurrentScenario.getInstance().getPreviousTrack();

        if (previousTrack != null) {
            CurrentScenario.getInstance().setCurrentTrack(previousTrack);
            updateMediaPlayer();
        } else {
            Toast.makeText(getActivity(), "Already on first track =(", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.seekBar) {

            int playPositionMilliseconds = (mPreviewLength / 100) * mSeekBar.getProgress();
            mPlayer.seekTo(playPositionMilliseconds);
        }

        return false;
    }
}
