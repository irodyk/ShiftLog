package com.deputy.shiftlog.data.repository.database;

import com.deputy.shiftlog.domain.model.Shift;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public interface ShiftLocalDataStore {

    Observable<ArrayList<Shift>> shiftList();
}
