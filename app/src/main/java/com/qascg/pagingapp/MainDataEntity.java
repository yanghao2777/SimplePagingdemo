package com.qascg.pagingapp;

import androidx.annotation.Keep;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Keep
@Entity(tableName = "main_data_db")
public class MainDataEntity {
    @PrimaryKey
    public long id;

    public String name;
    public long addData;

    public MainDataEntity(long id,String name,long addData) {
        this.id = id;
        this.name = name;
        this.addData = addData;
    }

}
