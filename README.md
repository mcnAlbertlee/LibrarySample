# LibrarySample

1. Architecture Component
2. Dagger
3. RxJava
4. Crashlytics

## Sample App
* 기본적으로 샘플들과 연결 되어 샘플리스트를 제공하고 해당 샘플을 선택하면 샘플이 적용된 화면을 제공하는 샘플 앱

### 앱 설계
1. Dagger + Arch를 사용해서 DependencyInject 및 ViewModel을 앱 전반적으로 적용한다.
2. Crashlytics를 사용해서 버그 리포팅
3. 리사이클뷰를 사용해서 리스트로 보여주며 샘플 이름으로 각 샘플의 리스트를 생성 하고 클릭하면 해당 샘플의 뷰로 연결한다.
4. strings.xml 을 사용해서 string-array로 샘플 이름을 추가한다. (샘플이 추가되면 여기에 추가)
5. Activity 기준으로 화면을 만들고 Dagger의 Inject를 사용해서 의존성 주입해서 사용
6. 샘플들 이름으로 앱 타이틀바 변경
7. Manifest에 메타정보로 개인 fabric id가 노출되므로 그래들과 키를 로컬에 두어서 숨김처리

### 참고사이트
[Manifest에 포함되어 있는 메타 정보(facebook sdk id, fabric id 등) 숨기기](https://medium.com/@elye.project/keep-your-keys-outside-manifest-and-gradle-ba44110bafca)


## RxJava
### 참고 사이트
[RxJava 시각화 다이어그램](http://rxmarbles.com/)

[RxJava2 AndroidSamples](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples)

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


### 참고 사이트
[Android Architecture Component Sample](https://github.com/googlesamples/android-architecture-components)

[MVVM Architecture & LiveData, ViewModel, LifeCycle Components](https://medium.com/@gauravgyal/android-architecture-pattern-components-mvvm-livedata-viewmodel-lifecycle-544e84e85177)

[Android-Architecture-Components-ViewModel-Inject](https://thdev.tech/androiddev/2017/07/25/Android-Architecture-Components-ViewModel-Inject.html)

[ViewModel Document](https://developer.android.com/reference/android/arch/lifecycle/ViewModel)

[LiveData Document](https://developer.android.com/reference/android/arch/lifecycle/LiveData)


## Dagger

### DaggerApplication 사용
* DaggerApplication, DaggerActivity, DaggerFragment 등을 제공.
* Application, Activity, Fragment 중에 주입받을 곳이 어디인지 신경쓰지 않아도 된다.
 

### 참고 사이트
[MVP with Dagger 2.11](https://proandroiddev.com/mvp-with-dagger-2-11-847d52c27c5a)

[@Binds, @ContributesAndroidInjector 샘플](https://proandroiddev.com/dagger-2-annotations-binds-contributesandroidinjector-a09e6a57758f)

[Android Dagger2 ContributesAndroidInjector](http://rimduhui.tistory.com/57)