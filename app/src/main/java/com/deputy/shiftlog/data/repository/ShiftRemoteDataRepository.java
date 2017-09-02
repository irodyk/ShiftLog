package com.deputy.shiftlog.data.repository;

import com.deputy.shiftlog.data.repository.network.ShiftRemoteDataStore;
import com.deputy.shiftlog.model.Shift;
import com.deputy.shiftlog.domain.repository.ShiftRemoteRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class ShiftRemoteDataRepository implements ShiftRemoteRepository {

    private ShiftRemoteDataStore shiftDataStore;

    @Inject
    ShiftRemoteDataRepository(ShiftRemoteDataStore shiftDataStore){
        this.shiftDataStore = shiftDataStore;
    }

    @Override public Observable<ArrayList<Shift>> shifts() {
        return shiftDataStore.shiftList();
    }

    @Override
    public Observable<Shift> createNewShift(Shift shift) {
        return shiftDataStore.create(shift);
    }

    @Override
    public Observable<Shift> endCurrentShift(Shift shift) {
        return shiftDataStore.end(shift);
    }
}
