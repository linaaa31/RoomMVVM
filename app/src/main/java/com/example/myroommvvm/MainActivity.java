package com.example.myroommvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myroommvvm.db.CinemaEntity;
import com.example.myroommvvm.db.FilmEntity;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private FilmListAdapter adapter;
    private BlankFragment fragment;
    private FilmViewModel filmViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RecyclerView init to display the data
        RecyclerView rv = findViewById(R.id.item_list);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new FilmListAdapter();
        rv.setAdapter(adapter);
        FilmViewModel filmViewModel = new ViewModelProvider(this).get(FilmViewModel.class);
        //Retrieving data from ViewModel and passing to RecyclerView
        filmViewModel.getfilms().observe(this, filmEntities -> {
            Log.i("UWC", "Employees live data changed");
            adapter.setFilms(filmEntities);
        });
        filmViewModel.getCinemas().observe(this, cinemaEntities -> {
            Log.i("UWC", "Employees live data changed");
            adapter.setCinemas(cinemaEntities);
        });

    }
    private class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.FilmCardHolder> {
        List<FilmEntity> films;
        List<CinemaEntity> cinemas;

        public void setFilms(List<FilmEntity> films) {
            this.films = new ArrayList<>(films);
            //this.cinemas = new ArrayList<>(cinemas);
            notifyDataSetChanged();
        }
        public void setCinemas( List <CinemaEntity> cinemas) {
           // this.films = new ArrayList<>(films);
            this.cinemas = new ArrayList<>(cinemas);
            notifyDataSetChanged();
        }
        @NonNull
        @Override
        public FilmCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            return new FilmCardHolder(inflater.inflate(R.layout.view_film_item, parent, false));            }
        @Override
        public void onBindViewHolder(@NonNull FilmCardHolder holder, int position) {
            holder.filmName.setText(films.get(position).filmName);
            holder.cinemaAddress.setText(cinemas.get(position).address);
            holder.duration.setText(String.valueOf(films.get(position).duration));
        }
        @Override
        public int getItemCount() {
            return films != null ? films.size() : 0;
        }
        class FilmCardHolder extends RecyclerView.ViewHolder {
            TextView filmName;
            TextView cinemaAddress;
            TextView duration;
            ImageView delete;
            public FilmCardHolder(@NonNull View itemView) {
                super(itemView);
                filmName = itemView.findViewById(R.id.tvFilmName);
                cinemaAddress = itemView.findViewById(R.id.tvCinemaAddress);
                duration= itemView.findViewById(R.id.tvDuration);
                delete = itemView.findViewById(R.id.film_delete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        filmViewModel.deleteFilms(films.get(getAdapterPosition()));
                        filmViewModel.deleteCinemas(cinemas.get(getAdapterPosition()));
                    }
                });
            }
        }        }
}