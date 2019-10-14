package com.app.javatraining.mvpdagger.lobby;

public interface LobbyGreetingContract {
    interface LobbyView{
        //button clicks
        void onCommonGreatingClicked();
        void onLobbyGreetingButtonClicked();

        //greating display
        void displayGreeting(String greeting);

        //error
        void displayGreetingError(Throwable throwable);

        void setLoadingIndicator(boolean active);
    }

    interface LobbyPresenter{
        void loadCommonGreeting();
        void loadLobbyGreeting();

    }
}
