package com.deputy.shiftlog.domain.interactor;

import com.deputy.shiftlog.domain.executor.PostExecutionThread;
import com.deputy.shiftlog.domain.model.Shift;
import com.deputy.shiftlog.domain.repository.ShiftLocalRepository;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class GetShiftsFromLocal extends UseCase<ArrayList<Shift>, Void> {

    private final ShiftLocalRepository shiftLocalRepository;

    @Inject
    GetShiftsFromLocal(ShiftLocalRepository shiftLocalRepository, Executor threadExecutor,
                       PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.shiftLocalRepository = shiftLocalRepository;
    }

    @Override
    Observable<ArrayList<Shift>> buildUseCaseObservable(Void unused) {
        return this.shiftLocalRepository.shifts();
    }
}
