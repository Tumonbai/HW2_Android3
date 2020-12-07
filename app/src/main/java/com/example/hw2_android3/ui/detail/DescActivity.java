package com.example.hw2_android3.ui.detail;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hw2_android3.R;
import com.example.hw2_android3.data.models.Film;
import com.example.hw2_android3.data.network.GhibliApi;
import com.example.hw2_android3.data.network.GhibliService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescActivity extends AppCompatActivity {

    TextView tv_title, tv_desc, tv_director;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        tv_desc = findViewById(R.id.description);
        tv_director = findViewById(R.id.director);
        tv_title = findViewById(R.id.tv_title);
        String id = getIntent().getStringExtra("id");

        GhibliService.getInstance().getFilm(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                addText(response.body());
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }

    private void addText(Film film) {
        tv_title.setText(film.getTitle());
        tv_director.setText(film.getDirector());
        tv_desc.setText(film.getDescription());
    }
}
