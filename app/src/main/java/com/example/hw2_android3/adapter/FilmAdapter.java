package com.example.hw2_android3.adapter;

import android.service.autofill.OnClickAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw2_android3.R;
import com.example.hw2_android3.data.models.Film;
import com.example.hw2_android3.data.network.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    List<Film> films = new ArrayList<>();
    OnItemClickListener onItemClickListener;

    public void addFilm(List<Film> filmList, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        films.addAll(filmList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(films.get(position), onItemClickListener);

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);

        }

        void onBind(final Film films, final OnItemClickListener onItemClickListener) {
            title.setText(films.getTitle());
            desc.setText(films.getDescription());

            itemView.setOnClickListener(v -> onItemClickListener.onClick(films.getId()));

        }
    }
}
