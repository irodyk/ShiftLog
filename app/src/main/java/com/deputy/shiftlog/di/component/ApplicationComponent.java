package com.deputy.shiftlog.di.component;

import com.deputy.shiftlog.di.module.ApplicationModule;
import com.deputy.shiftlog.ui.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
}
