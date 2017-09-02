package com.deputy.shiftlog.di.module;

import android.content.Context;

import com.deputy.shiftlog.app.ShiftLogApp;
import com.deputy.shiftlog.data.executor.ThreadExecutor;
import com.deputy.shiftlog.data.repository.ShiftLocalDataRepository;
import com.deputy.shiftlog.data.repository.ShiftRemoteDataRepository;
import com.deputy.shiftlog.data.repository.database.DatabaseManager;
import com.deputy.shiftlog.data.repository.database.ShiftLocalDataStore;
import com.deputy.shiftlog.data.repository.network.NetworkManager;
import com.deputy.shiftlog.data.repository.network.ShiftRemoteDataStore;
import com.deputy.shiftlog.domain.executor.PostExecutionThread;
import com.deputy.shiftlog.domain.executor.UiThread;
import com.deputy.shiftlog.domain.repository.ShiftLocalRepository;
import com.deputy.shiftlog.domain.repository.ShiftRemoteRepository;

import java.util.concurrent.Executor;

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
    private DatabaseManager databaseManager;
    private NetworkManager networkManager;

    public ApplicationModule(ShiftLogApp application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UiThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    Executor provideExecutor(ThreadExecutor threadExecutor) {
        return threadExecutor;
    }

    @Provides @Singleton
    ShiftLocalRepository provideShiftLocalRepository(ShiftLocalDataRepository shiftLocalDataRepository) {
        return shiftLocalDataRepository;
    }

    @Provides @Singleton
    ShiftRemoteRepository provideShiftRemoteRepository(ShiftRemoteDataRepository shiftRemoteDataRepository) {
        return shiftRemoteDataRepository;
    }

    @Provides @Singleton
    ShiftLocalDataStore provideShiftLocalDataStore(DatabaseManager databaseManager) {
        if(this.databaseManager == null)
            this.databaseManager = databaseManager;
        return this.databaseManager;
    }

    @Provides @Singleton
    ShiftRemoteDataStore provideShiftRemoteDataStore(NetworkManager networkManager) {
        if(this.networkManager == null)
            this.networkManager = networkManager;
        return this.networkManager;
    }
}
