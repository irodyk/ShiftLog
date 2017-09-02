package com.deputy.shiftlog.data.executor;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class ThreadExecutor implements Executor {

    private final ThreadPoolExecutor threadExecutor;

    @Inject
    ThreadExecutor() {
        this.threadExecutor = new ThreadPoolExecutor(
                3, 5, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue(), new JobThreadFactory());
    }

    @Override public void execute(@NonNull Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private int counter = 0;

        @Override public Thread newThread(@NonNull Runnable command) {
            return new Thread(command, "android_" + counter++);
        }
    }
}
