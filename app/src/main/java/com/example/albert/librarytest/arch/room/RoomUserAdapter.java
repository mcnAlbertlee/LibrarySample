package com.example.albert.librarytest.arch.room;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.albert.librarytest.R;

import java.util.ArrayList;
import java.util.List;

public class RoomUserAdapter extends RecyclerView.Adapter<RoomUserAdapter.ViewHolder> {

    private List<UserEntity> mValues = new ArrayList<>();

    public void addAll(List<UserEntity> sampleList) {
        mValues.clear();
        mValues.addAll(sampleList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_main, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserEntity item = mValues.get(position);
        holder.tvMainTitle.setText(item.getUsername());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvMainTitle;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            tvMainTitle = view.findViewById(R.id.tvMainTitle);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvMainTitle.getText() + "'";
        }
    }
}
