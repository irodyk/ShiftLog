package com.deputy.shiftlog.data.repository.network;

import com.deputy.shiftlog.model.Shift;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public interface ShiftRemoteDataStore {

    /**
     * Fetches all record from remote.
     */
    Observable<ArrayList<Shift>> shiftList();

    /**
     * Creates new POST request to create new record on server.
     * @param shift - object to send.
     */
    Observable<Shift> create(Shift shift);

    /**
     * Creates new POST request to end previously created shift on server.
     * @param shift - object to send.
     */
    Observable<Shift> end(Shift shift);
}
