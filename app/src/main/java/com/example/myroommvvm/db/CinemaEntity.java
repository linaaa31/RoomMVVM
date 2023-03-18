package com.example.myroommvvm.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CinemaEntity {

    @PrimaryKey(autoGenerate = true)
    public int cinemaId;

    @ColumnInfo(name="address")
    public String address;

    public CinemaEntity(String address){
        this.address = address;
    }
}
