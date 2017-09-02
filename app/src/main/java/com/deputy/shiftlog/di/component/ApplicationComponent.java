package com.deputy.shiftlog.di.component;

import android.content.Context;

import com.deputy.shiftlog.di.module.ApplicationModule;
import com.deputy.shiftlog.domain.executor.PostExecutionThread;
import com.deputy.shiftlog.domain.repository.ShiftLocalRepository;
import com.deputy.shiftlog.domain.repository.ShiftRemoteRepository;
import com.deputy.shiftlog.ui.activity.BaseActivity;

import java.util.concurrent.Executor;

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

    Context context();
    PostExecutionThread postExecutionThread();
    Executor executor();
    ShiftLocalRepository shiftLocalRepository();
    ShiftRemoteRepository shiftRemoteRepository();
}
