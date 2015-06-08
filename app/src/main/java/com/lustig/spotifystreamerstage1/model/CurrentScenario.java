package com.lustig.spotifystreamerstage1.model;

public class CurrentScenario {

    String mArtistSearchText;

    _Artist mCurrentArtist;
    _Track mCurrentTrack;

    private static CurrentScenario ourInstance = new CurrentScenario();

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
}
