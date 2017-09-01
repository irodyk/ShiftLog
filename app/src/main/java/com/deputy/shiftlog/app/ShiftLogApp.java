package com.deputy.shiftlog.app;

import android.app.Application;

import com.deputy.shiftlog.BuildConfig;
import com.deputy.shiftlog.di.component.ApplicationComponent;
import com.deputy.shiftlog.di.component.DaggerApplicationComponent;
import com.deputy.shiftlog.di.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

public class ShiftLogApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
