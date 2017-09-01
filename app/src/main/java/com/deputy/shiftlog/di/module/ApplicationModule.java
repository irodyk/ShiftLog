package com.deputy.shiftlog.di.module;

import android.content.Context;

import com.deputy.shiftlog.app.ShiftLogApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

@Module
public class ApplicationModule {
    private final ShiftLogApp application;

    public ApplicationModule(ShiftLogApp application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.application;
    }
}
