package com.deputy.shiftlog.domain.repository;

import com.deputy.shiftlog.model.Shift;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public interface ShiftRemoteRepository {

    /**
     * Provides a list of all shifts from remote.
     */
    Observable<ArrayList<Shift>> shifts();

    /**
     * Creates new shift remotely.
     * @param shift - shift to create.
     */
    Observable<Shift> createNewShift(Shift shift);

    /**
     * Updates currently started shift.
     * @param shift - object contains information of time and location ending.
     */
    Observable<Shift> endCurrentShift(Shift shift);

}
