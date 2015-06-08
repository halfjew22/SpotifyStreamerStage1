package com.lustig.spotifystreamerstage1.model;

public class CurrentScenario {

    _Artist mCurrentArtist;
    String mArtistSearchText;

    private static CurrentScenario ourInstance = new CurrentScenario();

    public static CurrentScenario getInstance() {

        if (ourInstance == null) {
            ourInstance = new CurrentScenario();
        }

        return ourInstance;
    }

    private CurrentScenario() {}

    public void setCurrentArtist(_Artist artist) {
        mCurrentArtist = artist;
    }

    public void setArtistSearchText(String artistSearchText) {
        mArtistSearchText = artistSearchText;
    }

    public _Artist getCurrentArtist() {
        return mCurrentArtist;
    }

    public String getArtistSearchText() {
        return mArtistSearchText;
    }
}
