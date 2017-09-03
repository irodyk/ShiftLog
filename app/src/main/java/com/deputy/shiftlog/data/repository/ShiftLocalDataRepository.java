package com.deputy.shiftlog.data.repository;

import com.deputy.shiftlog.data.repository.database.ShiftLocalDataStore;
import com.deputy.shiftlog.domain.model.Shift;
import com.deputy.shiftlog.domain.repository.ShiftLocalRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class ShiftLocalDataRepository implements ShiftLocalRepository {

    private ShiftLocalDataStore shiftDataStore;

    @Inject
    ShiftLocalDataRepository(ShiftLocalDataStore shiftDataStore){
        this.shiftDataStore = shiftDataStore;
    }

    @Override
    public Observable<ArrayList<Shift>> shifts() {
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

    @Override
    public Observable<Boolean> storeShiftForUpdate(Shift shift) {
        return shiftDataStore.storeSync(shift);
    }

    @Override
    public Observable<Boolean> storeAllShifts(ArrayList<Shift> shifts) {
        return shiftDataStore.recreate(shifts);
    }
}
