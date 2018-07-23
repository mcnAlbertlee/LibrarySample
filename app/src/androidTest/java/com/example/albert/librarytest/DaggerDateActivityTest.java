package com.example.albert.librarytest;

import android.arch.lifecycle.MutableLiveData;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.example.albert.librarytest.dagger.DaggerDateActivity;
import com.example.albert.librarytest.dagger.DaggerDateViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dagger.android.AndroidInjection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DaggerDateActivityTest {

    @Rule
    public IntentsTestRule<DaggerDateActivity> activityRule = new IntentsTestRule<>(DaggerDateActivity.class, false, false);


    MutableLiveData<String> result = new MutableLiveData<>();

    @Test
    public void test()  {
        // given
        DaggerDateViewModel viewModel = mock(DaggerDateViewModel.class);
        MutableLiveData<String> currentDate = new MutableLiveData<>();

        when(viewModel.getDate()).thenReturn(currentDate);

        AndroidInjection.mocking(activity -> {
            if(activity instanceof DaggerDateActivity){
                ((DaggerDateActivity) activity).viewModel = viewModel;
            }
        });
    }

}
