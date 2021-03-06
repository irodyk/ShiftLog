package com.deputy.shiftlog.domain.interactor;

import com.deputy.shiftlog.domain.executor.PostExecutionThread;
import com.deputy.shiftlog.domain.model.Shift;
import com.deputy.shiftlog.domain.repository.ShiftLocalRepository;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class EndShiftLocal extends UseCase<Shift, Shift> {

    private final ShiftLocalRepository shiftLocalRepository;

    @Inject
    EndShiftLocal(ShiftLocalRepository shiftLocalRepository,
                  Executor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.shiftLocalRepository = shiftLocalRepository;
    }

    @Override
    Observable<Shift> buildUseCaseObservable(Shift shift) {
        return shiftLocalRepository.endCurrentShift(shift);
    }
}
