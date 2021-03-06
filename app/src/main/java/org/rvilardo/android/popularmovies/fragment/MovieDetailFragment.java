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
import org.rvilardo.android.popularmovies.bean.MovieDetail;
import org.rvilardo.android.popularmovies.bean.MovieDetailListener;

import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class MovieDetailFragment extends Fragment implements MovieDetailListener {

    private final String LOG_TAG = MovieDetailFragment.class.getSimpleName();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    public static final String MOVIE = "MOVIE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.movie_detail_fragment, container, false);

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(MOVIE)) {
            Movie movieSelected = (Movie) intent.getSerializableExtra(MOVIE);

            Log.v(LOG_TAG, "movieIdSelected =" + movieSelected.getId().toString());

            new FetchMovieDetailTask(this).execute(movieSelected.getId());

            Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w342" + movieSelected.getPosterPath()).placeholder(R.mipmap.ic_launcher).into(
                    (ImageView) rootView.findViewById(R.id.movie_item_detail_image_view)
            );
        }

        return rootView;
    }


    @Override
    public void onMovieDetailLoaded(MovieDetail movieDetail) {
        Log.v(LOG_TAG, "movieDetail=" + movieDetail.getOriginalTitle());

        ((TextView) getActivity().findViewById(R.id.movie_item_detail_title)).setText(movieDetail.getOriginalTitle());
        ((TextView) getActivity().findViewById(R.id.movie_item_detail_year)).setText(sdf.format(movieDetail.getReleaseDate()));
        ((TextView) getActivity().findViewById(R.id.movie_item_detail_duration)).setText(String.format("%dmin", movieDetail.getRuntime()));
        ((TextView) getActivity().findViewById(R.id.movie_item_detail_rating)).setText(String.format("%s/%d", movieDetail.getVoteAverage(),10));
        ((TextView) getActivity().findViewById(R.id.movie_item_detail_description)).setText(movieDetail.getOverview());
    }
}
