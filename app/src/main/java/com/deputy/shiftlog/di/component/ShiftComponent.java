package com.deputy.shiftlog.di.component;

import com.deputy.shiftlog.di.module.ActivityModule;
import com.deputy.shiftlog.di.module.ShiftModule;
import com.deputy.shiftlog.di.scope.PerActivity;
import com.deputy.shiftlog.ui.fragment.ShiftListFragment;

import dagger.Component;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ShiftModule.class})
public interface ShiftComponent extends ActivityComponent {
    void inject(ShiftListFragment shiftListFragment);
}
