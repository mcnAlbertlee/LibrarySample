package com.example.albert.librarytest.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.albert.librarytest.BaseActivity;
import com.example.albert.librarytest.R;
import com.example.albert.librarytest.network.dto.UsersResponse;

import javax.inject.Inject;

public class RxActivity extends BaseActivity {

    @Inject
    RxViewModel rxViewModel;

    boolean mHasText = false;

//    @Inject
//    RxSearchAdapter rxSearchAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rx);

        setScreenTitle("RxJava Test");

        setSearchUserInfo(null);
        initEditText();

        rxViewModel.getLiveUserInfo().observe(this, this::setSearchUserInfo);
    }

    public void initEditText() {
        EditText etSearch = findViewById(R.id.etSearch);
        etSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            boolean handled = false;

            if(i == EditorInfo.IME_ACTION_SEARCH) {
                searchUserInfo();

                handled = true;
            }

            return handled;
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mHasText = true;

                } else {
                    mHasText = false;
                }

                rxViewModel.enterUserName(String.valueOf(charSequence));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void searchUserInfo() {
        rxViewModel.getUserInfo();
    }

    public void setSearchUserInfo(UsersResponse userInfo) {

        RelativeLayout rvUserInfo = findViewById(R.id.rvUserInfo);
        if(userInfo ==  null) {
            rvUserInfo.setVisibility(View.GONE);
        } else {

            rvUserInfo.setVisibility(View.VISIBLE);

            ImageView ivAvatar = findViewById(R.id.ivAvatar);

            TextView tvName = findViewById(R.id.tvName);
            TextView tvCompany = findViewById(R.id.tvCompany);
            TextView tvRepos = findViewById(R.id.tvRepos);
            TextView tvFollowers = findViewById(R.id.tvFollowers);
            TextView tvFollowing = findViewById(R.id.tvFollowing);

            tvName.setText(userInfo.name);
            tvCompany.setText(userInfo.company);
            tvRepos.setText(Integer.toString(userInfo.public_repos));
            tvFollowers.setText(Integer.toString(userInfo.followers));
            tvFollowing.setText(Integer.toString(userInfo.following));
        }
    }
}
