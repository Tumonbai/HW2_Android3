package com.example.hw2_android3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hw2_android3.adapter.FilmAdapter;
import com.example.hw2_android3.data.models.Film;
import com.example.hw2_android3.data.network.GhibliService;
import com.example.hw2_android3.data.network.OnItemClickListener;
import com.example.hw2_android3.ui.detail.DescActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    RecyclerView rv;
    FilmAdapter filmAdapter;
    private ArrayList<Film> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recycler);
        setUpAdapter();


        GhibliService.getInstance().getAllFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                filmAdapter.addFilm(response.body(), MainActivity.this);
                Log.e("TAG", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

    public void init(View v) {
    }

    public void setUpAdapter() {
        filmAdapter = new FilmAdapter();
        rv.setAdapter(filmAdapter);

    }

    @Override
    public void onClick(String id) {
        startActivity(new Intent(this, DescActivity.class).putExtra("id", id));
    }
}