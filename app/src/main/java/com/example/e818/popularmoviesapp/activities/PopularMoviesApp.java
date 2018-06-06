package com.example.e818.popularmoviesapp.activities;

import android.app.Application;

import com.facebook.stetho.Stetho;

// Uso de Stetho para debug
// https://github.com/facebook/stetho
// chrome://inspect
public class PopularMoviesApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
