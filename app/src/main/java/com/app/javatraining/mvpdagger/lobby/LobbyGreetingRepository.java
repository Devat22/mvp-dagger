package com.app.javatraining.mvpdagger.lobby;

import io.reactivex.Single;

public class LobbyGreetingRepository {
    Single<String> getGreeting(){
        return Single.just("Hello from LobbyGreeting Repository");
    }
}
