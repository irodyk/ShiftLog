package com.deputy.shiftlog.domain.interactor;

import com.deputy.shiftlog.domain.executor.PostExecutionThread;
import com.deputy.shiftlog.domain.model.Shift;
import com.deputy.shiftlog.domain.repository.ShiftRemoteRepository;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class StartShiftRemote extends UseCase<Shift, Shift> {

    private final ShiftRemoteRepository shiftRemoteRepository;

    @Inject
    StartShiftRemote(ShiftRemoteRepository shiftRemoteRepository,
                     Executor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.shiftRemoteRepository = shiftRemoteRepository;
    }

    @Override
    Observable<Shift> buildUseCaseObservable(Shift shift) {
        return shiftRemoteRepository.createNewShift(shift);
    }
}
