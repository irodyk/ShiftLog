package com.deputy.shiftlog.domain.interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class DataObserver<T, V> extends DisposableObserver<T> {

    @Override
    public void onNext(T value) {
        //no default implementation
    }

    @Override
    public void onError(Throwable e) {
        //no default implementation
    }

    @Override
    public void onComplete() {
        //no default implementation
    }
}
