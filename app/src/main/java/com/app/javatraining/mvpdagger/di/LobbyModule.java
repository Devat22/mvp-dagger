package com.app.javatraining.mvpdagger.di;

import com.app.javatraining.mvpdagger.common.LoadCommonGreetingUseCase;
import com.app.javatraining.mvpdagger.lobby.LoadLobbyGreetingUseCase;
import com.app.javatraining.mvpdagger.lobby.LobbyGreetingContract;
import com.app.javatraining.mvpdagger.lobby.LobbyGreetingRepository;
import com.app.javatraining.mvpdagger.lobby.LobbyPresenter;
import com.app.javatraining.mvpdagger.rx.SchedulersFacade;

import dagger.Module;
import dagger.Provides;

@Module
public class LobbyModule {
    @Provides
    LobbyGreetingRepository provideLobbyGreetingRepository(){
        return new LobbyGreetingRepository();
    }
    @Provides
    LobbyPresenter provideLobbyPresenter(
            LobbyGreetingContract.LobbyView lobbyView,
            LoadCommonGreetingUseCase loadCommonGreetingUseCase,
            LoadLobbyGreetingUseCase loadLobbyGreetingUseCase,
            SchedulersFacade schedulersFacade
    ){
        return new LobbyPresenter(lobbyView,
                loadCommonGreetingUseCase,
                loadLobbyGreetingUseCase,
                schedulersFacade);
    }
}
