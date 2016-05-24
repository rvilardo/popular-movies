package org.rvilardo.android.popularmovies.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.rvilardo.android.popularmovies.bean.Movie;
import org.rvilardo.android.popularmovies.bean.MovieDetail;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by rvilardo on 5/23/2016.
 */
public class MovieService {
    private static final String MOVIE_DB_BASE_URL = "https://api.themoviedb.org/3/";
//    private String appKey;
    private MovieServiceAPI serviceAPI;

    public MovieService(final String appKey) {
//        this.appKey = appKey;
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request request = chain.request();
                        final HttpUrl url = request.url().newBuilder()
                                .addQueryParameter("api_key", appKey)
                                .build();
                        return chain.proceed(request.newBuilder().url(url).build());
                    }
                })
                .build();

        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder().client(client)
                .baseUrl(MOVIE_DB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        serviceAPI = retrofit.create(MovieServiceAPI.class);
    }


    public List<Movie> getMostPopularMovies() throws IOException {
        return serviceAPI.getMostPopularMovies().execute().body().getResults();
    }

    public List<Movie> getTopRatedMovies() throws IOException {
        return serviceAPI.getTopRatedMovies().execute().body().getResults();
    }

    public MovieDetail getMovieDetail(Long id) throws IOException {
        return serviceAPI.getMovieDetail(id).execute().body();
    }
}
