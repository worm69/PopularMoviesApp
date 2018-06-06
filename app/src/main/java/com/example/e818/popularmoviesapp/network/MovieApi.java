package com.example.e818.popularmoviesapp.network;

import java.util.ArrayList;

import com.example.e818.popularmoviesapp.BuildConfig;
import com.example.e818.popularmoviesapp.models.Movie;
import com.example.e818.popularmoviesapp.models.Result;
import com.example.e818.popularmoviesapp.models.Review;
import com.example.e818.popularmoviesapp.models.Video;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class MovieApi {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static MovieApi sInstance;

    private final TheMovieDbService mService;
    private Call<Result<Movie>> mLastCall;

    private MovieApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = retrofit.create(TheMovieDbService.class);
    }

    public static MovieApi instance() {
        if (sInstance == null) {
            sInstance = new MovieApi();
        }

        return sInstance;
    }

    private Call<Result<Movie>> tryToCancelLastCall(final Call<Result<Movie>> lastCall, Call<Result<Movie>> call) {
        if (lastCall != null && !lastCall.isCanceled() && !lastCall.isExecuted()) {
            lastCall.cancel();
        }

        return call;
    }

    public void getTopRatedMovies(final MovieResultListener movieListener, int pageindex, boolean append) {
        mLastCall = tryToCancelLastCall(mLastCall, mService.getTopRatedMovies(BuildConfig.THE_MOVIE_DB_APP_KEY, pageindex));
        executeCall(mLastCall, movieListener, append);
    }

    public void getPopularMovies(final MovieResultListener movieListener, int pageindex, boolean append) {
        mLastCall = tryToCancelLastCall(mLastCall, mService.getPopularMovies(BuildConfig.THE_MOVIE_DB_APP_KEY, pageindex));
        executeCall(mLastCall, movieListener, append);
    }

    public void getVideosByMovies(final int movieId, final VideoResultListener videoListener,  boolean append) {
        executeCall(mService.getVideosByMovies(movieId, BuildConfig.THE_MOVIE_DB_APP_KEY), videoListener, append);
    }

    public void getReviewsByMovies(final int movieId, final ReviewResultListener reviewListener,  boolean append) {
        executeCall(mService.getReviewsByMovies(movieId, BuildConfig.THE_MOVIE_DB_APP_KEY), reviewListener, append);
    }

    private <T> void executeCall(final Call<Result<T>> call, final ResultListener<T> listener, final boolean append) {
        if (listener == null) {
            return;
        }

        call.enqueue(new Callback<Result<T>>() {
            @Override
            public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    final Result<T> result = response.body();
                    listener.onSuccessResult(result.getResults(), result.getTotalResults(), result.getTotalPages(), append);
                } else {
                    listener.onFailure(new RuntimeException(response.message()));
                }
            }

            @Override
            public void onFailure(Call<Result<T>> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    public interface ResultListener<T> {
        void onSuccessResult(ArrayList<T> results, int totalResults, int totalPages, boolean append);

        void onFailure(Throwable exception);
    }

    public interface MovieResultListener extends ResultListener<Movie> {
        // interface listener for Movies
    }

    public interface VideoResultListener extends ResultListener<Video> {
        // interface listener for Videos
    }

    public interface ReviewResultListener extends ResultListener<Review> {
        // interface listener for Reviews
    }
}
