package com.deputy.shiftlog.data.repository.database;

import android.content.Context;

import com.deputy.shiftlog.domain.model.Shift;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class DatabaseManager implements ShiftLocalDataStore {

    @Inject
    DatabaseManager(Context context){
    }

    @Override
    public Observable<List<Shift>> shiftList() {
        return null;
    }
}
