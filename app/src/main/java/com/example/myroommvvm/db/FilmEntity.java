package com.example.myroommvvm.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FilmEntity {
    @PrimaryKey(autoGenerate = true)
    public int filmID;

    @ColumnInfo(name="filmName")
    public String filmName;

    @ColumnInfo(name="duration")
    public Integer duration;

    public FilmEntity(String filmName, Integer duration){
        this.filmName = filmName;
        this.duration = duration;
    }
}
