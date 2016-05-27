package org.rvilardo.android.popularmovies.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import org.rvilardo.android.popularmovies.R;
import org.rvilardo.android.popularmovies.fragment.SettingsFragment;

import java.util.List;

/**
 * Created by rvilardo on 5/23/2016.
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

}
