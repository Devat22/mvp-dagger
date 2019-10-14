package com.app.javatraining.mvpdagger.lobby;

import com.app.javatraining.mvpdagger.common.LoadCommonGreetingUseCase;
import com.app.javatraining.mvpdagger.mvp.BasePresenter;
import com.app.javatraining.mvpdagger.rx.SchedulersFacade;

import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;

import com.jakewharton.rxrelay2.BehaviorRelay;

public class LobbyPresenter extends BasePresenter<LobbyGreetingContract.LobbyView>
implements LobbyGreetingContract.LobbyPresenter{
    private final LoadCommonGreetingUseCase loadCommonGreetingUseCase;
    private final LoadLobbyGreetingUseCase loadLobbyGreetingUseCase;
    private final SchedulersFacade schedulersFacade;
    private final BehaviorRelay<RequestState> requestStateObserver
            = BehaviorRelay.createDefault(RequestState.IDLE);
     public LobbyPresenter(LobbyGreetingContract.LobbyView view
             , LoadCommonGreetingUseCase loadCommonGreetingUseCase,
                           LoadLobbyGreetingUseCase loadLobbyGreetingUseCase,
                           SchedulersFacade schedulersFacade) {
        super(view);
        this.loadCommonGreetingUseCase = loadCommonGreetingUseCase;
        this.loadLobbyGreetingUseCase = loadLobbyGreetingUseCase;
        this.schedulersFacade = schedulersFacade;
        observeRequestState();
    }

    @Override
    public void loadCommonGreeting() {
        loadGreeting(loadCommonGreetingUseCase.execute());
    }

    private void loadGreeting(Single<String> greetingString) {
        addDisposable(greetingString
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
                .doOnSubscribe(s->publishRequestState(RequestState.LOADING))
                .doOnSuccess(s->publishRequestState(RequestState.COMPLETE))
                .doOnError(t->publishRequestState(RequestState.ERROR))
                .subscribe(view::displayGreeting, view::displayGreetingError)
        );
     }
    private void publishRequestState(RequestState requestState){
        addDisposable(Observable.just(requestState)
        .observeOn(schedulersFacade.ui())
        .subscribe(requestStateObserver));
    }
    @Override
    public void loadLobbyGreeting() {
        loadGreeting(loadLobbyGreetingUseCase.execute());
     }
     private void observeRequestState(){
         requestStateObserver.subscribe(requestState -> {
             switch (requestState){
                 case ERROR: view.setLoadingIndicator(false);
                 break;
                 case LOADING:
                     view.setLoadingIndicator(true);break;
                 case IDLE:break;
                 case COMPLETE:view.setLoadingIndicator(false);
             }
         }, Timber::e);
     }
}
