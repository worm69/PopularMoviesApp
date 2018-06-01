package com.example.e818.popularmoviesapp.network;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by E818 on 01/06/2018
 */
public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    ///     movie/popular and /movie/top_rated endpoints.
    private static final String STATIC_THEMOVIEDB_URL = "https://api.themoviedb.org/3/movie/";

//    For example, the poster path return for Interstellar is
//“/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg”
//    You will need to append a base path ahead of this relative path to build the complete url you
//    will need to fetch the image using Picasso.
//    It’s constructed using 3 parts:
//            1. The base URL will look like: http://image.tmdb.org/t/p/.
//            2. Then you will need a ‘size’, which will be one of the following: "w92", "w154", "w185",
//            "w342", "w500", "w780", or "original". For most phones we recommend using “w185”.
//            3. And finally the poster path returned by the query, in this case
//            “/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg”
//    Combining these three parts gives us a final url of
//    http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg

    private static final String STATIC_BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";
    private static final String STATIC_RECOMMENDED_SIZE_IMAGE = "w185";

//    Linguagem
//    https://api.themoviedb.org/3/movie/76341?api_key=<<api_key>>&language=pt-BR
    private static final String STATIC_BASE_LANGUAGE_QUERY = "pt-PT";
//    Query para
    private static final String QUERY_API_KEY ="api_key";
    private static final String QUERY_LANGUAGE = "language";
    private static final String QUERY_SORT_ORDER_DEFAULT = "/popular";
    /**
     * Retrieves the proper URL to query for the weather data. The reason for both this method as
     * well as {@link #buildUrlWithLocationQuery(String)} is two fold.
     * <p>
     * 1) You should be able to just use one method when you need to create the URL within the
     * app instead of calling both methods.
     * 2) Later in Sunshine, you are going to add an alternate method of allowing the user
     * to select their preferred location. Once you do so, there will be another way to form
     * the URL using a latitude and longitude rather than just a location String. This method
     * will "decide" which URL to build and return it.
     *
     * @param context used to access other Utility methods
     * @return URL to query weather service
/*     *//*
    public static URL getUrl(Context context) {
        if (SunshinePreferences.isLocationLatLonAvailable(context)) {
            double[] preferredCoordinates = SunshinePreferences.getLocationCoordinates(context);
            double latitude = preferredCoordinates[0];
            double longitude = preferredCoordinates[1];
            return buildUrlWithLatitudeLongitude(latitude, longitude);
        } else {
            String locationQuery = SunshinePreferences.getPreferredWeatherLocation(context);
            return buildUrlWithLocationQuery(locationQuery);
        }
    }*/

    /**
     * Builds the URL used to talk to the weather server using latitude and longitude of a
     * location.
     *
     *  latitude  The latitude of the location
     *  longitude The longitude of the location
     * @return The Url to use to query the weather server.
     */
    private static URL buildUrl() {
        Uri weatherQueryUri = Uri.parse(STATIC_THEMOVIEDB_URL).buildUpon()
                .appendPath(QUERY_SORT_ORDER_DEFAULT)
                .appendQueryParameter(QUERY_API_KEY,"4f2d9b150437a897db3e4112ff29e085")
//                .appendQueryParameter(LAT_PARAM, String.valueOf(latitude))
//                .appendQueryParameter(LON_PARAM, String.valueOf(longitude))
//                .appendQueryParameter(FORMAT_PARAM, format)
//                .appendQueryParameter(UNITS_PARAM, units)
//                .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
                .build();

        try {
            URL weatherQueryUrl = new URL(weatherQueryUri.toString());
            Log.v(TAG, "URL: " + weatherQueryUrl);
            return weatherQueryUrl;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }




    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response, null if no response
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            String response = null;
            if (hasInput) {
                response = scanner.next();
            }
            scanner.close();
            return response;
        } finally {
            urlConnection.disconnect();
        }
    }

}
