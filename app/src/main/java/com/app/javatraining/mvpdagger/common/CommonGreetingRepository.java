package com.app.javatraining.mvpdagger.common;

import io.reactivex.Single;

public class CommonGreetingRepository {


    public Single<String> getGreeting() {
        return Single.just("Hello from Common Greeting Repository");
    }
}
