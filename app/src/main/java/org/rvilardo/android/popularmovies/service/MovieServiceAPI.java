package org.rvilardo.android.popularmovies.service;

import org.rvilardo.android.popularmovies.bean.MovieDetail;
import org.rvilardo.android.popularmovies.bean.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rvilardo on 5/23/2016.
 */
public interface MovieServiceAPI {

    @GET("movie/popular")
    Call<MovieList> getMostPopularMovies();

    @GET("movie/top_rated")
    Call<MovieList> getTopRatedMovies();

    @GET("movie/{id}")
    Call<MovieDetail> getMovieDetail(@Path("id") Long id);
}
