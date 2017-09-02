package com.deputy.shiftlog.domain.repository;

import com.deputy.shiftlog.domain.model.Shift;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public interface ShiftLocalRepository {

    /**
     * Provides a list of all shifts from local database.
     */
    Observable<ArrayList<Shift>> shifts();

    /**
     * Creates new shift in local database.
     * @param shift - shift to create.
     */
    Observable<Void> createNewShift(Shift shift);

    /**
     * Updates currently started shift.
     * @param shift - object contains information of time and location ending.
     */
    Observable<Void> endCurrentShift(Shift shift);

    /**
     * Saves shift for update due to disconnecting from network.
     * @param shift - shift to be updated later.
     */
    Observable<Boolean> storeShiftForUpdate(Shift shift);

    /**
     * Overwrites database with updated shifts (if any).
     * @param shifts - list of shifts returned from server.
     * @
     */
    Observable<Boolean> storeAllShifts(ArrayList<Shift> shifts);
}
