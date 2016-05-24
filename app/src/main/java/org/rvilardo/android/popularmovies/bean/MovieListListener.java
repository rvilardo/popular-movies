package org.rvilardo.android.popularmovies.bean;

import java.util.List;

/**
 * Created by rvilardo on 5/19/2016.
 */
public interface MovieListListener {

    void onMovieListLoaded(List<Movie> movies);
}
