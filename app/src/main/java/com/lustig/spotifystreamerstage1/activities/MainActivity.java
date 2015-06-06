package com.lustig.spotifystreamerstage1.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lustig.spotifystreamerstage1.R;
import com.lustig.spotifystreamerstage1.fragments.FragmentArtistSearch;
import com.lustig.spotifystreamerstage1.interfaces.OnArtistCardClickListener;
import com.lustig.spotifystreamerstage1.model.Artist;

import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Album;
import kaaes.spotify.webapi.android.models.ArtistSimple;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements OnArtistCardClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentArtistSearch f =
                (FragmentArtistSearch)
                        getSupportFragmentManager()
                                .findFragmentById(R.id.fragmentArtistSearch);


        SpotifyApi api = new SpotifyApi();

//        api.setAccessToken(SPOTIFY_ACCESS_TOKEN);

        SpotifyService spotify = api.getService();

        spotify.getAlbum(
                "2dIGnmEIy1WZIcZCFSj6i8", new Callback<Album>() {

                    @Override
                    public void success(Album album, Response response) {

                        List<ArtistSimple> artists = album.artists;

                        for (ArtistSimple artist : artists) {
                            d(artist.name);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        d(error.getMessage());
                    }
                });

        spotify.searchArtists(
                "The Used", new Callback<ArtistsPager>() {

                    @Override
                    public void success(ArtistsPager artistsPager, Response response) {
                        List<kaaes.spotify.webapi.android.models.Artist> artists = artistsPager.artists.items;
                        for (kaaes.spotify.webapi.android.models.Artist a : artists) {
                            d(a.name);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

    }

    @Override
    public void onArtistClick(Artist artist) {
        d(artist.getName());
    }

    void d(String msg) {
        Log.d("MainActivity", msg);
    }
}
