package com.deputy.shiftlog.domain.interactor;

import com.deputy.shiftlog.domain.executor.PostExecutionThread;
import com.deputy.shiftlog.model.Shift;
import com.deputy.shiftlog.domain.repository.ShiftLocalRepository;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class StoreAllShiftsLocally extends UseCase<Boolean, ArrayList<Shift>> {

    private final ShiftLocalRepository shiftLocalRepository;

    @Inject
    StoreAllShiftsLocally(ShiftLocalRepository shiftLocalRepository, Executor threadExecutor,
                       PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.shiftLocalRepository = shiftLocalRepository;
    }

    @Override
    Observable<Boolean> buildUseCaseObservable(ArrayList<Shift> shifts) {
        return shiftLocalRepository.storeAllShifts(shifts);
    }
}
