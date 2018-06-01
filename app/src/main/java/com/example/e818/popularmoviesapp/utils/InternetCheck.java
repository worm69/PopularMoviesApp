package com.example.e818.popularmoviesapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by E818 on 01/06/2018
 */
public class InternetCheck extends AsyncTask<Void,Void,Boolean> {

//    ou
//public boolean isOnline() {
//    ConnectivityManager cm =
//            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//    NetworkInfo netInfo = cm.getActiveNetworkInfo();
//    return netInfo != null && netInfo.isConnectedOrConnecting();
//}

    private Consumer mConsumer;
    public  interface Consumer { void accept(Boolean internet); }

    public  InternetCheck(Consumer consumer) { mConsumer = consumer; execute(); }

    @Override protected Boolean doInBackground(Void... voids) { try {
        Socket sock = new Socket();
        sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
        sock.close();
        return true;
    } catch (IOException e) { return false; } }

    @Override protected void onPostExecute(Boolean internet) { mConsumer.accept(internet); }
}
///////////////////////////////////////////////////////////////////////////////////
// Usage

//    new InternetCheck(internet -> { /* do something with boolean response */ });