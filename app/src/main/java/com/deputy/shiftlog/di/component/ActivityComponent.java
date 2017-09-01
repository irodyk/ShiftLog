package com.deputy.shiftlog.di.component;

import android.app.Activity;

import com.deputy.shiftlog.di.module.ActivityModule;
import com.deputy.shiftlog.di.scope.PerActivity;

import dagger.Component;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {

    Activity activity();
}
