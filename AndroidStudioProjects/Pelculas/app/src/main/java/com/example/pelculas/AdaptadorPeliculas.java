package com.example.pelculas;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import java.util.List;

    public class AdaptadorClub extends RecyclerView.Adapter<AdaptadorClub.ClubViewHolder> {
        private List<Peliculas> pelis;
        private Context context;

        public AdaptadorClub(List<Peliculas> pelis, Context context) {
            this.pelis = pelis;
            this.context = context;
        }

        @NonNull
        @Override
        public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.peliculas, parent, false);
            return new ClubViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
            Club club = clubs.get(position);
            holder.logoClub.setImageResource(club.getLogo());
            holder.nombreClub.setText(club.getNombre());
            holder.ubicacionClub.setText(club.getUbicacion());
            holder.estadioClub.setText("Estadio: " + club.getEstadio());
            holder.titulosClub.setText("Títulos: " + club.getTitulos());
            holder.ratingClub.setRating(club.getRating());
        }

        @Override
        public int getItemCount() {
            return clubs.size();
        }

        public static class ClubViewHolder extends RecyclerView.ViewHolder {
            ImageView logoClub;
            TextView nombreClub, ubicacionClub, estadioClub, titulosClub;
            RatingBar ratingClub;

            public ClubViewHolder(@NonNull View itemView) {
                super(itemView);
                logoClub = itemView.findViewById(R.id.iconoClub);
                nombreClub = itemView.findViewById(R.id.nombreClub);
                ubicacionClub = itemView.findViewById(R.id.ubicacionClub);
                estadioClub = itemView.findViewById(R.id.estadioClub);
                titulosClub = itemView.findViewById(R.id.titulosClub);
                ratingClub = itemView.findViewById(R.id.ratingClub);
            }
        }
    }

}
