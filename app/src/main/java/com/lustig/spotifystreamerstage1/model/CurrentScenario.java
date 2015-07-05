package com.lustig.spotifystreamerstage1.model;

import java.util.ArrayList;

public class CurrentScenario {

    String mArtistSearchText;

    _Artist mCurrentArtist;
    _Track mCurrentTrack;

    private static CurrentScenario ourInstance = new CurrentScenario();

    private ArrayList<_Track> mCurrentTrackList;

    public static CurrentScenario getInstance() {

        if (ourInstance == null) {
            ourInstance = new CurrentScenario();
        }

        return ourInstance;
    }

    private CurrentScenario() {}

    public void setArtistSearchText(String artistSearchText) {
        mArtistSearchText = artistSearchText;
    }

    public void setCurrentArtist(_Artist artist) {
        mCurrentArtist = artist;
    }

    public void setCurrentTrack(_Track currentTrack) {
        mCurrentTrack = currentTrack;
    }

    public String getArtistSearchText() {
        return mArtistSearchText;
    }

    public _Artist getCurrentArtist() {
        return mCurrentArtist;
    }

    public _Track getCurrentTrack() {
        return mCurrentTrack;
    }

    public _Track getNextTrack() {

        _Track nextTrack = null;
        int nextTrackPosition = -1;

        int currentTrackPosition =
                getCurrentTrackList().indexOf(getCurrentTrack());

        if (currentTrackPosition >= 0 && currentTrackPosition <= getCurrentTrackList().size() - 2) {
            nextTrackPosition = currentTrackPosition + 1;
        }

        if (nextTrackPosition != -1) {
            nextTrack = getCurrentTrackList().get(nextTrackPosition);
        }

        return nextTrack;
    }

    public _Track getPreviousTrack() {

        _Track previousTrack = null;
        int previousTrackPosition = -1;

        int currentTrackPosition =
                getCurrentTrackList().indexOf(getCurrentTrack());

        if (currentTrackPosition >= 1) {
            previousTrackPosition = currentTrackPosition - 1;
        }

        if (previousTrackPosition != -1) {
            previousTrack = getCurrentTrackList().get(previousTrackPosition);
        }

        return previousTrack;
    }

    private ArrayList<_Track> getCurrentTrackList() {
       return mCurrentTrackList;
    }

    public void setCurrentTrackList(ArrayList<_Track> currentTrackList) {
        mCurrentTrackList = currentTrackList;
    }
}
