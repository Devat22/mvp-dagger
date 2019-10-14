package com.app.javatraining.mvpdagger.di;

import com.app.javatraining.mvpdagger.activities.LobbyActivity;
import com.app.javatraining.mvpdagger.lobby.LobbyGreetingContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LobbyViewModule {
    @Binds
    abstract LobbyGreetingContract.LobbyView provideLobbyView(LobbyActivity lobbyActivity);
}
