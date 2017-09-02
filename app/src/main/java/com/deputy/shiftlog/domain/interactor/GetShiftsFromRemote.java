package com.deputy.shiftlog.domain.interactor;

import com.deputy.shiftlog.domain.executor.PostExecutionThread;
import com.deputy.shiftlog.domain.model.Shift;
import com.deputy.shiftlog.domain.repository.ShiftsLocalRepository;
import com.deputy.shiftlog.domain.repository.ShiftsRemoteRepository;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class GetShiftsFromRemote extends UseCase<List<Shift>, Void> {

    private final ShiftsRemoteRepository shiftsRemoteRepository;

    @Inject
    GetShiftsFromRemote(ShiftsRemoteRepository shiftsRemoteRepository, Executor threadExecutor,
                       PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.shiftsRemoteRepository = shiftsRemoteRepository;
    }

    @Override
    Observable<List<Shift>> buildUseCaseObservable(Void unused) {
        return this.shiftsRemoteRepository.shifts();
    }
}