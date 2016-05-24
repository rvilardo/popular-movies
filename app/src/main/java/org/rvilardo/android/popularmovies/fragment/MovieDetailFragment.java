package org.rvilardo.android.popularmovies.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.rvilardo.android.popularmovies.R;
import org.rvilardo.android.popularmovies.async.FetchMovieDetailTask;
import org.rvilardo.android.popularmovies.bean.Movie;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class MovieDetailFragment extends Fragment {

    private final String LOG_TAG = MovieDetailFragment.class.getSimpleName();
    private Movie movieSelected;

    public static final String MOVIE = "MOVIE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.movie_detail_fragment, container, false);

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(MOVIE)) {
            movieSelected = (Movie) intent.getSerializableExtra(MOVIE);
            ((TextView) rootView.findViewById(R.id.detail_text)).setText(movieSelected.getId().toString());
            Log.v(LOG_TAG, "movieIdSelected =" + movieSelected.getId().toString());

            new FetchMovieDetailTask().execute(movieSelected.getId());

            Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w342" + movieSelected.getPosterPath()).placeholder(R.mipmap.ic_launcher).into(
                    (ImageView) rootView.findViewById(R.id.movie_item_detail_image_view)
            );
        }

        return rootView;
    }
}
