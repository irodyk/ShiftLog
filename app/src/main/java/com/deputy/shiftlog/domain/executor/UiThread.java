package com.deputy.shiftlog.domain.executor;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class UiThread implements PostExecutionThread {

    @Inject UiThread() {}

    @Override public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
