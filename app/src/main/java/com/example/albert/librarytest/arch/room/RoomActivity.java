package com.example.albert.librarytest.arch.room;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.albert.librarytest.BaseActivity;
import com.example.albert.librarytest.R;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * https://github.com/googlesamples/android-architecture-components/tree/master/BasicRxJavaSample/#readme
 */
public class RoomActivity extends BaseActivity {

    @Inject
    RoomViewModel roomViewModel;

    @Inject
    RoomUserAdapter adapter;

    ArrayList<UserEntity> allUserEntities = new ArrayList<>();

    EditText etInput;

    private String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room);

        etInput = findViewById(R.id.etInput);

        initEditText();
        actionLivedata();

        getAllUser();
    }

    public void initEditText() {


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userName = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        etInput.addTextChangedListener(textWatcher);
    }

    public void actionLivedata() {
        roomViewModel.getLivedataUserList().observe(this, users -> {
            allUserEntities.clear();
            allUserEntities.addAll(users);
            setRecyclerView();
        });

        roomViewModel.getLivedataDeleteStatus().observe(this, status -> {
            if(status)
                showToast("Delete Success");
            else
                showToast("Delete Failed");
        });

        roomViewModel.getLivedataInsertStatus().observe(this, status -> {
            if(status)
                showToast("Insert Success");
            else
                showToast("Insert Failed");
        });
    }

    public void setRecyclerView() {

        RecyclerView rvUsers = findViewById(R.id.rvUsers);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvUsers.setLayoutManager(manager);
        rvUsers.setAdapter(adapter);

        adapter.addAll(allUserEntities);

//        for(UserEntity temp : allUserEntities) {
//            Log.d(this.getClass().getSimpleName(), "User Name:" + temp.getUsername() + "\n");
//        }

    }

    public void getAllUser() {
        roomViewModel.getUserList();
    }

    public void insertUser(View view) {
        UserEntity userEntity = new UserEntity(userName);
        roomViewModel.insertUser(userEntity);

        etInput.clearFocus();
        etInput.setText("");
        userName = "";
    }

    public void deleteAllUsers(View view) {
        roomViewModel.deleteAllUsers();
    }
}
