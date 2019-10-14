package com.app.javatraining.mvpdagger.di;

import android.content.Context;

import com.app.javatraining.mvpdagger.App;
import com.app.javatraining.mvpdagger.common.CommonGreetingRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    Context provideContext(App app){
        return app.getApplicationContext();
    }
    @Singleton
    @Provides
    CommonGreetingRepository provideCommonHelloService(){
        return new CommonGreetingRepository();
    }
}
