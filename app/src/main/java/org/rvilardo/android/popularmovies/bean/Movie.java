package org.rvilardo.android.popularmovies.bean;

import java.io.Serializable;

/**
 * Created by rvilardo on 5/18/2016.
 */
public class Movie implements Serializable {
    private Long id;
    private String posterPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
