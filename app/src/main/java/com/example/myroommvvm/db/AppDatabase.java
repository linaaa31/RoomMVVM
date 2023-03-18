package com.example.myroommvvm.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FilmEntity.class, CinemaEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FilmDao filmDao();
    public abstract CinemaDao cinemaDao();
}
