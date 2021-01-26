package com.qascg.pagingapp;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MainDataEntity.class}, version = 1,exportSchema = false)
public abstract class MainDatabase extends RoomDatabase {

    public abstract MainDataDao getDataDao();

    private static volatile MainDatabase Instance;

    public static MainDatabase getInstance() {
        if (Instance == null) {
            synchronized (MainDatabase.class) {
                if (Instance == null) {
                    Instance = Room.databaseBuilder(
                            PagingApp.getAppContext(),
                            MainDatabase.class,
                            "main_data_db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return Instance;
    }
}
