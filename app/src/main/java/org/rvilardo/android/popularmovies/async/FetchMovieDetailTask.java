package org.rvilardo.android.popularmovies.async;

import android.os.AsyncTask;

import org.rvilardo.android.popularmovies.BuildConfig;
import org.rvilardo.android.popularmovies.bean.MovieDetail;
import org.rvilardo.android.popularmovies.bean.MovieDetailListener;
import org.rvilardo.android.popularmovies.service.MovieService;

import java.io.IOException;

/**
 * Created by rvilardo on 5/24/2016.
 */
public class FetchMovieDetailTask extends AsyncTask<Long, Void, MovieDetail> {
    private static final String LOG_TAG = FetchMovieDetailTask.class.getSimpleName();
    private MovieDetailListener listener;
    private MovieService movieService = new MovieService(BuildConfig.MOVIE_DB_API_KEY);

    public FetchMovieDetailTask(MovieDetailListener listener) {
        this.listener = listener;
    }

    @Override
    protected MovieDetail doInBackground(Long... params) {
        Long movieIdSelected = params[0];
        try {
            return movieService.getMovieDetail(movieIdSelected);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MovieDetail();
    }

    @Override
    protected void onPostExecute(MovieDetail movieDetail) {
        listener.onMovieDetailLoaded(movieDetail);
    }
}
