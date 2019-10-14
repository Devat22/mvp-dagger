package com.app.javatraining.mvpdagger.di;

import com.app.javatraining.mvpdagger.App;
import com.app.javatraining.mvpdagger.activities.LobbyActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector(modules =
            {LobbyViewModule.class, LobbyModule.class})
    abstract LobbyActivity bindLobbyActivity();
}
