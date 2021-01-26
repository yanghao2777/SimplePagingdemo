package com.qascg.pagingapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private final PagedList.Config pagingConfig = new PagedList.Config.Builder()
            .setPageSize(5)
            .setPrefetchDistance(200)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(true)
            .build();


    LiveData<PagedList<MainDataEntity>> mainData = new LivePagedListBuilder<>(
            MainDatabase.getInstance().getDataDao().queryAllData(), pagingConfig
    ).build();

    public void getMainData() {
        new Thread(() -> {
            List<MainDataBean> mainDataBeans = new ArrayList<>();
            MainDataEntity[] mainDataEntities = new MainDataEntity[100];
            try {
                Thread.sleep(2000);
                for (int i = 0; i < 100; i++) {
                    mainDataBeans.add(new MainDataBean(
                            "main data" + i,
                            System.currentTimeMillis())
                    );
                }

                Thread.sleep(1000);

                for(int i = 0;i < mainDataBeans.size();i++){
                    mainDataEntities[i] = new MainDataEntity(i,
                            mainDataBeans.get(i).name, mainDataBeans.get(i).addData);
                    Log.e("###","data:" + mainDataBeans.get(i).addData);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.e("###","size:" + mainDataEntities.length);
            MainDatabase.getInstance().getDataDao().insertAll(mainDataEntities);
        }).start();
    }

}
