package org.rvilardo.android.popularmovies.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.rvilardo.android.popularmovies.R;
import org.rvilardo.android.popularmovies.activity.MovieDetailActivity;
import org.rvilardo.android.popularmovies.adapter.MovieAdapter;
import org.rvilardo.android.popularmovies.async.FetchMoviesTask;
import org.rvilardo.android.popularmovies.bean.Movie;
import org.rvilardo.android.popularmovies.bean.MovieListListener;
import org.rvilardo.android.popularmovies.util.MoviesSortMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rvilardo on 5/18/2016.
 */
public class MoviesFragment extends Fragment implements MovieListListener {

    private MovieAdapter movieAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movies_fragment, container, false);
        movieAdapter = new MovieAdapter(getActivity(), new ArrayList<Movie>());

        GridView moviesGrid = (GridView) rootView.findViewById(R.id.moviesGrid);
        moviesGrid.setAdapter(movieAdapter);
        moviesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movieSelected =  movieAdapter.getItem(position);
                //Toast.makeText(getActivity(), forecastSelected, Toast.LENGTH_SHORT).show();

                Intent detailIntent = new Intent(getActivity(),MovieDetailActivity.class).putExtra(MovieDetailFragment.MOVIE, movieSelected);
                startActivity(detailIntent);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        new FetchMoviesTask(this).execute(getSortMode());
    }

    @Override
    public void onMovieListLoaded(List<Movie> movies) {
        movieAdapter.clear();
        movieAdapter.addAll(movies);
    }

    private MoviesSortMode getSortMode() {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortMode = s.getString(getString(R.string.pref_sort_key),getString(R.string.pref_sort_default));

        return MoviesSortMode.valueOf(sortMode.toUpperCase());
    }
}
