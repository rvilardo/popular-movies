package org.rvilardo.android.popularmovies.async;

import android.os.AsyncTask;
import android.util.Log;

import org.rvilardo.android.popularmovies.BuildConfig;
import org.rvilardo.android.popularmovies.bean.Movie;
import org.rvilardo.android.popularmovies.bean.MovieListListener;
import org.rvilardo.android.popularmovies.service.MovieService;
import org.rvilardo.android.popularmovies.util.MoviesSortMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvilardo on 5/18/2016.
 */
public class FetchMoviesTask extends AsyncTask<MoviesSortMode, Void, List<Movie>> {

    private static final String LOG_TAG = FetchMoviesTask.class.getSimpleName();
    private MovieListListener movieListListener;
    private MovieService movieService = new MovieService(BuildConfig.MOVIE_DB_API_KEY);

    public FetchMoviesTask(MovieListListener movieListListener) {
        this.movieListListener = movieListListener;
    }

    @Override
    protected List<Movie> doInBackground(MoviesSortMode... params) {
        Log.v(LOG_TAG, "doing in background");
        MoviesSortMode mode = params[0];

        try {
            if (mode == MoviesSortMode.MOST_POPULAR) {
                return movieService.getMostPopularMovies();
            } else {
                return movieService.getTopRatedMovies();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        movieListListener.onMovieListLoaded(movies);
    }
}
