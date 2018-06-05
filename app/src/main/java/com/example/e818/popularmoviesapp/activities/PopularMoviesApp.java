package com.example.e818.popularmoviesapp.activities;

import android.app.Application;

import com.facebook.stetho.Stetho;


public class PopularMoviesApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
