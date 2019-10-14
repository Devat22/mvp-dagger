package com.app.javatraining.mvpdagger.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {

    protected final V view;
    private CompositeDisposable disposables = new CompositeDisposable();
    protected BasePresenter(V view){
        this.view = view;
    }
    public void start(){

    }
    public void stop(){
        disposables.clear();
    }

    protected void addDisposable(Disposable disposable){
        disposables.add(disposable);
    }

}
