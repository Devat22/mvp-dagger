package com.app.javatraining.mvpdagger.lobby;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoadLobbyGreetingUseCase {
    private final LobbyGreetingRepository greetingRepository;
    @Inject
    LoadLobbyGreetingUseCase(LobbyGreetingRepository greetingRepository){
        this.greetingRepository = greetingRepository;
    }
    Single<String> execute(){
        return greetingRepository.getGreeting();
    }
}
