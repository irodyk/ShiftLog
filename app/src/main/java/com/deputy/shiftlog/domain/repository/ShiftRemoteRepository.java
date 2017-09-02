package com.deputy.shiftlog.domain.repository;

import com.deputy.shiftlog.domain.model.Shift;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public interface ShiftRemoteRepository {

    Observable<ArrayList<Shift>> shifts();
}
