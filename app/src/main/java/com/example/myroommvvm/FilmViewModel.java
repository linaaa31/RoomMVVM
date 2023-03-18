package com.example.myroommvvm;


import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myroommvvm.db.AppDatabase;
import com.example.myroommvvm.db.CinemaEntity;
import com.example.myroommvvm.db.DatabaseClient;
import com.example.myroommvvm.db.FilmEntity;

import java.util.List;

public class FilmViewModel extends AndroidViewModel {
    private MutableLiveData<List<FilmEntity>> films;
    private MutableLiveData<List<CinemaEntity>> cinemas;
    private AppDatabase appDatabase;
    public FilmViewModel(@NonNull Application application) {
        super(application);
        Log.i("UWC","ViewModel created ");
        films = new MutableLiveData<>();
        cinemas = new MutableLiveData<>();
        appDatabase = DatabaseClient.getInstance(getApplication()).getAppDatabase();
        AsyncTask.execute(()->{
            Log.i("UWC","ViewModel ...");
            refreshFilmList();
            refreshCinemaList();
        });
    }
    public LiveData<List<FilmEntity>> getfilms() {
        return films;
    }
    public LiveData<List<CinemaEntity>>getCinemas(){
        return cinemas;
    }
    public void addFilm(String filmName, Integer duration) {
        Log.i("UWC","add");
        //DB access must be done from background thread
        AsyncTask.execute(() -> {
        appDatabase.filmDao().insertFilm(new FilmEntity(filmName, duration));
        refreshFilmList();
    });
}
    public void addCinema(String address) {
        Log.i("UWC","add");
        //DB access must be done from background thread
        AsyncTask.execute(() -> {
            appDatabase.cinemaDao().insertCinema(new CinemaEntity(address));
            refreshCinemaList();
        });
    }
    public void deleteFilms(FilmEntity film) {
        AsyncTask.execute(() -> {
            appDatabase.filmDao().deleteFilm(film);
            refreshFilmList();
        });

    }
    public void deleteCinemas(CinemaEntity cinema) {
        AsyncTask.execute(() -> {
            appDatabase.cinemaDao().deleteCinema(cinema);
            refreshCinemaList();
        });
    }
    private void refreshFilmList(){
    List<FilmEntity> updateList = appDatabase.filmDao().getAllFilms();
    films.postValue(updateList);
}
    private void refreshCinemaList(){
        List<CinemaEntity> updateList = appDatabase.cinemaDao().getAllCinemas();
        cinemas.postValue(updateList);
    }
}