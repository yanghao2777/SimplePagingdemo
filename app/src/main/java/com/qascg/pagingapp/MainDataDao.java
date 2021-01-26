package com.qascg.pagingapp;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MainDataDao {
    @Query("SELECT * FROM main_data_db")
    DataSource.Factory<Integer, MainDataEntity> queryAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MainDataEntity... mainDataBeans);
}
