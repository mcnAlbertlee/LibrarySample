package com.example.albert.librarytest.rx;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.albert.librarytest.R;

public class RxSearchAdapter extends RecyclerView.Adapter<RxSearchAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView ivAvatar;

        public final TextView tvName;
        public final TextView tvCompany;
        public final TextView tvRepos;
        public final TextView tvFollowers;
        public final TextView tvFollowing;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            ivAvatar = view.findViewById(R.id.ivAvatar);

            tvName = view.findViewById(R.id.tvName);
            tvCompany = view.findViewById(R.id.tvCompany);
            tvRepos = view.findViewById(R.id.tvRepos);
            tvFollowers = view.findViewById(R.id.tvFollowers);
            tvFollowing = view.findViewById(R.id.tvFollowing);
        }
    }
}
