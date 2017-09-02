package com.deputy.shiftlog.data.repository.network;

import android.content.Context;

import com.deputy.shiftlog.domain.model.Shift;

import java.util.List;

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
    public Observable<List<Shift>> shiftList() {
        return null;
    }
}
