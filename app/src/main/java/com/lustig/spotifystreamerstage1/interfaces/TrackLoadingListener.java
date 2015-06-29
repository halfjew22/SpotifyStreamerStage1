package com.lustig.spotifystreamerstage1.interfaces;

import com.lustig.spotifystreamerstage1.model.TrackList;

/**
 * This interface serves as a listener for
 * callbacks to tracks list loading process completed.
 */
public interface TrackLoadingListener {

    void onLoadingComplete(TrackList list);

}
