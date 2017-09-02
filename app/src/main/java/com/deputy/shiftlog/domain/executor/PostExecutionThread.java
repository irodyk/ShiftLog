package com.deputy.shiftlog.domain.executor;

import io.reactivex.Scheduler;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public interface PostExecutionThread {

    Scheduler getScheduler();
}
