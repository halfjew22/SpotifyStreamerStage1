package com.lustig.spotifystreamerstage1.model;

public class CurrentScenario {

    _Artist mCurrentArtist;

    private static CurrentScenario ourInstance = new CurrentScenario();

    public static CurrentScenario getInstance() {

        if (ourInstance == null) {
            ourInstance = new CurrentScenario();
        }

        return ourInstance;
    }

    private CurrentScenario() {
        mCurrentArtist = new _Artist("Nobody");
    }

    public void setCurrentArtist(_Artist artist) {
        mCurrentArtist = artist;
    }

    public _Artist getCurrentArtist() {
        return mCurrentArtist;
    }
}
