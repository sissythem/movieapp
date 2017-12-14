package com.gnt.utils;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gnt.appobjects.MovieDto;
import com.gnt.appobjects.UserDto;
import com.gnt.moviesapp.R;

import java.util.ArrayList;

/**
 * Created by sissy on 12/9/17.
 */

public class RecyclerAdapterMovies extends RecyclerView.Adapter<RecyclerAdapterMovies.MoviesCardHolder>{

        private Context context;
        UserDto user;
        private ArrayList<MovieDto> movies = new ArrayList<>();
        public void setSearchList(ArrayList<MovieDto> movies) {this.movies = movies;}

        public RecyclerAdapterMovies(Context context, UserDto user, ArrayList<MovieDto> movies) {
            this.context    = context;
            this.user       = user;
            this.movies     = movies;
        }

        @Override
        public MoviesCardHolder onCreateViewHolder (ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies, parent, false);
            return new MoviesCardHolder(view);
        }

        @Override
        public void onBindViewHolder(MoviesCardHolder holder, final int position) {
            Utils.loadPoster(context, holder.rPhoto, movies.get(position).getPosterPath());
            holder.rTitle.setText(movies.get(position).getOriginalTitle());
            holder.rGenre.setText(movies.get(position).getGenres().toString());
            holder.rOverview.setText(movies.get(position).getOverview());
            holder.rRatingBar.setRating((float)movies.get(position).getAverageRating());

//            holder.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener(){
//                @Override
//                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//                    if (context.getClass().equals(HostActivity.class)) {
//                        menu.setHeaderTitle("Reservations - Host Options");
//
//                        menu.add(0, position, 0, VIEW_RESIDENCE_ACTION);
//                        menu.add(0, position, 1, EDIT_ACTION);
//                        menu.add(0, position, 2, RESERVATIONS_ACTION);
//                        menu.add(0, position, 3, DELETE_ACTION);
//                    }
//                }
//            });
//
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final Bundle btype = new Bundle();
//                    btype.putBoolean("type", user);
//                    btype.putString("source", "home");
//                    btype.putInt("residenceId", residences.get(position).getId());
//                    btype.putString("guests", Integer.toString(guests));
//                    btype.putString("startDate", startDate);
//                    btype.putString("endDate", endDate);
//                    Utils.goToActivity(context, ResidenceActivity.class, btype);
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }

        public static class MoviesCardHolder extends RecyclerView.ViewHolder
        {
            CardView rCardView;
            ImageView rPhoto;
            TextView rTitle, rGenre, rOverview;
            RatingBar rRatingBar;

            public MoviesCardHolder(View itemView) {
                super(itemView);

                rCardView       = itemView.findViewById(R.id.rCardView);
                rPhoto          = itemView.findViewById(R.id.poster);
                rTitle          = itemView.findViewById(R.id.title);
                rGenre          = itemView.findViewById(R.id.genre);
                rOverview       = itemView.findViewById(R.id.overview);
                rRatingBar      = itemView.findViewById(R.id.rating);
            }

            public void setOnCreateContextMenuListener(View.OnCreateContextMenuListener listener) {
                itemView.setOnCreateContextMenuListener(listener);
            }
        }

}
