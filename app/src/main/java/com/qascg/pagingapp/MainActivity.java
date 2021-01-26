package com.qascg.pagingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.qascg.pagingapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel vModel;
    private ActivityMainBinding vBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //StrictMode

        vBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(vBinding.getRoot());

        ViewModelProvider viewModelProvider =
                new ViewModelProvider(this,
                        new ViewModelProvider.NewInstanceFactory());
        vModel = viewModelProvider.get(MainViewModel.class);

        vBinding.mainRecycler.setLayoutManager(new LinearLayoutManager(this));

        dataObserver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData(){
        vModel.getMainData();
    }

    private MainAdapter adapter;
    private void dataObserver(){
        vModel.mainData.observe(this,it -> {
            if(vBinding.mainRecycler.getAdapter() == null || adapter == null){
                adapter = new MainAdapter(new MainAdapter.MainDiff());
                vBinding.mainRecycler.setAdapter(adapter);
            }
            adapter.submitList(it);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vBinding = null;
    }
}