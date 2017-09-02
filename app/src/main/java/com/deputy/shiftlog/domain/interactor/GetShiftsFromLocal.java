package com.deputy.shiftlog.domain.interactor;

import com.deputy.shiftlog.domain.executor.PostExecutionThread;
import com.deputy.shiftlog.domain.model.Shift;
import com.deputy.shiftlog.domain.repository.ShiftsLocalRepository;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class GetShiftsFromLocal extends UseCase<List<Shift>, Void> {

    private final ShiftsLocalRepository shiftsLocalRepository;

    @Inject
    GetShiftsFromLocal(ShiftsLocalRepository shiftsLocalRepository, Executor threadExecutor,
                        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.shiftsLocalRepository = shiftsLocalRepository;
    }

    @Override
    Observable<List<Shift>> buildUseCaseObservable(Void unused) {
        return this.shiftsLocalRepository.shifts();
    }
}
