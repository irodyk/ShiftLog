package com.deputy.shiftlog.data.repository.database;

import com.deputy.shiftlog.domain.model.Shift;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public interface ShiftLocalDataStore {

    /**
     * Fetches all record from database.
     */
    Observable<ArrayList<Shift>> shiftList();

    /**
     * Creates new record in database.
     * @param shift - source for record.
     */
    Observable<Shift> create(Shift shift);

    /**
     * Updates latest record in database.
     * @param shift - source for update.
     */
    Observable<Shift> end(Shift shift);

    /**
     * Stores to synchronization table and will be fetches later to update remote.
     * @param shift - source to store.
     */
    Observable<Boolean> storeSync(Shift shift);

    /**
     * Recreates Response table and puts all new records.
     * @param shifts - newly fetched shifts from remote.
     */
    Observable<Boolean> recreate(ArrayList<Shift> shifts);

    /**
     * Returns all shifts, which were not synced.
     */
    Observable<ArrayList<Shift>> queryAllSync();
}
