package com.example.e818.popularmoviesapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    public final static Parcelable.Creator<Movie> CREATOR = new Creator<Movie>() {

        @SuppressWarnings({"unchecked"})
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Expose
    private int id;
    @SerializedName("poster_path")
    private String posterPath;
    @Expose
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @Expose
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("vote_average")
    private Float voteAverage;

    public Movie() {
        //
    }

    private Movie(Parcel in) {
        id = in.readInt();
        posterPath = in.readString();
        backdropPath = in.readString();

        title = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readFloat();
        overview = in.readString();
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPosterPath(final String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setOverview(final String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setReleaseDate(final String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setBackdropPath(final String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setVoteAverage(final Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);

        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeFloat(voteAverage);
        dest.writeString(overview);
    }
}
