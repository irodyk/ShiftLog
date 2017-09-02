package com.deputy.shiftlog.data.repository.network;

import com.deputy.shiftlog.domain.model.Shift;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public interface ShiftRemoteDataStore {

    Observable<ArrayList<Shift>> shiftList();
}
