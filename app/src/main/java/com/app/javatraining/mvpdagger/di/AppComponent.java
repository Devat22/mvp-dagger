package com.app.javatraining.mvpdagger.di;

import com.app.javatraining.mvpdagger.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        BuildersModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(App application);
        AppComponent build();
    }
    void inject(App app);
}
