package com.example.e818.popularmoviesapp.views.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.example.e818.popularmoviesapp.R;
import com.example.e818.popularmoviesapp.models.Movie;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private static final String TAG = "MoviesAdapter";
    public static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w185";
//    TODO : usar imagens large no caso de a resolução ser > 768
    public static final String BASE_IMAGE_LARGER_URL = "http://image.tmdb.org/t/p/w342";
    private OnBottomReachedListener onBottomReachedListener;
    private final ListItemClickListener mOnClickListener;
    private List<Movie> mMovies;
//  New pagination
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;

    public MoviesAdapter(final ListItemClickListener onClickItemListener, List<Movie> movies) {
        Log.d(TAG, movies.size() + " items loaded");
        mMovies = movies;
        mOnClickListener = onClickItemListener;
    }

    public MoviesAdapter(final ListItemClickListener onClickItemListener) {
        this(onClickItemListener, new ArrayList<Movie>());
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(view);
    }
//TODO: Remover trocar por melhor sistema
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
        //added
        if ((position+1)%20 == 0 && position !=0){

            onBottomReachedListener.onBottomReached(position);
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.mMovies = movies;
        notifyDataSetChanged();
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public interface ListItemClickListener {
        void onListItemClick(final Movie movie);
    }

    //ADDED
    public interface OnBottomReachedListener {

        void onBottomReached(int position);

    }
    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener){

        this.onBottomReachedListener = onBottomReachedListener;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_movie_poster_image)
        ImageView ivMoviePosterImage;
        @BindView(R.id.tv_movie_title)
        TextView tvMovieTitle;

        MovieViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        void bind(final Movie movie) {
            final Uri url = Uri.parse(BASE_IMAGE_URL + movie.getPosterPath());
            Picasso.with(ivMoviePosterImage.getContext())
                    .load(url)
                    .placeholder(R.drawable.movie_poster)
                    .into(ivMoviePosterImage);
            tvMovieTitle.setText(movie.getTitle());
        }

        @Override
        public void onClick(View v) {
            final ListItemClickListener listener = mOnClickListener;

            if (listener != null && mMovies != null && !mMovies.isEmpty()) {
                final int position = getAdapterPosition();
                final Movie movie = mMovies.get(position);
                Log.d(TAG, "Item position" + position + " has been clicked");
                listener.onListItemClick(movie);
            }
        }

//    new pagination
        /*
        Helpers - Pagination
   _________________________________________________________________________________________________
    */

        public void add(Movie r) {
            mMovies.add(r);
            notifyItemInserted(mMovies.size() - 1);
        }

        public void addAll(List<Movie> moveResults) {
            for (Movie result : moveResults) {
                add(result);
            }
        }

        public void remove(Movie r) {
            int position = mMovies.indexOf(r);
            if (position > -1) {
                mMovies.remove(position);
                notifyItemRemoved(position);
            }
        }

        public void clear() {
            isLoadingAdded = false;
            while (getItemCount() > 0) {
                remove(getItem(0));
            }
        }

        public boolean isEmpty() {
            return getItemCount() == 0;
        }


        public void addLoadingFooter() {
            isLoadingAdded = true;
            add(new Movie());
        }

        public void removeLoadingFooter() {
            isLoadingAdded = false;

            int position = mMovies.size() - 1;
            Movie result = getItem(position);

            if (result != null) {
                mMovies.remove(position);
                notifyItemRemoved(position);
            }
        }
        public Movie getItem(int position) {
            return mMovies.get(position);
        }
    }
}
