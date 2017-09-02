package com.deputy.shiftlog.data.repository.network;

import android.content.Context;

import com.deputy.shiftlog.model.Shift;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class NetworkManager implements ShiftRemoteDataStore {

    @Inject
    NetworkManager(Context context){

    }

    @Override
    public Observable<ArrayList<Shift>> shiftList() {
        return null;
    }

    @Override
    public Observable<Shift> create(Shift shift) {
        return null;
    }

    @Override
    public Observable<Shift> end(Shift shift) {
        return null;
    }
}
