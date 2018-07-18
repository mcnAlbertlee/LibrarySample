# LibrarySample

1. Architecture Component
2. Dagger
3. RxJava

## Architecture Component
### android.arch.lifecycle

* Dependency
```
implementation 'android.arch.lifecycle:runtime:1.1.1'
implementation 'android.arch.lifecycle:extensions:1.1.1'
annotationProcessor "android.arch.lifecycle:compiler:1.1.1"
```

### 구현
1. ViewModel을 상속받아서 ViewModel 작성(LiveData) 포함
2. 액티비티에서 사용할 ViewModel을 가져온다
    * 초기화값 필요 없음
        * ViewModelProviders.of(activity).get(ModuleName.class);
    * 초기화 값 필요
        * ViewModelProvider.Factory()를 사용해서 초기화

    ```
    return ViewModelProviders.of(walletListActivity, new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new WalletListViewModel(walletBackupService, walletSupervisor, tokenManager, storage);
        }
    }).get(WalletListViewModel.class);
    ```
3. ViewModel의 LiveData를 사용한 함수에 observe를 추가해서 데이터가 들어오면 Observer로 전달, 이후 UI를 업데이트
    ```
    model.getFruitList().observe(this, fruitlist -> {
        // update UI
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, fruitlist);
        // Assign adapter to ListView
        listView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    });
    ```


## 참고 사이트
[Android Architecture Component Sample](https://github.com/googlesamples/android-architecture-components)

[MVVM Architecture & LiveData, ViewModel, LifeCycle Components](https://medium.com/@gauravgyal/android-architecture-pattern-components-mvvm-livedata-viewmodel-lifecycle-544e84e85177)

[Android-Architecture-Components-ViewModel-Inject](https://thdev.tech/androiddev/2017/07/25/Android-Architecture-Components-ViewModel-Inject.html)

[ViewModel Document](https://developer.android.com/reference/android/arch/lifecycle/ViewModel)

[LiveData Document](https://developer.android.com/reference/android/arch/lifecycle/LiveData)


