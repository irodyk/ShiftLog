package com.deputy.shiftlog.di.module;

import android.app.Activity;

import com.deputy.shiftlog.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}