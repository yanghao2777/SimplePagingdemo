package com.qascg.pagingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qascg.pagingapp.databinding.ItemMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainAdapter extends PagedListAdapter<MainDataEntity, MainAdapter.MainViewHolder> {

    protected MainAdapter(@NonNull DiffUtil.ItemCallback<MainDataEntity> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(
                ItemMainBinding.bind(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_main, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if (position >= super.getItemCount()) return;
        MainDataEntity data = getItem(position);
        if (data == null) return;

        holder.itemBinding.name.setText(data.name);
        holder.itemBinding.addTime.setText(transferLongToDate(data.addData));
    }

    public String transferLongToDate(Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    public static class MainDiff extends DiffUtil.ItemCallback<MainDataEntity> {

        @Override
        public boolean areItemsTheSame(@NonNull MainDataEntity oldItem, @NonNull MainDataEntity newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull MainDataEntity oldItem, @NonNull MainDataEntity newItem) {
            return oldItem.id == newItem.id && oldItem.name.equals(newItem.name) && oldItem.addData == newItem.addData;
        }
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        public ItemMainBinding itemBinding;

        public MainViewHolder(@NonNull ItemMainBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }
    }
}
