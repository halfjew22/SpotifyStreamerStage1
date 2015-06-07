package com.lustig.spotifystreamerstage1.api;


import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;

public class SpotifyHelper {

    private static SpotifyApi sApi;

    public static SpotifyApi getInstance() {

        if (sApi == null) {
            sApi = new SpotifyApi();
        }

        return sApi;
    }

    public SpotifyService getService() {
        return sApi.getService();
    }
}
