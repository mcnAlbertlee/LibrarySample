package com.example.albert.librarytest.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.albert.librarytest.R;
import com.example.albert.librarytest.arch.ArchActivity;
import com.example.albert.librarytest.arch.room.RoomActivity;
import com.example.albert.librarytest.dagger.DaggerDateActivity;
import com.example.albert.librarytest.rx.RxOperatorExampleActivity;

import java.util.ArrayList;
import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {

    MainViewModel viewModel;
    private List<String> mValues = new ArrayList<>();

    public MainListAdapter(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void addAll(List<String> sampleList) {
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
        String item = mValues.get(position);
        holder.tvMainTitle.setText(item);

        holder.mView.setOnClickListener(v -> {
            switch (position) {

                case 0: {
                    Context context = v.getContext();
                    Intent intent = new Intent(v.getContext(), ArchActivity.class);
                    context.startActivity(intent);
                }
                    break;

                case 1: {
                    Context context = v.getContext();
                    Intent intent = new Intent(v.getContext(), DaggerDateActivity.class);
                    context.startActivity(intent);
                }
                    break;

                case 2: {
                    Context context = v.getContext();
                    Intent intent = new Intent(v.getContext(), RxOperatorExampleActivity.class);
                    context.startActivity(intent);
                }
                    break;

                case 3: {
                    Context context = v.getContext();
                    Intent intent = new Intent(v.getContext(), RoomActivity.class);
                    context.startActivity(intent);
                }
                    break;

            }
        });
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
