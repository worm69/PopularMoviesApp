package com.example.e818.popularmoviesapp.network.services;

import android.net.Uri;
import android.provider.BaseColumns;

public final class FavoriteMovieContract {
    private FavoriteMovieContract() {
        // nothing
    }

    static final String AUTHORITY = "com.example.e818.popularmoviesapp";

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    static final String PATH_MOVIES = "movies";
    static final String PATH_REVIEWS = "reviews";
    static final String PATH_VIDEOS = "videos";

    public static class MovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIES)
                .build();

        public static Uri getMovieById(final int movieId) {
            return CONTENT_URI
                    .buildUpon()
                    .appendPath(String.valueOf(movieId))
                    .build();
        }

        public static Uri getReviewsByMovieContentUri(final int movieId) {
            return CONTENT_URI
                    .buildUpon()
                    .appendPath(FavoriteMovieContract.PATH_REVIEWS)
                    .appendPath(String.valueOf(movieId))
                    .build();
        }

        public static Uri getVideosByMovieContentUri(final int movieId) {
            return CONTENT_URI
                    .buildUpon()
                    .appendPath(FavoriteMovieContract.PATH_VIDEOS)
                    .appendPath(String.valueOf(movieId))
                    .build();
        }

        static final String TABLE_NAME = "movie";

        static final String COLUMN_NAME_TITLE = "title";
        static final String COLUMN_NAME_OVERVIEW = "overview";
        static final String COLUMN_NAME_BACKDROP_PATH = "backdrop_path";
        static final String COLUMN_NAME_POSTER_PATH = "poster_path";
        static final String COLUMN_NAME_RELEASE_DATE = "release_date";
        static final String COLUMN_NAME_VOTE_AVERAGE = "vote_average";
    }

    public static class ReviewEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_REVIEWS)
                .build();

        static final String TABLE_NAME = "review";

        static final String COLUMN_NAME_MOVIE_ID = "movie_id";
        static final String COLUMN_NAME_AUTHOR = "author";
        static final String COLUMN_NAME_CONTENT = "content";
    }

    public static class VideoEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_VIDEOS)
                .build();

        static final String TABLE_NAME = "video";

        static final String COLUMN_NAME_MOVIE_ID = "movie_id";
        static final String COLUMN_NAME_KEY = "key";
    }
}
